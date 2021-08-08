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
import com.shatteredtrap.shatteredpixeldungeon.items.Item;
import com.watabou.noosa.TextureFilm;
import com.watabou.utils.Callback;

public class DartSpitterSprite extends MobSprite {

	private int cellToAttack;

	public DartSpitterSprite() {
		super();
		
		texture( Assets.DARTSPITTER );
		
		TextureFilm frames = new TextureFilm( texture, 16, 16 );
		
		idle = new Animation( 2, true );
		idle.frames( frames, 0, 1 );
		
		run = new Animation( 10, true );
		run.frames( frames, 0, 1, 2, 3 );
		
		attack = new Animation( 6/ SPDSettings.speeder2(), false );
		attack.frames( frames, 4, 7 );
		
		zap = attack.clone();
		
		die = new Animation( 12, false );
		die.frames( frames, 7, 4, 5, 5, 6 );
		
		play( idle );
	}
	
	@Override
	public int blood() {
		return 0xFF44FF22;
	}
	
	@Override
	public void attack( int cell ) {
		if (!Dungeon.level.adjacent( cell, ch.pos )) {
			
			cellToAttack = cell;
			turnTo( ch.pos , cell );
			play( zap );
			
		} else {
			
			super.attack( cell );
			
		}
	}
	
	@Override
	public void onComplete( Animation anim ) {
		if (anim == zap) {
			idle();
			
			((MissileSprite)parent.recycle( MissileSprite.class )).
			reset( ch.pos, cellToAttack, new ScorpioShot(), new Callback() {
				@Override
				public void call() {
					ch.onAttackComplete();
				}
			} );
		} else {
			super.onComplete( anim );
		}
	}
	
	public class ScorpioShot extends Item {
		{
			image = ItemSpriteSheet.ALCH_PAGE;
		}
	}
}
