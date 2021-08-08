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

public class SlaverBlueSprite extends MobSprite {

	public SlaverBlueSprite() {
		super();
		
		texture( Assets.SLAVER );
		
		TextureFilm frames = new TextureFilm( texture, 20, 20 );
		
		idle = new Animation( 1, true );
		idle.frames( frames, 0 );
		
		run = new Animation( 4, true );
		run.frames( frames, 0, 3 );
		
		attack = new Animation( 8 / SPDSettings.speeder2(), false );
		attack.frames( frames, 1,0,1 );
		
		die = new Animation( 10, true );
		die.frames( frames, 0,2,0,3 );
		
		play( idle );
	}
}
