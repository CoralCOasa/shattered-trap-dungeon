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
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.Electricity;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredtrap.shatteredpixeldungeon.effects.Pushing;
import com.shatteredtrap.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredtrap.shatteredpixeldungeon.effects.particles.SparkParticle;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.brews.ShockingBrew;
import com.shatteredtrap.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredtrap.shatteredpixeldungeon.sprites.RatSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ShockerBlockSprite;
import com.shatteredtrap.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class ShockerBlock extends Mob {

	{
		spriteClass = ShockerBlockSprite.class;
		HP = HT = 58;
		defenseSkill = 13;
		flying = true;
		EXP = 9;
		maxLvl = 17;
		properties.add(Property.INORGANIC);
		loot = new ShockingBrew();
		lootChance = 0.1f;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 8, 20 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 9001;
	}

    @Override
    protected float attackDelay() {
        return super.attackDelay()*0.3334f;
    }

	@Override
	public int attackProc( Char enemy, int damage ) {
		if (Dungeon.level.water[enemy.pos] && !enemy.flying) {
			int dm = Random.NormalIntRange( 4, 11 );
			if(enemy==Dungeon.hero){
				Camera.main.shake( 3, 0.5f );
			}
			enemy.damage( dm, new ToughGhost.Damamdawawd() );
			enemy.sprite.centerEmitter().burst( SparkParticle.FACTORY, dm );
			Sample.INSTANCE.play( Assets.SND_LIGHTNING );
			if (!enemy.isAlive() && enemy == Dungeon.hero) {
				Dungeon.fail( getClass() );
				GLog.n( "You were killed by electricity..." );
			}
		}

		for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
			int ofs = PathFinder.NEIGHBOURS8[i];
			if (enemy.pos - pos == ofs) {
				int newPos = enemy.pos + ofs;
				if ((Dungeon.level.passable[newPos] || Dungeon.level.avoid[newPos])
						&& Actor.findChar( newPos ) == null) {

					Actor.addDelayed( new Pushing( enemy, enemy.pos, newPos ), -1 );

					enemy.pos = newPos;
					Dungeon.level.press( newPos, enemy );

				}
				break;
			}
		}
		damage = super.attackProc( enemy, damage );
		return damage;
	}

	@Override
	public void die( Object cause ) {
		GameScene.add(Blob.seed(pos, 7, Electricity.class));
		super.die(cause);
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 10);
	}

}
