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
import com.shatteredtrap.shatteredpixeldungeon.items.bombs.ShrapnelBomb;
import com.shatteredtrap.shatteredpixeldungeon.sprites.BombSpider2Sprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.BombSpiderSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.CharSprite;
import com.watabou.utils.Random;

public class BombSpider2 extends Mob {
	int nuke = 4;
	Object o = null;
	{
		spriteClass = BombSpider2Sprite.class;
		HP = HT = 60;
		defenseSkill = 19;
		EXP = 11;
		maxLvl = 21;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 16, 24 );
	}

	@Override
	protected boolean act() {
		if(HP == 0){
			nuke--;
			this.sprite.showStatus( CharSprite.WARNING,nuke+"" );
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
		return 27;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 7);
	}

	@Override
	public void die( Object cause ) {
		if (buff(Chill.class) != null){
			super.die(cause);
			return;
		}

		if(nuke==0){
			super.die( cause );
			new ShrapnelBomb().explode(pos);
		}else{
			o = cause;
			((BombSpider2Sprite)sprite).explode(pos);
		}
	}
}
