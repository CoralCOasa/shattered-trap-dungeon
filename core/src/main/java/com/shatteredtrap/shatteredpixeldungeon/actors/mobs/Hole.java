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
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredtrap.shatteredpixeldungeon.levels.features.Chasm;
import com.shatteredtrap.shatteredpixeldungeon.sprites.HoleSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.RatSprite;
import com.watabou.utils.Random;

public class Hole extends Mob {

	{
		spriteClass = HoleSprite.class;
		HP = HT = 90;
		defenseSkill = 0;
		baseSpeed = 0.5f;
		EXP = 12;
		maxLvl = 25;
		properties.add(Property.DEMONIC);
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 17, 25 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 38;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 12);
	}

	@Override
	public int attackProc(Char enemy, int damage) {
		if (enemy != null && !enemy.flying) {
			if (enemy == Dungeon.hero) {
				Chasm.heroFall(pos);
			} else {
				Chasm.mobFall((Mob) enemy);
			}
		}
		return damage;
	}

}
