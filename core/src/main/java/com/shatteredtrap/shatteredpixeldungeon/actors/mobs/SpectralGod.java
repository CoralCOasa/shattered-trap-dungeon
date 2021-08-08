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
import com.shatteredtrap.shatteredpixeldungeon.actors.Actor;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Adrenaline;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Bless;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredtrap.shatteredpixeldungeon.effects.Flare;
import com.shatteredtrap.shatteredpixeldungeon.items.Generator;
import com.shatteredtrap.shatteredpixeldungeon.items.Item;
import com.shatteredtrap.shatteredpixeldungeon.items.RatDust;
import com.shatteredtrap.shatteredpixeldungeon.items.SpectralGem;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.enchantments.Grim;
import com.shatteredtrap.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.SpectralGodSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.WarlockSprite;
import com.shatteredtrap.shatteredpixeldungeon.ui.BossHealthBar;
import com.shatteredtrap.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class SpectralGod extends Mob implements Callback {
	
	private static final float TIME_TO_ZAP	= 1f;
	boolean greeting = false;
	{
		spriteClass = SpectralGodSprite.class;
		
		HP = HT = 330;
		defenseSkill = 27;
		
		EXP = 30;
		maxLvl = 30;
		baseSpeed = 0.666667f;
		loot = new SpectralGem();
		lootChance = 1f;

		properties.add(Property.BOSS);
	}
	
	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 26, 44 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 38;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 15);
	}
	
	@Override
	protected boolean canAttack( Char enemy ) {
		return new Ballistica( pos, enemy.pos, Ballistica.MAGIC_BOLT).collisionPos == enemy.pos && enemy.buff(Frost.class) == null;
	}
	
	protected boolean doAttack( Char enemy ) {

		if (Dungeon.level.adjacent( pos, enemy.pos )) {
			
			return super.doAttack( enemy );
			
		} else {
			
			boolean visible = fieldOfView[pos] || fieldOfView[enemy.pos];
			if (visible) {
				sprite.zap( enemy.pos );
			} else {
				zap();
			}
			
			return !visible;
		}
	}

	@Override
	public int attackProc( Char enemy, int damage ) {
		damage = super.attackProc( enemy, damage );
		Ballistica trajectory = new Ballistica(pos, enemy.pos, Ballistica.STOP_TARGET);
		trajectory = new Ballistica(trajectory.collisionPos, trajectory.path.get(trajectory.path.size()-1), Ballistica.PROJECTILE);
		WandOfBlastWave.throwChar(enemy, trajectory, 3);

		return damage;
	}
	
	//used so resistances can differentiate between melee and magical attacks
	public static class DarkBolt{}
	
	private void zap() {
		spend( TIME_TO_ZAP );
		
		if (hit( this, enemy, true )) {
			int dmg = Random.Int( 14, 22 );
			enemy.damage( dmg, new DarkBolt() );
			Buff.prolong( enemy, Frost.class, 5 );
			
			if (!enemy.isAlive() && enemy == Dungeon.hero) {
				Dungeon.fail( getClass() );
				GLog.n( "You were killed by the frost bolt..." );
			}
		} else {
			enemy.sprite.showStatus( CharSprite.NEUTRAL,  enemy.defenseVerb() );
		}
	}

	@Override
	public void notice() {
		super.notice();
		if (!greeting) {
			greeting = true;
			yell("Those who seek to alter the universe will be obliterated!"); }
			new Flare( 5, 32 ).color( 0xC4D0FF, true ).show( this.sprite, 5f );
			new Flare( 3, 36 ).color( 0xC4D0FF, true ).show( this.sprite, 3f );
			new Flare( 4, 40 ).color( 0xC4D0FF, true ).show( this.sprite, 4f );
			for (Mob mob : Dungeon.level.mobs) {
				Buff.prolong( mob, Bless.class, 10 );
				Buff.prolong( mob, Adrenaline.class, 10 );
				mob.aggro( Dungeon.hero );
			}
	}

	public void onZapComplete() {
		zap();
		next();
	}

	private final String GREETING = "greeting";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(GREETING, greeting);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		greeting = bundle.getBoolean(GREETING);
	}

	@Override
	public void call() {
		next();
	}

}
