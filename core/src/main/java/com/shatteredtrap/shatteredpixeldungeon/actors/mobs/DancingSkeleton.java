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
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.items.spells.ReclaimTrap;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.StoneOfIntuition;
import com.shatteredtrap.shatteredpixeldungeon.levels.features.Chasm;
import com.shatteredtrap.shatteredpixeldungeon.sprites.DancingSkeletonSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.RatSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class DancingSkeleton extends Mob {

	private boolean dancing = true;

	{
		spriteClass = DancingSkeletonSprite.class;
		HP = HT = 11;
		defenseSkill = 2;
		maxLvl = 5;
		properties.add(Property.UNDEAD);
		properties.add(Property.INORGANIC);
		loot = new StoneOfIntuition();
		lootChance = 0.14f;
	}

	@Override
	protected boolean act() {
		if(state!=SLEEPING){
		if (dancing) {
			dancing = false;
			spend( 1 );
			((DancingSkeletonSprite)sprite).dance(pos);
			return true;
		}else {
			dancing = true;
			return super.act();
		}}
		return super.act();
	}

	@Override
	public int damageRoll() {
		return Random.NormalIntRange( 2, 7 );
	}
	
	@Override
	public int attackSkill( Char target ) {
		return 11;
	}
	
	@Override
	public int drRoll() {
		return Random.NormalIntRange(0, 2);
	}


	@Override
	public void die( Object cause ) {

		super.die(cause);

		if (cause == Chasm.class) return;

		if (Dungeon.level.heroFOV[pos]) {
			Sample.INSTANCE.play(Assets.SND_BONES);
		}
	}

}
