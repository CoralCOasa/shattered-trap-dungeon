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

package com.shatteredtrap.shatteredpixeldungeon.levels.traps;

import com.shatteredtrap.shatteredpixeldungeon.Assets;
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.Statistics;
import com.shatteredtrap.shatteredpixeldungeon.actors.Actor;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredtrap.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredtrap.shatteredpixeldungeon.effects.Speck;
import com.shatteredtrap.shatteredpixeldungeon.items.Heap;
import com.shatteredtrap.shatteredpixeldungeon.items.Item;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredtrap.shatteredpixeldungeon.utils.BArray;
import com.shatteredtrap.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class LotteryTrap extends Trap {

	{
		color = YELLOW;
		shape = DIAMOND;
	}

	@Override
	public Trap hide() {
		//this one can't be hidden
		return reveal();
	}

	@Override
	public void activate() {
		Char ch = Actor.findChar(pos);
		if (ch != null && !ch.flying) {
			if (ch instanceof Hero) {
				Sample.INSTANCE.play(Assets.SND_DESCEND);
				int effect = Random.Int( 4 );
				if(effect == 0){
					GLog.p( Messages.get(this, "win") );
					CellEmitter.get(pos).start(Speck.factory(Speck.LIGHT), 0.2f, 3);
					Statistics.goldCollected += Dungeon.gold;
					Dungeon.gold += Dungeon.gold;
				}else if(effect==1){
					GLog.n( Messages.get(this, "lose") );
					CellEmitter.get(pos).start(Speck.factory(Speck.BONE), 0.2f, 3);
					Dungeon.gold=0;
				}else if (effect==2){
					GLog.p( Messages.get(this, "power") );
					CellEmitter.get(pos).start(Speck.factory(Speck.LIGHT), 0.2f, 3);
					Dungeon.hero.STR++;
				}else{
					GLog.n( Messages.get(this, "weaken") );
					CellEmitter.get(pos).start(Speck.factory(Speck.BONE), 0.2f, 3);
					Dungeon.hero.STR--;
				}
			}
		}
	}
}
