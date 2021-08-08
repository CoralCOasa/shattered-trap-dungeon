/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredtrap.shatteredpixeldungeon.items.artifacts;

import com.shatteredtrap.shatteredpixeldungeon.Assets;
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.Actor;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredtrap.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Shaman;
import com.shatteredtrap.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredtrap.shatteredpixeldungeon.effects.Chains;
import com.shatteredtrap.shatteredpixeldungeon.effects.Lightning;
import com.shatteredtrap.shatteredpixeldungeon.effects.Pushing;
import com.shatteredtrap.shatteredpixeldungeon.effects.Speck;
import com.shatteredtrap.shatteredpixeldungeon.effects.particles.ElmoParticle;
import com.shatteredtrap.shatteredpixeldungeon.effects.particles.EnergyParticle;
import com.shatteredtrap.shatteredpixeldungeon.effects.particles.EnergyParticleFast;
import com.shatteredtrap.shatteredpixeldungeon.effects.particles.SparkParticle;
import com.shatteredtrap.shatteredpixeldungeon.items.EquipableItem;
import com.shatteredtrap.shatteredpixeldungeon.items.Item;
import com.shatteredtrap.shatteredpixeldungeon.items.Recipe;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.Trap;
import com.shatteredtrap.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.scenes.AlchemyScene;
import com.shatteredtrap.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredtrap.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredtrap.shatteredpixeldungeon.utils.BArray;
import com.shatteredtrap.shatteredpixeldungeon.utils.GLog;
import com.shatteredtrap.shatteredpixeldungeon.windows.WndBag;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.GameMath;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class AlchemistsToolkit extends Artifact {

	{
		image = ItemSpriteSheet.ARTIFACT_TOOLKIT;
		defaultAction = AC_CHANNEL;

		levelCap = 10;
		
		charge = (int)(level()*0.6f)+2;
		partialCharge = 0;
		chargeCap = (int)(level()*0.6f)+2;
	}

	public static final String AC_CHANNEL = "CHANNEL";
	public static final String AC_ADD = "ADD";
	
	protected WndBag.Mode mode = WndBag.Mode.EQUIPMENT;
	
	private boolean alchemyReady = false;

	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		if (isEquipped( hero ) && !cursed)
			actions.add(AC_CHANNEL);
		if (isEquipped( hero ) && level() < levelCap && !cursed)
			actions.add(AC_ADD);
		return actions;
	}

	@Override
	public void execute(Hero hero, String action ) {

		super.execute(hero, action);
		if (action.equals(AC_CHANNEL)){
			if (!isEquipped(hero))                                          GLog.i( Messages.get(this, "need_to_equip") );
			else if (cursed)                                                GLog.w( Messages.get(this, "cursed") );
			else if (false)                                         GLog.i( Messages.get(this, "upghint") );
			else if (true){
				GameScene.selectCell(caster);
			}
			
		}else if (action.equals(AC_ADD)){
			GameScene.selectItem(itemSelector, mode, "Sacrifice gear");
		}
	}

	private CellSelector.Listener caster = new CellSelector.Listener(){

		@Override
		public void onSelect(Integer targeti) {
			if(targeti==null){
				return;
			}
			if (charge <= 0){
				GLog.w( "The crown hasn't charged up enough!" );
				return;
			}
			PathFinder.buildDistanceMap(targeti, BArray.or(Dungeon.level.passable, Dungeon.level.avoid, null));
			if (!Dungeon.level.heroFOV[targeti] || Dungeon.level.solid[targeti]){
				GLog.w( "The magic of the crown can't reach there." );
				return;
			}

			Char c = Actor.findChar(targeti);

			if (c == null){
				for (Char ch : Actor.chars()){
					Ballistica bolt = new Ballistica(targeti, ch.pos, Ballistica.PROJECTILE);
					if (bolt.collisionPos == ch.pos &&
							(c == null || Dungeon.level.trueDistance(targeti, ch.pos) < Dungeon.level.trueDistance(targeti, c.pos))){
						c = ch;
					}
				}
			}
			if (c != null) {
				final Char cc = c;
				final int targetiti = targeti;
				Actor.add(new Pushing(cc, cc.pos, targetiti, new Callback() {

					public void call() {
						Dungeon.level.press(targetiti, cc, true);
					}
				}));
				cc.pos = targetiti;
				cc.sprite.centerEmitter().burst(SparkParticle.FACTORY, 5+level());

				if (cc instanceof Mob){
					int dmg = Random.NormalIntRange(1, 2+level()*2);
					if (Dungeon.level.water[cc.pos] && !cc.flying) {
						dmg *= 1.5f;
					}
					cc.damage( dmg, new Shaman.LightningBolt() );
				}
			}
			CellEmitter.get(targeti).burst(EnergyParticleFast.FACTORY, 20);
			Dungeon.observe();
			GameScene.updateFog();
			charge--;
			updateQuickslot();
			Dungeon.hero.spendAndNext(1f);
			Sample.INSTANCE.play(Assets.SND_LIGHTNING, 1, 1, 0.8f);
			/*
			if (target != null && (Dungeon.level.visited[target] || Dungeon.level.mapped[target])){

				//chains cannot be used to go where it is impossible to walk to
				PathFinder.buildDistanceMap(target, BArray.or(Dungeon.level.passable, Dungeon.level.avoid, null));
				if (PathFinder.distance[curUser.pos] == Integer.MAX_VALUE){
					GLog.w( Messages.get(EtherealChains.class, "cant_reach") );
					return;
				}

				final Ballistica chain = new Ballistica(curUser.pos, target, Ballistica.STOP_TARGET);

				if (Actor.findChar( chain.collisionPos ) != null){
					chainEnemy( chain, curUser, Actor.findChar( chain.collisionPos ));
				} else {
					chainLocation( chain, curUser );
				}

			}
*/
		}

		@Override
		public String prompt() {
			return Messages.get(EtherealChains.class, "prompt");
		}
	};

	@Override
	protected ArtifactBuff passiveBuff() {
		return new kitEnergy();
	}
	
	@Override
	public void charge(Hero target) {
		if (charge < chargeCap){
			partialCharge += 0.25f;
			if (partialCharge >= 1){
				partialCharge--;
				charge++;
				updateQuickslot();
			}
		}
	}

	public void chargeFull(){
		charge = chargeCap;
	}

	@Override
	public Item upgrade() {
		if(level()%2==1){
			chargeCap++;
		}

		return super.upgrade();
	}

	public void absorbEnergy( int energy ){
		
		exp += energy;
		while (exp >= 10 && level() < levelCap){
			upgrade();
			exp -= 10;
		}
		if (level() == levelCap){
			partialCharge += exp;
			energy -= exp;
			exp = 0;
		}
		
		partialCharge += energy/3f;
		while (partialCharge >= 1){
			
			partialCharge -= 1;
			charge++;
			
			if (charge >= chargeCap){
				charge = chargeCap;
				partialCharge = 0;
				break;
			}
		}
		updateQuickslot();
		
	}

	@Override
	public String desc() {
		String result = Messages.get(this, "desc");
		if (isEquipped(Dungeon.hero)) {
			if(!cursed){
				result += "\n\n" + Messages.get(this, "desc_wear2");
			}
			result += "\n\n" + Messages.get(this, "desc_wear");
			if (cursed)             result += "\n\n" + Messages.get(this, "desc_cursed");
			else if (level()<levelCap) result += "\n\n" + Messages.get(this, "desc_upghint");
			else                    result += "\n\n" + Messages.get(this, "desc_upgdone");
		}
		
		return result;
	}
	
	@Override
	public boolean doEquip(Hero hero) {
		if (super.doEquip(hero)){
			alchemyReady = false;
			return true;
		} else {
			return false;
		}
	}
	
	private static final String READY = "ready";
	
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(READY, alchemyReady);
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		alchemyReady = bundle.getBoolean(READY);
	}

	public class kitEnergy extends ArtifactBuff implements AlchemyScene.AlchemyProvider {

		@Override
		public boolean act() {

			if(cursed){
				for (int n : PathFinder.NEIGHBOURS9) {
					int c = Dungeon.hero.pos+n;
					Trap t = Dungeon.level.traps.get(c);
					if (t != null && t.active) {
						t.reveal();
						t.activate();
						t.disarm();
						CellEmitter.get(c).start(Speck.factory(Speck.STAR), 0.1f, 4);
					}
				}
			}
			spend( TICK );
			return true;
		}

		public void gainCharge(float levelPortion) {


			if(true){
				return;
			}
			alchemyReady = true;
			
			if (cursed) return;
			
			if (charge < chargeCap) {
				
				//generates 2 energy every hero level, +0.1 energy per toolkit level
				//to a max of 12 energy per hero level
				//This means that energy absorbed into the kit is recovered in 6.67 hero levels (as 33% of input energy is kept)
				//exp towards toolkit levels is included here
				float effectiveLevel = GameMath.gate(0, level() + exp/10f, 10);
				partialCharge += (2 + (1f * effectiveLevel)) * levelPortion;
				
				//charge is in increments of 1/10 max hunger value.
				while (partialCharge >= 1) {
					charge++;
					partialCharge -= 1;
					
					if (charge == chargeCap){
						GLog.p( Messages.get(AlchemistsToolkit.class, "full") );
						partialCharge = 0;
					}
					
					updateQuickslot();
				}
			} else
				partialCharge = 0;
		}
		
		@Override
		public int getEnergy() {
			return charge;
		}
		
		@Override
		public void spendEnergy(int reduction) {
			charge = Math.max(0, charge - reduction);
		}
	}

	protected WndBag.Listener itemSelector = new WndBag.Listener() {
		@Override
		public void onSelect(Item item) {
			if(item==null){
				return;
			}
			if (item != null && (item instanceof EquipableItem)&&!item.isEquipped(Dungeon.hero)&& !(item instanceof MissileWeapon)&& !item.cursed){
				Hero hero = Dungeon.hero;

						hero.sprite.operate( hero.pos );
						hero.busy();
						hero.spend( 2f );
						Sample.INSTANCE.play( Assets.SND_RAY, 1f, 1f, 2f );
						hero.sprite.emitter().burst( Speck.factory( Speck.STEAM ), 8 );
						item.detach(hero.belongings.backpack);

						upgrade();
						chargeFull();
						updateQuickslot();
						GLog.p( "The item was infused into the crown to strengthen it!" );
						return;
			}else if(item.isEquipped(Dungeon.hero)){
				GLog.w( "Unequip the item first!" );
			}else if(item instanceof MissileWeapon){
				GLog.w( "The missile weapon is rejected by the crown." );
			}else if(item.cursed){
				GLog.n( "The crown rejects the cursed item!" );
				item.cursedKnown = true;
			}
		}
	};


	public static class upgradeKit extends Recipe {
		
		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			return ingredients.get(0) instanceof AlchemistsToolkit
					&& !AlchemyScene.providerIsToolkit();
		}
		
		private static int lastCost;
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			return lastCost = Math.max(1, AlchemyScene.availableEnergy());
		}
		
		@Override
		public Item brew(ArrayList<Item> ingredients) {
			AlchemistsToolkit existing = (AlchemistsToolkit) ingredients.get(0);
			
			existing.absorbEnergy(lastCost);
			
			return existing;
		}
		
		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			AlchemistsToolkit sample = new AlchemistsToolkit();
			sample.identify();
			
			AlchemistsToolkit existing = (AlchemistsToolkit) ingredients.get(0);
			
			sample.charge = existing.charge;
			sample.partialCharge = existing.partialCharge;
			sample.exp = existing.exp;
			sample.level(existing.level());
			sample.absorbEnergy(AlchemyScene.availableEnergy());
			return sample;
		}




	}

}
