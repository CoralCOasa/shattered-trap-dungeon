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
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Prismancer;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Shaman;
import com.shatteredtrap.shatteredpixeldungeon.effects.Lightning;
import com.shatteredtrap.shatteredpixeldungeon.effects.Speck;
import com.watabou.noosa.TextureFilm;

public class PrismancerSprite extends MobSprite {

	public PrismancerSprite() {
		super();
		
		texture( Assets.PRISMANCER );
		
		TextureFilm frames = new TextureFilm( texture, 16, 16 );
		
		idle = new Animation( 2, true );
		idle.frames( frames, 0, 0, 1, 1, 0, 0, 1, 3, 1 );
		
		run = new Animation( 6, true );
		run.frames( frames, 5, 7 );
		
		attack = new Animation( 13/ SPDSettings.speeder2(), false );
		attack.frames( frames, 2, 4, 6, 8 );
		
		zap = attack.clone();
		
		die = new Animation( 9, false );
		die.frames( frames, 9, 10, 11 );
		//
		play( idle );
	}
	
	public void zap( int pos ) {

		parent.add( new Lightning( ch.pos, pos, (Prismancer)ch ) );

		turnTo( ch.pos, pos );
		play( zap );
	}

	@Override
	public void die() {
		super.die();
		if (Dungeon.level.heroFOV[ch.pos]) {
			emitter().burst( Speck.factory( Speck.STAR ), 40 );
			emitter().burst( Speck.factory( Speck.BONE ), 40 );
		}
	}
	public int blood() {
		return 0xE7B9E4;
	}
}
