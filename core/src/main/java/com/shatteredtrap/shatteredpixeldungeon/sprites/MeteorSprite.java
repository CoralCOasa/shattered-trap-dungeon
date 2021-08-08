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
import com.watabou.noosa.TextureFilm;

public class MeteorSprite extends MobSprite {

	public MeteorSprite() {
		super();
		
		texture( Assets.METEOR );
		
		TextureFilm frames = new TextureFilm( texture, 16, 16 );
		
		idle = new Animation( 2, true );
		idle.frames( frames, 1, 1, 9, 1, 1, 0 );
		
		run = new Animation( 5, true );
		run.frames( frames, 1, 9, 8, 9 );
		
		attack = new Animation( 16/ SPDSettings.speeder2(), false );
		attack.frames( frames, 1, 2, 3, 3 );
		
		die = new Animation( 6, false );
		die.frames( frames, 4, 5, 6, 7, 10 );
		
		play( idle );
	}
	
	@Override
	public int blood() {
		return 0x11586526;
	}
}
