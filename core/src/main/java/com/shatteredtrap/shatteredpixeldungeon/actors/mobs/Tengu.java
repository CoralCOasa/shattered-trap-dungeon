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
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.LockedFloor;
import com.shatteredtrap.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredtrap.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredtrap.shatteredpixeldungeon.effects.Speck;
import com.shatteredtrap.shatteredpixeldungeon.items.TomeOfMastery;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.LloydsBeacon;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredtrap.shatteredpixeldungeon.levels.Level;
import com.shatteredtrap.shatteredpixeldungeon.levels.PrisonBossLevel;
import com.shatteredtrap.shatteredpixeldungeon.levels.Terrain;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.GrippingTrap;
import com.shatteredtrap.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredtrap.shatteredpixeldungeon.sprites.TenguSprite;
import com.shatteredtrap.shatteredpixeldungeon.ui.BossHealthBar;
import com.shatteredtrap.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class Tengu extends Mob {
	
	{
		spriteClass = TenguSprite.class;
		
		HP = HT = 120;
		EXP = 20;
		defenseSkill = 20;

		HUNTING = new Hunting();

		flying = true; //doesn't literally fly, but he is fleet-of-foot enough to avoid hazards

		properties.add(Property.BOSS);
	}
	
	@Override
	protected void onAdd() {
		//when he's removed and re-added to the fight, his time is always set to now.
		spend(-cooldown());
		super.onAdd();
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 6, 20 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 20;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 5);
	}

	@Override
	public void damage(int dmg, Object src) {
		
		PrisonBossLevel.State state = ((PrisonBossLevel)Dungeon.level).state();
		
		int hpBracket;
		if (state == PrisonBossLevel.State.FIGHT_START){
			hpBracket = 12;
		} else {
			hpBracket = 20;
		}

		int beforeHitHP = HP;
		super.damage(dmg, src);
		dmg = beforeHitHP - HP;

		LockedFloor lock = Dungeon.hero.buff(LockedFloor.class);
		if (lock != null) {
			int multiple = state == PrisonBossLevel.State.FIGHT_START ? 1 : 4;
			lock.addTime(dmg*multiple);
		}

		//phase 2 of the fight is over
		if (HP == 0 && state == PrisonBossLevel.State.FIGHT_ARENA) {
			//let full attack action complete first
			Actor.add(new Actor() {
				
				{
					actPriority = VFX_PRIO;
				}
				
				@Override
				protected boolean act() {
					Actor.remove(this);
					((PrisonBossLevel)Dungeon.level).progress();
					return true;
				}
			});
			return;
		}
		
		//phase 1 of the fight is over
		if (state == PrisonBossLevel.State.FIGHT_START && HP <= HT/2){
			HP = (HT/2)-1;
			yell(Messages.get(this, "interesting"));
			((PrisonBossLevel)Dungeon.level).progress();
			BossHealthBar.bleed(true);

		//if tengu has lost a certain amount of hp, jump
		} else if (beforeHitHP / hpBracket != HP / hpBracket) {
			jump();
		}
	}

	@Override
	public boolean isAlive() {
		return Dungeon.level.mobs.contains(this); //Tengu has special death rules, see prisonbosslevel.progress()
	}

	@Override
	public void die( Object cause ) {
		
		if (Dungeon.hero.subClass == HeroSubClass.NONE) {
			Dungeon.level.drop( new TomeOfMastery(), pos ).sprite.drop();
		}
		
		GameScene.bossSlain();
		super.die( cause );
		
		Badges.validateBossSlain();

		LloydsBeacon beacon = Dungeon.hero.belongings.getItem(LloydsBeacon.class);
		if (beacon != null) {
			beacon.upgrade();
		}
		
		yell( Messages.get(this, "defeated") );
	}

	@Override
	protected boolean canAttack( Char enemy ) {
		return new Ballistica( pos, enemy.pos, Ballistica.PROJECTILE).collisionPos == enemy.pos;
	}

	//tengu's attack is always visible
	@Override
	protected boolean doAttack(Char enemy) {
		sprite.attack( enemy.pos );
		spend( attackDelay() );
		return false;
	}

	private void jump() {
		
		Level level = Dungeon.level;
		
		//incase tengu hasn't had a chance to act yet
		if (fieldOfView == null || fieldOfView.length != Dungeon.level.length()){
			fieldOfView = new boolean[Dungeon.level.length()];
			Dungeon.level.updateFieldOfView( this, fieldOfView );
		}
		
		if (enemy == null) enemy = chooseEnemy();
		if (enemy == null) return;

		int newPos;
		//if we're in phase 1, want to warp around within the room
		if (((PrisonBossLevel)Dungeon.level).state() == PrisonBossLevel.State.FIGHT_START) {
			
			//place new traps
			int tries;
			for (int i=0; i < 4; i++) {
				int trapPos;
				tries = 15;
				do {
					trapPos = Random.Int( level.length() );
				} while (tries-- > 0 && level.map[trapPos] != Terrain.INACTIVE_TRAP
						&& level.map[trapPos] != Terrain.TRAP);
				
				if (level.map[trapPos] == Terrain.INACTIVE_TRAP) {
					level.setTrap( new GrippingTrap().reveal(), trapPos );
					Level.set( trapPos, Terrain.TRAP );
					ScrollOfMagicMapping.discover( trapPos );
				}
			}
			
			tries = 50;
			do {
				newPos = Random.IntRange(3, 7) + 32*Random.IntRange(26, 30);
			} while ( (level.adjacent(newPos, enemy.pos) || Actor.findChar(newPos) != null)
					&& --tries > 0);
			if (tries <= 0) return;

		//otherwise go wherever, as long as it's a little bit away
		} else {
			Guard test = new Guard();
			Shaman test2 = new Shaman();
			do {
				test.pos = Random.Int(level.length());
			} while (
					level.solid[test.pos] ||
							level.distance(test.pos, enemy.pos) < 8 ||
							Actor.findChar(test.pos) != null);
			do {
				test2.pos = Random.Int(level.length());
			} while (
					level.solid[test2.pos] ||
							level.distance(test2.pos, enemy.pos) < 8 ||
							Actor.findChar(test2.pos) != null);
			do {
				newPos = Random.Int(level.length());
			} while (
					level.solid[newPos] ||
							level.distance(newPos, enemy.pos) < 8 ||
							Actor.findChar(newPos) != null);
			GameScene.add( test );
			GameScene.add( test2 );
		}

		for (Mob mob : Dungeon.level.mobs) {
			if (mob instanceof Guard || mob instanceof Shaman) {
				mob.aggro( enemy );
			}
		}

		if (level.heroFOV[pos]) CellEmitter.get( pos ).burst( Speck.factory( Speck.WOOL ), 6 );

		sprite.move( pos, newPos );
		move( newPos );
		
		if (level.heroFOV[newPos]) CellEmitter.get( newPos ).burst( Speck.factory( Speck.WOOL ), 6 );
		Sample.INSTANCE.play( Assets.SND_PUFF );
		
		//spend( 1 / speed() );
	}

	@Override
	public void notice() {
		super.notice();
		if (!BossHealthBar.isAssigned()) {
			BossHealthBar.assignBoss(this);
			if (HP <= HT/2) BossHealthBar.bleed(true);
			if (HP == HT) {
				yell(Messages.get(this, "notice_mine", Dungeon.hero.givenName()));
				for (Char ch : Actor.chars()){
					if (ch instanceof DriedRose.GhostHero){
						GLog.n("\n");
						((DriedRose.GhostHero) ch).sayBoss();
					}
				}
			} else {
				yell(Messages.get(this, "notice_face", Dungeon.hero.givenName()));
			}
		}
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		BossHealthBar.assignBoss(this);
		if (HP <= HT/2) BossHealthBar.bleed(true);
	}

	//tengu is always hunting
	private class Hunting extends Mob.Hunting{

		@Override
		public boolean act(boolean enemyInFOV, boolean justAlerted) {
			enemySeen = enemyInFOV;
			if (enemyInFOV && !isCharmedBy( enemy ) && canAttack( enemy )) {

				return doAttack( enemy );

			} else {

				if (enemyInFOV) {
					target = enemy.pos;
				} else {
					chooseEnemy();
					if (enemy != null) {
						target = enemy.pos;
					}
				}

				spend( TICK );
				return true;

			}
		}
	}
}
