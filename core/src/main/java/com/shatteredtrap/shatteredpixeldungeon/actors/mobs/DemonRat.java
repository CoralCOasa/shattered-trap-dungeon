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
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.ConfusionGas;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.CorrosiveGas;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.DreamGas;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.Inferno;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.ParalyticGas;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.SmokeScreen;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.StormCloud;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Bleeding;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Cripple;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Roots;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Slow;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredtrap.shatteredpixeldungeon.levels.features.Chasm;
import com.shatteredtrap.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredtrap.shatteredpixeldungeon.sprites.DemonRatSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.RatSprite;
import com.watabou.utils.Random;

public class DemonRat extends Mob {

	{
		spriteClass = DemonRatSprite.class;
		
		HP = HT = 97;
		defenseSkill = 26;

		EXP = 13;
		maxLvl = 25;

		properties.add(Property.DEMONIC);
	}

	@Override
	public int attackProc(Char enemy, int damage) {
		int effect = Random.Int(11);
		damage = super.attackProc( enemy, damage );
		switch(effect){
			case 1:
				Buff.affect(enemy, Poison.class).set(Random.Int(10, 16) );
				break;
			case 2:
				Buff.prolong( enemy, Cripple.class, 6+Random.Int(7) );
				break;
			case 3:
				Buff.affect( enemy, Burning.class ).reignite( enemy );
				break;
			case 4:
				Buff.prolong( enemy, Slow.class, 3+Random.Int(5) );
				break;
			case 5:
				Buff.prolong( enemy, MagicImmune.class, 5+Random.Int(12) );
				break;
			case 6:
				Buff.prolong( enemy, Roots.class, 2+Random.Int(7) );
				break;
			case 7:
				Buff.affect( enemy, Bleeding.class ).set( Math.round(damage*0.6f) );
				break;
			case 8:
				Buff.prolong( enemy, Blindness.class, 5+Random.Int(10) );
				break;
			case 9:
				Buff.affect( enemy, Ooze.class ).set( 30f );
				break;
			case 10:
				Buff.prolong( enemy, Weakness.class, 15+Random.Int(40) );
				break;
		}

		return damage;
	}

	@Override
	public void die( Object cause ) {
		if (cause != Chasm.class) {
			int rng = Random.Int(8);
			switch (rng) {
				case 0:
					GameScene.add(Blob.seed(pos, 160, Inferno.class));
					break;
				case 1:
					GameScene.add(Blob.seed(pos, 160, Blizzard.class));
					break;
				case 2:
					GameScene.add(Blob.seed(pos, 160, SmokeScreen.class));
					break;
				case 3:
					GameScene.add(Blob.seed(pos, 160, StormCloud.class));
					break;
				case 4:
					CorrosiveGas corrosiveGas = Blob.seed(pos, 160, CorrosiveGas.class);
					corrosiveGas.setStrength(5);
					GameScene.add(corrosiveGas);
					break;
				case 5:
					GameScene.add(Blob.seed(pos, 160, ParalyticGas.class));
					break;
				case 6:
					GameScene.add(Blob.seed(pos, 160, ToxicGas.class));
					break;
				case 7:
					GameScene.add(Blob.seed(pos, 160, ConfusionGas.class));
					break;
				case 8:
					GameScene.add(Blob.seed(pos, 160, DreamGas.class));
					break;
			}
		}
		super.die(cause);
	}


	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 28, 38 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 38;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 12);
	}
}
