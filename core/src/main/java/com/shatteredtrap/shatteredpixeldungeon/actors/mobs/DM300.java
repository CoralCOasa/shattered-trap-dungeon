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
import com.shatteredtrap.shatteredpixeldungeon.Badges;
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.Actor;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Adrenaline;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.LockedFloor;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredtrap.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredtrap.shatteredpixeldungeon.effects.Pushing;
import com.shatteredtrap.shatteredpixeldungeon.effects.Speck;
import com.shatteredtrap.shatteredpixeldungeon.effects.particles.ElmoParticle;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.LloydsBeacon;
import com.shatteredtrap.shatteredpixeldungeon.items.keys.SkeletonKey;
import com.shatteredtrap.shatteredpixeldungeon.items.quest.MetalShard;
import com.shatteredtrap.shatteredpixeldungeon.levels.Level;
import com.shatteredtrap.shatteredpixeldungeon.levels.Terrain;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredtrap.shatteredpixeldungeon.sprites.DM300Sprite;
import com.shatteredtrap.shatteredpixeldungeon.ui.BossHealthBar;
import com.shatteredtrap.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class DM300 extends Mob {
	boolean goblinMode = false;
	{
		spriteClass = DM300Sprite.class;
		
		HP = HT = 200;
		EXP = 30;
		defenseSkill = 18;
		

		properties.add(Property.BOSS);
		properties.add(Property.INORGANIC);
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 20, 25 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 28;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 10);
	}
	
	@Override
	public boolean act() {
		
		GameScene.add( Blob.seed( pos, 30, ToxicGas.class ) );

		if(HP<=HT/2 && !goblinMode){
			goblinMode = true;
			yell("Goblin protocol active!");
			Buff.affect( this, Terror.class, 10f+Random.Int(6));
			Buff.affect( this, Adrenaline.class, 5f );

			Goblin test = new Goblin();
			test.state = test.WANDERING;
			do {
				test.pos = Random.Int(Dungeon.level.length());
			} while (
					Dungeon.level.solid[test.pos] ||
							Dungeon.level.distance(test.pos, enemy.pos) < 8 ||
							Actor.findChar(test.pos) != null);
			GameScene.add( test );

			Goblin test2 = new Goblin();
			test2.state = test2.WANDERING;
			do {
				test2.pos = Random.Int(Dungeon.level.length());
			} while (
					Dungeon.level.solid[test2.pos] ||
							Dungeon.level.distance(test2.pos, enemy.pos) < 8 ||
							Actor.findChar(test2.pos) != null);
			GameScene.add( test2 );

			Goblin test3 = new Goblin();
			test3.state = test3.WANDERING;
			do {
				test3.pos = Random.Int(Dungeon.level.length());
			} while (
					Dungeon.level.solid[test3.pos] ||
							Dungeon.level.distance(test3.pos, enemy.pos) < 8 ||
							Actor.findChar(test3.pos) != null);
			GameScene.add( test3 );
		}

		return super.act();
	}
	
	@Override
	public void move( int step ) {
		super.move( step );
		
		if (Dungeon.level.map[step] == Terrain.INACTIVE_TRAP && HP < HT) {
			
			HP += Random.Int( 1, HT - HP );
			sprite.emitter().burst( ElmoParticle.FACTORY, 5 );
			
			if (Dungeon.level.heroFOV[step] && Dungeon.hero.isAlive()) {
				GLog.n( Messages.get(this, "repair") );
			}
		}
		
		int[] cells = {
			step-1, step+1, step-Dungeon.level.width(), step+Dungeon.level.width(),
			step-1-Dungeon.level.width(),
			step-1+Dungeon.level.width(),
			step+1-Dungeon.level.width(),
			step+1+Dungeon.level.width()
		};
		int cell = cells[Random.Int( cells.length )];
		
		if (Dungeon.level.heroFOV[cell]) {
			CellEmitter.get( cell ).start( Speck.factory( Speck.ROCK ), 0.07f, 10 );
			Camera.main.shake( 3, 0.7f );
			Sample.INSTANCE.play( Assets.SND_ROCKS );
			
			if (Dungeon.level.water[cell]) {
				GameScene.ripple( cell );
			} else if (Dungeon.level.map[cell] == Terrain.EMPTY) {
				Level.set( cell, Terrain.EMPTY_DECO );
				GameScene.updateMap( cell );
			}
		}

		Char ch = Actor.findChar( cell );
		if (ch != null && ch != this) {
			Buff.prolong( ch, Paralysis.class, 2 );
		}
	}

	@Override
	public void damage(int dmg, Object src) {
		super.damage(dmg, src);
		LockedFloor lock = Dungeon.hero.buff(LockedFloor.class);
		if (lock != null && !isImmune(src.getClass())) lock.addTime(dmg*1.5f);
	}

	@Override
	public void die( Object cause ) {
		
		super.die( cause );
		
		GameScene.bossSlain();
		Dungeon.level.drop( new SkeletonKey( Dungeon.depth  ), pos ).sprite.drop();
		
		//60% chance of 2 shards, 30% chance of 3, 10% chance for 4. Average of 2.5
		int shards = Random.chances(new float[]{0, 0, 6, 3, 1});
		for (int i = 0; i < shards; i++){
			int ofs;
			do {
				ofs = PathFinder.NEIGHBOURS8[Random.Int(8)];
			} while (!Dungeon.level.passable[pos + ofs]);
			Dungeon.level.drop( new MetalShard(), pos + ofs ).sprite.drop( pos );
		}
		
		Badges.validateBossSlain();

		LloydsBeacon beacon = Dungeon.hero.belongings.getItem(LloydsBeacon.class);
		if (beacon != null) {
			beacon.upgrade();
		}
		
		yell( Messages.get(this, "defeated") );
	}

	@Override
	public int defenseProc( Char enemy, int damage ) {

		for (Mob mob : Dungeon.level.mobs) {
			if (mob instanceof Bat || mob instanceof Spinner || mob instanceof DartSpitter|| mob instanceof Brute) {
				mob.aggro( enemy );
			}
		}

		return super.defenseProc(enemy, damage);
	}

	@Override
	public void notice() {
		super.notice();
		if (!BossHealthBar.isAssigned()) {
			BossHealthBar.assignBoss(this);
			yell(Messages.get(this, "notice"));
			for (Char ch : Actor.chars()){
				if (ch instanceof DriedRose.GhostHero){
					GLog.n("\n");
					((DriedRose.GhostHero) ch).sayBoss();
				}
			}
		}
	}
	
	{
		immunities.add( ToxicGas.class );
		//immunities.add( Terror.class );
	}

	private final String GOBLINMODE = "goblinMode";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(GOBLINMODE, goblinMode);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		goblinMode = bundle.getBoolean(GOBLINMODE);
		BossHealthBar.assignBoss(this);
	}
}
