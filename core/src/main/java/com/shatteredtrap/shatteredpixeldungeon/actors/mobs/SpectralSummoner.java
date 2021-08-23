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
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Adrenaline;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Blindness;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredtrap.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredtrap.shatteredpixeldungeon.effects.Flare;
import com.shatteredtrap.shatteredpixeldungeon.items.RatDust;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredtrap.shatteredpixeldungeon.items.spells.FeatherFall;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredtrap.shatteredpixeldungeon.sprites.SpectralSamuraiSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.SpectralSummonerSprite;
import com.shatteredtrap.shatteredpixeldungeon.ui.BossHealthBar;
import com.shatteredtrap.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public class SpectralSummoner extends Mob {
	//boolean demonArmy = false;
	{
		spriteClass = SpectralSummonerSprite.class;
		HP = HT = 170;
		defenseSkill = 22;
		EXP = 16;
		maxLvl = 30;
		loot = new RatDust();
		lootChance = 1f;
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 22, 34 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 34;
	}

	@Override
	protected float attackDelay() {
		return super.attackDelay()*2f;
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 11);
	}

	@Override
	public int attackProc( Char enemy, int damage ){
		damage = super.attackProc( enemy, damage );
		spawnMob();
		return damage;
	}

	@Override
	public void notice() {
		if (this.buff(FeatherFall.FeatherBuff.class) == null ){
			Buff.prolong( this, FeatherFall.FeatherBuff.class, 999999 );
			for(int i = 0;i<3;i++){
				spawnMob();
			}
		}
	}

	public void spawnMob(){
		Sample.INSTANCE.play( Assets.SND_TELEPORT );
		new Flare( 5, 32 ).color( 0x38B78A, true ).show( this.sprite, 2f );
		Mob test;
		int ii = Random.Int(4);
		if(ii==0){
			test = new DemonRat();
		}else if(ii==1){
			test = new Succubus();
		}else if(ii==2){
			test = new Scorpio();
		}else{
			test = new Eye();
		}
		test.state = test.WANDERING;
		do {
			test.pos = Random.Int(Dungeon.level.length());
		} while (
				Dungeon.level.solid[test.pos] ||
						Dungeon.level.pit[test.pos] ||
						Dungeon.level.distance(test.pos, Dungeon.hero.pos) < 8 ||
						Actor.findChar(test.pos) != null);
		GameScene.add( test );
		Buff.prolong( test, Adrenaline.class, 5 );
		test.beckon(Dungeon.hero.pos);
	}
/*
	private final String DEMONARMY = "demonArmy";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(DEMONARMY, demonArmy);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		demonArmy = bundle.getBoolean(DEMONARMY);

	}
	*/
}
