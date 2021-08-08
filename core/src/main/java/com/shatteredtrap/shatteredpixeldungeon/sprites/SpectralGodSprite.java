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
import com.shatteredtrap.shatteredpixeldungeon.SPDSettings;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.SpectralGod;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Warlock;
import com.shatteredtrap.shatteredpixeldungeon.effects.MagicMissile;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class SpectralGodSprite extends MobSprite {

	public SpectralGodSprite() {
		super();
		
		texture( Assets.GOD );
		
		TextureFilm frames = new TextureFilm( texture, 20, 20 );
		
		idle = new Animation( 2, true );
		idle.frames( frames, 0 );
		
		run = new Animation( 2, true );
		run.frames( frames, 0 );
		
		attack = new Animation( 4/ SPDSettings.speeder2(), false );
		attack.frames( frames, 1, 2 );
		
		zap = attack.clone();
		
		die = new Animation( 3, false );
		die.frames( frames, 3, 4, 5, 6, 7 );
		
		play( idle );
	}
	
	public void zap( int cell ) {
		
		turnTo( ch.pos , cell );
		play( zap );

		MagicMissile.boltFromChar( parent,
				MagicMissile.FROST,
				this,
				cell,
				new Callback() {
					@Override
					public void call() {
						((SpectralGod)ch).onZapComplete();
					}
				} );
		Sample.INSTANCE.play( Assets.SND_ZAP );
	}
	
	@Override
	public void onComplete( Animation anim ) {
		if (anim == zap) {
			idle();
		}
		super.onComplete( anim );
	}
}
