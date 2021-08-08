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
import com.shatteredtrap.shatteredpixeldungeon.actors.Actor;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.StenchGas;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.TreasureBag;
import com.shatteredtrap.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredtrap.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.RatSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.SlaverBossSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class SlaverBoss extends Mob {
	boolean spawned = false;
	{
		spriteClass = SlaverBossSprite.class;
		HP = HT = 60;
		defenseSkill = 8;
		EXP = 6;
		maxLvl = 30;
		loot = new TreasureBag();
		lootChance = 1f;
		//properties.add(Property.MINIBOSS);
	}

	@Override
	protected boolean act() {
		if(!spawned){
			spawned = true;
			SlaverRed test = new SlaverRed();
			SlaverBlue test2 = new SlaverBlue();

			do {
				test.pos = pos + PathFinder.NEIGHBOURS8[Random.Int( 8 )];
				test2.pos = pos + PathFinder.NEIGHBOURS8[Random.Int( 8 )];
			} while (!Dungeon.level.passable[test.pos] || !Dungeon.level.passable[test2.pos] || Actor.findChar( pos ) == null);

			GameScene.add( test );
			GameScene.add( test2 );
		}
		return super.act();
	}

	@Override
	public int defenseProc( Char enemy, int damage ) {

		for (Mob mob : Dungeon.level.mobs) {
			if (mob instanceof SlaverRed || mob instanceof SlaverBlue) {
				mob.aggro( enemy );
			}
		}
		return super.defenseProc(enemy, damage);

	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 3, 10 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 17;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 4);
	}

	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		if (Random.Int( 2 ) == 0) {
			Buff.affect( enemy, Cripple.class, Cripple.DURATION );
		}

		return damage;
	}

	private final String SPAWNED = "spawned";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(SPAWNED, spawned);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		spawned = bundle.getBoolean(SPAWNED);
	}

}
