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

public class BombSpider2Sprite extends MobSprite {
	private Animation explode;
	public BombSpider2Sprite() {
		super();
		
		texture( Assets.BOMBSPIDER );
		
		TextureFilm frames = new TextureFilm( texture, 16, 16 );
		
		idle = new Animation( 2, true );
		idle.frames( frames, 0+8, 0+8, 1+8 );
		
		run = new Animation( 8, true );
		run.frames( frames, 0+8, 2+8, 3+8 );
		
		attack = new Animation( 6/ SPDSettings.speeder2(), false );
		attack.frames( frames, 3+8, 6+8 );
		
		die = new Animation( 2, false );
		die.frames( frames, 7+8 );

		explode = new Animation( 5, true );
		explode.frames( frames, 4+8, 5+8 );

		play( idle );
	}

	public void explode(int pos){
		turnTo(ch.pos, pos);
		play(explode);
	}

	@Override
	public int blood() {
		return 0x799DD6;
	}
}
