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

package com.shatteredtrap.shatteredpixeldungeon.items.spells;

import com.shatteredtrap.shatteredpixeldungeon.Assets;
import com.shatteredtrap.shatteredpixeldungeon.Badges;
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredtrap.shatteredpixeldungeon.Statistics;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Trapskill;
import com.shatteredtrap.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredtrap.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.shatteredtrap.shatteredpixeldungeon.items.quest.MetalShard;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.Trap;
import com.shatteredtrap.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredtrap.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;

import java.util.ArrayList;

public class ReclaimTrap2 extends TargetedSpell {
	
	{
		image = ItemSpriteSheet.RECLAIM_TRAP;
	}
	
	private Class<?extends Trap> storedTrap = null;

	public boolean isTrapStored(){
		if(storedTrap==null){
			return false;
		}
		return true;
	}

	public void storeTrap(Class<?extends Trap> trapp){
		this.storedTrap = trapp;
	}

	@Override
	public ArrayList<String> actions(Hero hero ) {
		ArrayList<String> actions = new ArrayList<>();
		actions.add( AC_CAST );
		return actions;
	}

	@Override
	protected void affectTarget(Ballistica bolt, Hero hero) {
		if (storedTrap == null) {
			quantity++; //storing a trap doesn't consume the spell
			Trap t = Dungeon.level.traps.get(bolt.collisionPos);
			if (t != null && t.active && t.visible) {
				t.disarm();
				if(hero.getTrapSkill()>0){
					hero.setCurrentTrapSkill2(hero.getTrapSkill());
					//Buff.prolong( hero, Trapskill.class, 999999f );
				}
				Sample.INSTANCE.play(Assets.SND_LIGHTNING);
				ScrollOfRecharging.charge(hero);
				storedTrap = t.getClass();
				Statistics.trapsReclaimed++;
				Badges.validateTrapsReclaimed();

				Belongings b = hero.belongings;
				if (b.misc1 instanceof AlchemistsToolkit){
					((AlchemistsToolkit)b.misc1).chargeFull();
				}
				if (b.misc2 instanceof AlchemistsToolkit){
					((AlchemistsToolkit)b.misc2).chargeFull();
				}
			} else {
				GLog.w(Messages.get(this, "no_trap"));
			}
		} else {
			
			try {
				Trap t = storedTrap.newInstance();

				//DemonBeacon beacons = Dungeon.hero.belongings.getItem( DemonBeacon.class );
				boolean consume = true;
				if (hero.getCurrentTrapSkill2() != 0 ){
					//if(Random.Int(1+beacons.quantity())!=0){
					//	GLog.p( "Crystal's energy conserves the spell!" );
					//	CellEmitter.get(Dungeon.hero.pos).start(Speck.factory(Speck.STAR), 0.02f, 14);
					hero.setCurrentTrapSkill2(hero.getCurrentTrapSkill2()-1);
					//Buff.prolong( hero, Trapskill.class, 99999f );
					if (hero.getCurrentTrapSkill2()==0){
						Buff.detach( hero, Trapskill.class);
					}
						consume = false;
					//}
				}
				if(consume){
					storedTrap = null;
				}else{
					quantity++;
				}
				
				t.pos = bolt.collisionPos;
				t.activate();
				
			} catch (Exception e) {
				ShatteredPixelDungeon.reportException(e);
			}
		}
	}
	
	@Override
	public String desc() {
		String desc = super.desc();
		if (storedTrap != null){
			desc += "\n\n" + Messages.get(this, "desc_trap", Messages.get(storedTrap, "name"));
		}
		return desc;
	}
	
	@Override
	protected void onThrow(int cell) {
		storedTrap = null;
		super.onThrow(cell);
	}
	
	@Override
	public void doDrop(Hero hero) {
		storedTrap = null;
		super.doDrop(hero);
	}
	
	private static final ItemSprite.Glowing[] COLORS = new ItemSprite.Glowing[]{
			new ItemSprite.Glowing( 0xFF0000 ),
			new ItemSprite.Glowing( 0xFF8000 ),
			new ItemSprite.Glowing( 0xFFFF00 ),
			new ItemSprite.Glowing( 0x00FF00 ),
			new ItemSprite.Glowing( 0x00FFFF ),
			new ItemSprite.Glowing( 0x8000FF ),
			new ItemSprite.Glowing( 0xC0E1FF ),
			new ItemSprite.Glowing( 0x808080 ),
			new ItemSprite.Glowing( 0x000000 )
	};

	private static final int[] COLORS2 = new int[]{
			0xFF0000,
			0xFF8000,
			0xFFFF00 ,
			0x00FF00 ,
			0x00FFFF ,
			0x8000FF ,
			0xC0E1FF ,
			 0x808080,
			0x000000
	};
	
	@Override
	public ItemSprite.Glowing glowing() {
		if (storedTrap != null){
			try {
				return COLORS[storedTrap.newInstance().color];
			} catch (Exception e) {
				ShatteredPixelDungeon.reportException(e);
			}
		}
		return null;
	}

	public int storedColor(){
		try{
			return COLORS2[storedTrap.newInstance().color];
		}catch(Exception e){
			//lol
		}
		return 0;
	}

	@Override
	public int price() {
		//prices of ingredients, divided by output quantity
		return Math.round(quantity * ((40 + 100) / 6f));
	}
	
	private static final String STORED_TRAP = "stored_trap";
	
	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(STORED_TRAP, storedTrap);
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		storedTrap = bundle.getClass(STORED_TRAP);
	}
	
	public static class Recipe extends com.shatteredtrap.shatteredpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{ScrollOfMagicMapping.class, MetalShard.class};
			inQuantity = new int[]{1, 1};
			
			cost = 6;
			
			output = ReclaimTrap2.class;
			outQuantity = 3;
		}
		
	}
	
}
