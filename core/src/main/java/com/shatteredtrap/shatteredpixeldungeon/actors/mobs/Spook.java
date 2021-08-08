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
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.Blizzard;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.StenchGas;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.TreasureBag;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.enchantments.Grim;
import com.shatteredtrap.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredtrap.shatteredpixeldungeon.sprites.RatSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.SpookSprite;
import com.watabou.utils.Random;

public class Spook extends Mob {

	{
		spriteClass = SpookSprite.class;
		HP = HT = 130;
		defenseSkill = 14;
		EXP = 12;
		maxLvl = 30;
		baseSpeed = 0.75f;
		//properties.add(Property.MINIBOSS);
		immunities.add(Frost.class);
		immunities.add(Chill.class);
		loot = new TreasureBag();
		lootChance = 1f;
	}

	@Override
	protected boolean act() {
		GameScene.add(Blob.seed(pos, 40, Blizzard.class));
		return super.act();
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 18, 32 );
	}

	@Override
	protected float attackDelay() {
		return super.attackDelay()*2f;
	}

	@Override
	public int attackSkill( Char target ) {
		return 22;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 7);
	}

}
