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

package com.shatteredtrap.shatteredpixeldungeon.items.bombs;

import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.Actor;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredtrap.shatteredpixeldungeon.levels.Level;
import com.shatteredtrap.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class Flashbang extends Bomb {
	
	{
		image = ItemSpriteSheet.FLASHBANG;
	}

	@Override
	public void explode(int cell) {
		super.explode(cell);

		Level l = Dungeon.level;
		for (Char ch : Actor.chars()){
			if (ch.fieldOfView != null && ch.fieldOfView[cell]){
				int power = 16 - 4*l.distance(ch.pos, cell);
				if (power > 0){
					Buff.prolong(ch, Blindness.class, power);
					Buff.prolong(ch, Cripple.class, power);
				}
				if (ch == Dungeon.hero){
					GameScene.flash(0xFFFFFF);
				}
			}
		}
		
	}
	
	@Override
	public int price() {
		//prices of ingredients
		return quantity * (20 + 40);
	}
}
