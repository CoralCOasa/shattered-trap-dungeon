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

public class OrcSprite extends MobSprite {

	public OrcSprite() {
		super();
		
		texture( Assets.ORC );
		
		TextureFilm frames = new TextureFilm( texture, 16, 16 );
		
		idle = new Animation( 2, true );
		idle.frames( frames, 1, 1, 1, 0, 0, 1, 1, 0 );
		
		run = new Animation( 7, true );
		run.frames( frames, 2, 0, 1 );
		
		attack = new Animation( 10 / SPDSettings.speeder2(), false );
		attack.frames( frames, 0, 3, 4, 3 );
		
		die = new Animation( 6, false );
		die.frames( frames, 4, 5, 6 );
		
		play( idle );
	}
}
