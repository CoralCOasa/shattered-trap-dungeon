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

import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredtrap.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredtrap.shatteredpixeldungeon.items.Generator;
import com.shatteredtrap.shatteredpixeldungeon.items.KindOfWeapon;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfTerror;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Gauntlet;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Gloves;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.sprites.RatSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ToughGhostSprite;
import com.shatteredtrap.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Random;

public class ToughGhost extends Mob {

	{
		spriteClass = ToughGhostSprite.class;
		HP = HT = 18;
		defenseSkill = 9999;
		flying = true;
		properties.add(Property.UNDEAD);
		maxLvl = 10;
		EXP = 5;

		loot = new ScrollOfTerror();
		lootChance = 0.16f;
	}

	public static class Damamdawawd{}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 1, 7 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 13;
	}

	@Override
	public int attackProc( Char enemy, int damage ) {
		int dm = Random.NormalIntRange( 2, 4 );
		enemy.damage( dm, new Damamdawawd() );
		enemy.sprite.emitter().burst(ShadowParticle.UP, dm);
		if (!enemy.isAlive() && enemy == Dungeon.hero) {
			Dungeon.fail( getClass() );
			GLog.n( "You were killed by dark energy..." );
		}
		damage = super.attackProc( enemy, damage );
		return damage;
	}

	@Override
	public int drRoll() {
		return 0;
	}

}
