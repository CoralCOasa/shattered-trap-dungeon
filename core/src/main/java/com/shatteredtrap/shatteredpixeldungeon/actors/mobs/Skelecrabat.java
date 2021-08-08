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

package com.shatteredtrap.shatteredpixeldungeon.actors.mobs;

import com.shatteredtrap.shatteredpixeldungeon.Assets;
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.effects.Speck;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.TreasureBag;
import com.shatteredtrap.shatteredpixeldungeon.levels.features.Chasm;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.sprites.RatSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.SkelecrabatSprite;
import com.shatteredtrap.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Skelecrabat extends Mob {

	{
		spriteClass = SkelecrabatSprite.class;
		HP = HT = 200;
		defenseSkill = 27;
		baseSpeed = 2f;
		EXP = 20;
		maxLvl = 30;
		flying = true;
		//properties.add(Property.MINIBOSS);
		loot = new TreasureBag();
		lootChance = 1f;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 28, 42 );
	}

	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		int reg = Math.min( damage, HT - HP );

		if (reg > 0) {
			HP += reg;
			sprite.emitter().burst( Speck.factory( Speck.HEALING ), 1 );
		}

		return damage;
	}

	@Override
	public int attackSkill( Char target ) {
		return 38;
	}

	@Override
	public void die( Object cause ) {

		super.die( cause );

		if (cause == Chasm.class) return;

		boolean heroKilled = false;
		for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
			Char ch = findChar( pos + PathFinder.NEIGHBOURS8[i] );
			if (ch != null && ch.isAlive()) {
				int damage = Random.NormalIntRange(25, 55);
				damage = Math.max( 0,  damage - ch.drRoll() );
				ch.damage( damage, this );
				if (ch == Dungeon.hero && !ch.isAlive()) {
					heroKilled = true;
				}
			}
		}

		if (Dungeon.level.heroFOV[pos]) {
			Sample.INSTANCE.play( Assets.SND_BONES, 1.5f, 1.5f, 0.7f );
		}

		if (heroKilled) {
			Dungeon.fail( getClass() );
			GLog.n( "You were killed by the explosion of bones..." );
		}
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 18);
	}

}
