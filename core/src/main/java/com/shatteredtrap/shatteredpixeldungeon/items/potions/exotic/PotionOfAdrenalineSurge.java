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

package com.shatteredtrap.shatteredpixeldungeon.items.potions.exotic;

import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.AdrenalineSurge;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredtrap.shatteredpixeldungeon.actors.hero.Hero;

public class PotionOfAdrenalineSurge extends ExoticPotion {
	
	{
		initials = 10;
	}
	
	@Override
	public void apply(Hero hero) {
		setKnown();
		Buff.affect(hero, AdrenalineSurge.class).reset(2, 800f);
	}
	
}
