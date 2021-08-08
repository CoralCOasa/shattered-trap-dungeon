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

package com.shatteredtrap.shatteredpixeldungeon.sprites;

import com.shatteredtrap.shatteredpixeldungeon.Assets;
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.SPDSettings;
import com.shatteredtrap.shatteredpixeldungeon.effects.Speck;
import com.watabou.noosa.TextureFilm;

public class DancingSkeletonSprite extends MobSprite {
	private Animation dance;
	public DancingSkeletonSprite() {
		super();
		
		texture( Assets.DANCINGSKELETON );
		
		TextureFilm frames = new TextureFilm( texture, 16, 16 );
		
		idle = new Animation( 2, true );
		idle.frames( frames, 1, 1, 0 );
		
		run = new Animation( 5, true );
		run.frames( frames, 1, 0 );
		
		attack = new Animation( 7/ SPDSettings.speeder2(), false );
		attack.frames( frames, 1, 4, 5 );
		
		die = new Animation( 11, false );
		die.frames( frames, 6, 7, 8, 9 );

		dance = new Animation( 7, true );
		dance.frames( frames, 2, 4, 3 );

		play( idle );
	}

	public void dance(int pos){
		turnTo(ch.pos, pos);
		play(dance);
	}

	@Override
	public void die() {
		super.die();
		if (Dungeon.level.heroFOV[ch.pos]) {
			emitter().burst( Speck.factory( Speck.BONE ), 3 );
		}
	}

	@Override
	public int blood() {
		return 0xFFcccccc;
	}
}
