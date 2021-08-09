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
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredtrap.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredtrap.shatteredpixeldungeon.levels.features.Chasm;
import com.shatteredtrap.shatteredpixeldungeon.sprites.BombSpiderSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.DancingSkeletonSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.RatSprite;
import com.watabou.utils.Random;

public class BombSpider extends Mob {
	int nuke = 2;
	Object o = null;
	{
		spriteClass = BombSpiderSprite.class;
		HP = HT = 35;
		defenseSkill = 12;
		EXP = 7;
		maxLvl = 15;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 5, 20 );
	}

	@Override
	protected boolean act() {
		if(HP == 0){
			nuke--;
			this.sprite.showStatus( CharSprite.WARNING,"!!!" );
			if(nuke==0){
				die(o);
			}
			spend(1);
			return true;
		}

		return super.act();
	}

	@Override
	public int attackSkill( Char target ) {
		return 16;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 5);
	}

	@Override
	public void die( Object cause ) {
		if (buff(Chill.class) != null || cause == Chasm.class){
			super.die(cause);
			return;
		}

		if(nuke==0){
			super.die( cause );
			new Bomb().explode(pos);
		}else{
			o = cause;
			((BombSpiderSprite)sprite).explode(pos);
		}
	}
}
