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
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.Actor;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Monk;
import com.shatteredtrap.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredtrap.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.darts.Dart;
import com.shatteredtrap.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredtrap.shatteredpixeldungeon.sprites.MissileSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.tweeners.PosTweener;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class CannonTrap extends Trap {

	{
		color = ORANGE;
		shape = CROSSHAIR;
	}

	@Override
	public Trap hide() {
		//this one can't be hidden
		return reveal();
	}

	@Override
	public void activate() {
		for(int i = 0; i<2;i++) {
			Char target = Actor.findChar(pos);
			Char target2 = Actor.findChar(pos);

			//find the closest char that can be aimed at
			if (target == null) {
				for (Char ch : Actor.chars()) {
					Ballistica bolt = new Ballistica(pos, ch.pos, Ballistica.PROJECTILE);
					if (bolt.collisionPos == ch.pos &&
							(target == null || Dungeon.level.trueDistance(pos, ch.pos) < Dungeon.level.trueDistance(pos, target.pos))) {
						target2 = target;
						target = ch;
					}
				}
			}

			if (target != null) {
				final Char finalTarget = target;
				final Char finalTarget2 = target2;
				final CannonTrap trap = this;
				if (Dungeon.level.heroFOV[pos] || Dungeon.level.heroFOV[target.pos]) {
					Actor.add(new Actor() {

						{
							//it's a visual effect, gets priority no matter what
							actPriority = VFX_PRIO;
						}

						@Override
						protected boolean act() {
							final Actor toRemove = this;
							if(finalTarget.HP!=0) {
								((MissileSprite) ShatteredPixelDungeon.scene().recycle(MissileSprite.class)).
										reset(pos, finalTarget.sprite, new Bomb(), new Callback() {
											@Override
											public void call() {
												new Bomb().explode(finalTarget.pos);
												Actor.remove(toRemove);
												next();
											}
										});
							}else if(finalTarget2.HP!=0){
								((MissileSprite) ShatteredPixelDungeon.scene().recycle(MissileSprite.class)).
										reset(pos, finalTarget2.sprite, new Bomb(), new Callback() {
											@Override
											public void call() {
												new Bomb().explode(finalTarget2.pos);
												Actor.remove(toRemove);
												next();
											}
										});
							}else{
								Actor.remove(toRemove);
								next();
							}
							return false;
						}
					});
				} else {
					new Bomb().explode(finalTarget.pos);
				}
			}
		}
	}
	}

