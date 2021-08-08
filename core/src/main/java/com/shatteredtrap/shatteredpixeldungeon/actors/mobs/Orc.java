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

import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.sprites.OrcSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.RatSprite;
import com.watabou.utils.Random;

public class Orc extends Mob {

	{
		spriteClass = OrcSprite.class;
		HP = HT = 16;
		defenseSkill = 4;
		EXP = 4;
		maxLvl = 9;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 2, 11 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 13;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 2);
	}

}
