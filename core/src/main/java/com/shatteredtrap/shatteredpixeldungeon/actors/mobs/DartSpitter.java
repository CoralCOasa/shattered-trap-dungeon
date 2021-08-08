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

package com.shatteredtrap.shatteredpixeldungeon.actors.mobs;

import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Light;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredtrap.shatteredpixeldungeon.items.Generator;
import com.shatteredtrap.shatteredpixeldungeon.items.Item;
import com.shatteredtrap.shatteredpixeldungeon.items.food.MysteryMeat;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredtrap.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.sprites.DartSpitterSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ScorpioSprite;
import com.shatteredtrap.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Random;

public class DartSpitter extends Mob {
	
	{
		spriteClass = DartSpitterSprite.class;
		
		HP = HT = 45;
		defenseSkill = 13;
		viewDistance = Light.DISTANCE;
		baseSpeed = 2f;

		EXP = 8;
		maxLvl = 16;
		
		loot = Generator.Category.MISSILE;
		lootChance = 0.2f;

	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 6, 21 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 18;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 7);
	}
	
	@Override
	protected boolean canAttack( Char enemy ) {

		Ballistica attack = new Ballistica( pos, enemy.pos, Ballistica.PROJECTILE);
		return attack.collisionPos == enemy.pos;
	}

	@Override
	public int defenseProc( Char enemy, int damage ) {

		if(HP-damage<HT/2 && Random.Int( 3 )>0){
			Buff.affect( this, Terror.class, 10f+Random.Int( 16 ) );
		}
		return super.defenseProc( enemy, damage );
	}
	
}
