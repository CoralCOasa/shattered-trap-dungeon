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

import com.shatteredtrap.shatteredpixeldungeon.Assets;
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Trapskill;
import com.shatteredtrap.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredtrap.shatteredpixeldungeon.effects.Speck;
import com.shatteredtrap.shatteredpixeldungeon.items.Generator;
import com.shatteredtrap.shatteredpixeldungeon.items.spells.ReclaimTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.Trap;
import com.shatteredtrap.shatteredpixeldungeon.sprites.GnollTricksterSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.GoblinSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.RatSprite;
import com.shatteredtrap.shatteredpixeldungeon.utils.BArray;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Goblin extends Mob {

	{
		spriteClass = GoblinSprite.class;

		HP = HT = (1 + Dungeon.depth) * 3;
		EXP = 1 + Dungeon.depth/2;
		defenseSkill = attackSkill( null ) / 2;
		loot = new ReclaimTrap();
		lootChance = 1f;
	}

	@Override
	public void move(int step) {
		PathFinder.buildDistanceMap( pos, BArray.not( Dungeon.level.solid, null ), 3 );
		boolean laughed = false;
		if(state != WANDERING || this.buff(Terror.class) != null){
			for (int i = 0; i < PathFinder.distance.length; i++) {
				if (PathFinder.distance[i] < Integer.MAX_VALUE) {
					Trap t = Dungeon.level.traps.get(i);
					if (t != null && t.active) {
						//t.reveal();
						//t.activate();
						//t.disarm();
						t.trigger();
						if (!laughed) {
							if (Dungeon.level.heroFOV[pos]) {
								Sample.INSTANCE.play(Assets.SND_MIMIC, 0.7f, 0.7f, 3f);
								CellEmitter.get(i).start(Speck.factory(Speck.STAR), 0.05f, 16);
							}
							laughed = true;
						}
					}
				}
			}
		}
		super.move(step);
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( HT / 6, (HT / 3) +1 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 9 + Dungeon.depth;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 1+Dungeon.depth/2);
	}
}
