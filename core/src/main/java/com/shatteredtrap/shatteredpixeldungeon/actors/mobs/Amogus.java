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
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Marked;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Terror;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredtrap.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredtrap.shatteredpixeldungeon.effects.Speck;
import com.shatteredtrap.shatteredpixeldungeon.items.food.MysteryMeat;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.TreasureBag;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.enchantments.Grim;
import com.shatteredtrap.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredtrap.shatteredpixeldungeon.sprites.AmogusSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.RatSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class Amogus extends Mob {

	{
		spriteClass = AmogusSprite.class;
		HP = HT = 150;
		defenseSkill = 19;
		
		maxLvl = 30;
		EXP = 15;
		baseSpeed = 2f;
		//properties.add(Property.MINIBOSS);

		loot = new TreasureBag();
		lootChance = 1f;
	}

	@Override
	protected boolean act() {
		if(Random.Int(30)==1 && this.buff(Terror.class) == null){
			blink( Dungeon.hero.pos );
		}
		return super.act();
	}

	private void blink( int target ) {

		int cell = target;
		ArrayList<Integer> candidates = new ArrayList<>();
			for (int n : PathFinder.NEIGHBOURS8) {
				cell = cell + n;
				if (Dungeon.level.passable[cell] && Actor.findChar( cell ) == null) {
					candidates.add( cell );
				}
			}
		if (candidates.size() > 0) {
			cell = Random.element(candidates);
			state = HUNTING;
			ScrollOfTeleportation.silencedAppear( this, cell );
			sprite.showStatus( CharSprite.WARNING, "VENT" );
		}else {
			return;
		}

		if (Dungeon.level.heroFOV[pos]) CellEmitter.get( pos ).burst( Speck.factory( Speck.WOOL ), 6 );
		ScrollOfTeleportation.silencedAppear( this, cell );
		if (Dungeon.level.heroFOV[pos]) {
			Sample.INSTANCE.play(Assets.SND_PUFF);
		}
		if (Dungeon.level.heroFOV[pos]) CellEmitter.get( pos ).burst( Speck.factory( Speck.WOOL ), 6 );
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 20, 35 );
	}

	@Override
	public int attackSkill( Char target ) {
		return 32;
	}

	@Override
	public int attackProc(Char enemy, int damage) {
		damage = super.attackProc( enemy, damage );
		Buff.affect(enemy, Vertigo.class, 10);
		Buff.affect(this, Terror.class, 30+Random.Int(91));
		return damage;
	}

	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 10);
	}

}
