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
import com.shatteredtrap.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredtrap.shatteredpixeldungeon.effects.Beam;
import com.shatteredtrap.shatteredpixeldungeon.effects.Speck;
import com.shatteredtrap.shatteredpixeldungeon.effects.particles.SparkParticle;
import com.shatteredtrap.shatteredpixeldungeon.items.Generator;
import com.shatteredtrap.shatteredpixeldungeon.levels.features.Chasm;
import com.shatteredtrap.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.PrismancerSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ShamanSprite;
import com.shatteredtrap.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.shatteredtrap.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class Prismancer extends Mob implements Callback {

	private static final float TIME_TO_ZAP	= 2f;
	
	{
		spriteClass = PrismancerSprite.class;
		
		HP = HT = 70;
		defenseSkill = 21;
		
		EXP = 14;
		maxLvl = 25;
		
		loot = Generator.Category.STONE;
		lootChance = 0.667f;

	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 19, 28 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 38;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 12);
	}
	
	@Override
	protected boolean canAttack( Char enemy ) {
		return new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos;
	}
	
	//used so resistances can differentiate between melee and magical attacks
	public static class LightningBolt{}
	
	@Override
	protected boolean doAttack( Char enemy ) {

		if (Dungeon.level.distance( pos, enemy.pos ) <= 1) {
			
			return super.doAttack( enemy );
			
		} else {
			
			boolean visible = fieldOfView[pos] || fieldOfView[enemy.pos];
			if (visible) {
				sprite.zap( enemy.pos );
			}
			
			spend( TIME_TO_ZAP );

			Sample.INSTANCE.play(Assets.SND_RAY);
			ShatteredPixelDungeon.scene().add(new Beam.LightRay(DungeonTilemap.tileCenterToWorld(pos), enemy.sprite.center()));
			
			if (hit( this, enemy, true )) {
				int dmg = Random.NormalIntRange(20, 35);
				enemy.damage( dmg, new LightningBolt() );
				Buff.prolong(enemy, Blindness.class, 5+Random.Int(11));
				enemy.sprite.emitter().burst(Speck.factory(Speck.LIGHT), 12 );
				enemy.sprite.flash();
				
				if (enemy == Dungeon.hero) {
					
					//Camera.main.shake( 2, 0.3f );
					
					if (!enemy.isAlive()) {
						Dungeon.fail( getClass() );
						GLog.n( "You were killed by the prismatic beam..." );
					}
				}
			} else {
				enemy.sprite.showStatus( CharSprite.NEUTRAL,  enemy.defenseVerb() );
			}
			
			return !visible;
		}
	}
	
	@Override
	public void call() {
		next();
	}

	@Override
	public void die( Object cause ) {

		super.die( cause );

		if (cause == Chasm.class) return;

		boolean heroKilled = false;
		for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
			Char ch = findChar( pos + PathFinder.NEIGHBOURS8[i] );
			if (ch != null && ch.isAlive()) {
				int damage = Random.NormalIntRange(22, 34);
				damage = Math.max( 0,  damage - ch.drRoll() );
				ch.damage( damage, this );
				if (ch == Dungeon.hero && !ch.isAlive()) {
					heroKilled = true;
				}
			}
		}

		if (Dungeon.level.heroFOV[pos]) {
			Sample.INSTANCE.play( Assets.SND_SHATTER );
		}

		if (heroKilled) {
			Dungeon.fail( getClass() );
			GLog.n( "You were killed by the explosion of crystal shards..." );
		}
	}


}
