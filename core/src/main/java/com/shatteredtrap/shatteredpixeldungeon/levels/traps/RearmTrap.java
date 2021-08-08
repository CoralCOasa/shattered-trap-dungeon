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

package com.shatteredtrap.shatteredpixeldungeon.levels.traps;

import com.shatteredtrap.shatteredpixeldungeon.Assets;
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.ParalyticGas;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.Regrowth;
import com.shatteredtrap.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredtrap.shatteredpixeldungeon.effects.Flare;
import com.shatteredtrap.shatteredpixeldungeon.effects.particles.SmokeParticle;
import com.shatteredtrap.shatteredpixeldungeon.effects.particles.SparkParticle;
import com.shatteredtrap.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredtrap.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class RearmTrap extends Trap{

	{
		color = YELLOW;
		shape = GRILL;
	}

	@Override
	public void activate() {

		for (int i = 0; i < Dungeon.level.length(); i++){
			Trap t = Dungeon.level.traps.get(i);
			if (t!=null){
				t.arm();
			}
		}
		Sample.INSTANCE.play( Assets.SND_EVOKE,1f,1f,0.8f );
		CellEmitter.get(pos).burst(SmokeParticle.FACTORY, 8);
		CellEmitter.get(pos).burst( SparkParticle.FACTORY, 2 );
		GLog.w("Traps in the floor have been rearmed!");
		disarm();
	}
}
