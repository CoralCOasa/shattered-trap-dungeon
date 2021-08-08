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

package com.shatteredtrap.shatteredpixeldungeon.actors.buffs;

import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.items.Item;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredtrap.shatteredpixeldungeon.ui.BuffIndicator;
import com.watabou.utils.Random;

public class FrostCurse extends FlavourBuff {

	{
		type = buffType.NEGATIVE;
		announced = false;
	}

	public static final float DURATION = 10f;

	@Override
	public int icon() {
		return BuffIndicator.FROST;
	}

	@Override
	public void fx(boolean on) {
		if (on) target.sprite.add( CharSprite.State.MARKED );
		else target.sprite.remove( CharSprite.State.MARKED );
	}

	@Override
	public void detach() {
		if(target.isAlive()) {
			Buff.affect(target, Frost.class, 15 + Random.Int(16));
		}
		super.detach();
	}

	@Override
	public String toString() {
		return Messages.get(this, "name");
	}

	@Override
	public String desc() {
		return Messages.get(this, "desc", dispTurns());
	}
	
}