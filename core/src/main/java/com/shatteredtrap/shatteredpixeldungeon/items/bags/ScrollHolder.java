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

package com.shatteredtrap.shatteredpixeldungeon.items.bags;

import com.shatteredtrap.shatteredpixeldungeon.items.Item;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredtrap.shatteredpixeldungeon.items.spells.BeaconOfReturning;
import com.shatteredtrap.shatteredpixeldungeon.items.spells.Spell;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class ScrollHolder extends Bag {

	{
		image = ItemSpriteSheet.HOLDER;
		
		size = 20;
	}
	
	@Override
	public boolean grab( Item item ) {
		return item instanceof Scroll || item instanceof Spell;
	}
	
	@Override
	public void onDetach( ) {
		super.onDetach();
		for (Item item : items) {
			if (item instanceof BeaconOfReturning) {
				((BeaconOfReturning) item).returnDepth = -1;
			}
		}
	}
	
	@Override
	public int price() {
		return 30;
	}

}
