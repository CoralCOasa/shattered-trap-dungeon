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

package com.shatteredtrap.shatteredpixeldungeon.levels.traps;

import com.shatteredtrap.shatteredpixeldungeon.Assets;
import com.shatteredtrap.shatteredpixeldungeon.Challenges;
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredtrap.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredtrap.shatteredpixeldungeon.effects.particles.PurpleParticle;
import com.shatteredtrap.shatteredpixeldungeon.items.EquipableItem;
import com.shatteredtrap.shatteredpixeldungeon.items.Generator;
import com.shatteredtrap.shatteredpixeldungeon.items.Heap;
import com.shatteredtrap.shatteredpixeldungeon.items.Item;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredtrap.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.exotic.ExoticPotion;
import com.shatteredtrap.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfTransmutation;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.exotic.ExoticScroll;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.MagesStaff;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredtrap.shatteredpixeldungeon.journal.Catalog;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.plants.Plant;
import com.shatteredtrap.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class TransmutationTrap extends Trap {

	{
		color = VIOLET;
		shape = LARGE_DOT;
	}

	@Override
	public void activate() {
		if (Dungeon.level.heroFOV[ pos ]) {
			CellEmitter.get(pos).burst(PurpleParticle.BURST, 8);
			Sample.INSTANCE.play(Assets.SND_PUFF);
		}
		Heap heap = Dungeon.level.heaps.get( pos );
		if (heap != null){
		    Item i = heap.items.get(heap.size()-1);
		    Item ii = transmuteOnGround(i);
		    if(ii!=null){
		        heap.items.removeLast();
                Dungeon.level.drop(ii, pos).sprite.drop();
            }
		}
		if (Dungeon.hero.pos == pos && !Dungeon.hero.flying){
			curse(Dungeon.hero);
		}
	}

	public void curse(Hero hero){
		GLog.n( Messages.get(TransmutationTrap.class, "curse") );
		if(Random.Int(3)==0 && hero.belongings.weapon!=null){
            transmute(hero.belongings.weapon);
        }
        if(Random.Int(3)==0 && hero.belongings.misc1!=null){
            transmute(hero.belongings.misc1);
        }
        if(Random.Int(3)==0 && hero.belongings.misc2!=null){
            transmute(hero.belongings.misc2);
        }
		for(int i = 0;i< Random.Int(15)+3;i++){
			transmute(hero.belongings.randomUnequipped());
		}
	}

	//stuff !

	public void transmute(Item item) {

		if (item instanceof Bag){
			Bag bag = Dungeon.hero.belongings.backpack;
			bag = (Bag)item;
			item = Random.element(bag.items);
		}

		Item result;

		if (item instanceof MagesStaff) {
			result = changeStaff( (MagesStaff)item );
		} else if (item instanceof MeleeWeapon || item instanceof MissileWeapon) {
			result = changeWeapon( (Weapon)item );
		} else if (item instanceof Scroll) {
			result = changeScroll( (Scroll)item );
		} else if (item instanceof Potion) {
			result = changePotion( (Potion)item );
		} else if (item instanceof Ring) {
			result = changeRing( (Ring)item );
		} else if (item instanceof Wand) {
			result = changeWand( (Wand)item );
		} else if (item instanceof Plant.Seed) {
			result = changeSeed((Plant.Seed) item);
		} else if (item instanceof Runestone) {
			result = changeStone((Runestone) item);
		} else if (item instanceof Artifact) {
			result = changeArtifact( (Artifact)item );
		} else {
			result = null;
		}

		if (result == null){
			//This shouldn't ever trigger
			//GLog.n( Messages.get(this, "nothing") );
		} else {
			if (item.isEquipped(Dungeon.hero)){
				item.cursed = false; //to allow it to be unequipped
				((EquipableItem)item).doUnequip(Dungeon.hero, false);
				((EquipableItem)result).doEquip(Dungeon.hero);
			} else {
				item.detach(Dungeon.hero.belongings.backpack);
				if (!result.collect()){
					Dungeon.level.drop(result, Dungeon.hero.pos).sprite.drop();
				}
			}
			if (result.isIdentified()){
				Catalog.setSeen(result.getClass());
			}
			//TODO visuals
			//GLog.p( Messages.get(this, "morph") );
		}

	}


	public Item transmuteOnGround(Item item) {

		if (item instanceof Bag){
			Bag bag = Dungeon.hero.belongings.backpack;
			bag = (Bag)item;
			item = Random.element(bag.items);
		}

		Item result;

		if (item instanceof MagesStaff) {
			result = changeStaff( (MagesStaff)item );
		} else if (item instanceof MeleeWeapon || item instanceof MissileWeapon) {
			result = changeWeapon( (Weapon)item );
		} else if (item instanceof Scroll) {
			result = changeScroll( (Scroll)item );
		} else if (item instanceof Potion) {
			result = changePotion( (Potion)item );
		} else if (item instanceof Ring) {
			result = changeRing( (Ring)item );
		} else if (item instanceof Wand) {
			result = changeWand( (Wand)item );
		} else if (item instanceof Plant.Seed) {
			result = changeSeed((Plant.Seed) item);
		} else if (item instanceof Runestone) {
			result = changeStone((Runestone) item);
		} else if (item instanceof Artifact) {
			result = changeArtifact( (Artifact)item );
		} else {
			result = null;
		}

		if (result == null){
			//This shouldn't ever trigger
			//GLog.n( Messages.get(this, "nothing") );
		} else {
			if (result.isIdentified()){
				Catalog.setSeen(result.getClass());
			}
			return result;
		}
		return null;
	}



	//yes yes

	private static MagesStaff changeStaff(MagesStaff staff ){
		Class<?extends Wand> wandClass = staff.wandClass();

		if (wandClass == null){
			return null;
		} else {
			Wand n;
			do {
				n = (Wand) Generator.random(Generator.Category.WAND);
			} while (Challenges.isItemBlocked(n) || n.getClass() == wandClass);
			n.level(0);
			n.identify();
			staff.imbueWand(n, null);
		}

		return staff;
	}

	private static Weapon changeWeapon( Weapon w ) {

		Weapon n;
		Generator.Category c;
		if (w instanceof MeleeWeapon) {
			c = Generator.wepTiers[((MeleeWeapon)w).tier - 1];
		} else {
			c = Generator.misTiers[((MissileWeapon)w).tier - 1];
		}

		do {
			try {
				n = (Weapon)c.classes[Random.chances(c.probs)].newInstance();
			} catch (Exception e) {
				ShatteredPixelDungeon.reportException(e);
				return null;
			}
		} while (Challenges.isItemBlocked(n) || n.getClass() == w.getClass());

		int level = w.level();
		if (w.curseInfusionBonus) level--;
		if (level > 0) {
			n.upgrade( level );
		} else if (level < 0) {
			n.degrade( -level );
		}

		n.enchantment = w.enchantment;
		n.curseInfusionBonus = w.curseInfusionBonus;
		n.levelKnown = w.levelKnown;
		n.cursedKnown = w.cursedKnown;
		n.cursed = w.cursed;
		n.augment = w.augment;

		return n;

	}

	private static Ring changeRing(Ring r ) {
		Ring n;
		do {
			n = (Ring)Generator.random( Generator.Category.RING );
		} while (Challenges.isItemBlocked(n) || n.getClass() == r.getClass());

		n.level(0);

		int level = r.level();
		if (level > 0) {
			n.upgrade( level );
		} else if (level < 0) {
			n.degrade( -level );
		}

		n.levelKnown = r.levelKnown;
		n.cursedKnown = r.cursedKnown;
		n.cursed = r.cursed;

		return n;
	}

	private static Artifact changeArtifact(Artifact a ) {
		Artifact n = Generator.randomArtifact();

		if (n != null && !Challenges.isItemBlocked(n)){
			n.cursedKnown = a.cursedKnown;
			n.cursed = a.cursed;
			n.levelKnown = a.levelKnown;
			n.transferUpgrade(a.visiblyUpgraded());
			return n;
		}

		return null;
	}

	private static Wand changeWand( Wand w ) {

		Wand n;
		do {
			n = (Wand)Generator.random( Generator.Category.WAND );
		} while ( Challenges.isItemBlocked(n) || n.getClass() == w.getClass());

		n.level( 0 );
		int level = w.level();
		if (w.curseInfusionBonus) level--;
		n.upgrade( level );

		n.levelKnown = w.levelKnown;
		n.cursedKnown = w.cursedKnown;
		n.cursed = w.cursed;
		n.curseInfusionBonus = w.curseInfusionBonus;

		return n;
	}

	private static Plant.Seed changeSeed(Plant.Seed s ) {

		Plant.Seed n;

		do {
			n = (Plant.Seed)Generator.random( Generator.Category.SEED );
		} while (n.getClass() == s.getClass());

		return n;
	}

	private static Runestone changeStone(Runestone r ) {

		Runestone n;

		do {
			n = (Runestone) Generator.random( Generator.Category.STONE );
		} while (n.getClass() == r.getClass());

		return n;
	}

	private static Scroll changeScroll(Scroll s ) {
		try {
			if (s instanceof ExoticScroll) {
				return ExoticScroll.exoToReg.get(s.getClass()).newInstance();
			} else {
				return ExoticScroll.regToExo.get(s.getClass()).newInstance();
			}
		} catch ( Exception e ){
			ShatteredPixelDungeon.reportException(e);
			return null;
		}
	}

	private static Potion changePotion(Potion p ) {
		try {
			if (p instanceof ExoticPotion) {
				return ExoticPotion.exoToReg.get(p.getClass()).newInstance();
			} else {
				return ExoticPotion.regToExo.get(p.getClass()).newInstance();
			}
		} catch ( Exception e ){
			ShatteredPixelDungeon.reportException(e);
			return null;
		}
	}
}
