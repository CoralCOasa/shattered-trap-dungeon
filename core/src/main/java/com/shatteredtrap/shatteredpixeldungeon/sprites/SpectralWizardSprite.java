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
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.SpectralWizard;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Warlock;
import com.shatteredtrap.shatteredpixeldungeon.effects.MagicMissile;
import com.watabou.noosa.TextureFilm;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;

public class SpectralWizardSprite extends MobSprite {

	public SpectralWizardSprite() {
		super();
		
		texture( Assets.SPECTRAL );
		
		TextureFilm frames = new TextureFilm( texture, 16, 16 );
		
		idle = new Animation( 2, true );
		idle.frames( frames, 0+4, 0+4, 0+4, 1+4, 0+4, 0+4, 1+4 );
		
		run = new Animation( 7, true );
		run.frames( frames, 0+4, 2+4, 0+4, 2+4, 1+4, 2+4 );
		
		attack = new Animation( 8 / SPDSettings.speeder2(), false );
		attack.frames( frames, 3+4, 2+4, 3+4 );

		zap = attack.clone();

		die = new Animation( 4, false );
		die.frames( frames, 12, 13, 14 );
		
		play( idle );
	}

	public void zap( int cell ) {

		turnTo( ch.pos , cell );
		play( zap );

		MagicMissile.boltFromChar( parent,
				MagicMissile.MAGIC_MISSILE,
				this,
				cell,
				new Callback() {
					@Override
					public void call() {
						((SpectralWizard)ch).onZapComplete();
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
