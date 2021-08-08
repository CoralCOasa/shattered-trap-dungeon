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
import com.shatteredtrap.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.Actor;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Marked;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.PinCushion;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.ToughGhost;
import com.shatteredtrap.shatteredpixeldungeon.effects.Beam;
import com.shatteredtrap.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredtrap.shatteredpixeldungeon.effects.Flare;
import com.shatteredtrap.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredtrap.shatteredpixeldungeon.levels.Level;
import com.shatteredtrap.shatteredpixeldungeon.levels.Terrain;
import com.shatteredtrap.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredtrap.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredtrap.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class MarkingTrap extends Trap {

	{
		color = WHITE;
		shape = LARGE_DOT;
	}

	@Override
	public void trigger() {
		if (Dungeon.level.heroFOV[pos]){
			Sample.INSTANCE.play(Assets.SND_TRAP);
		}
		//this trap is not disarmed by being triggered
		reveal();
		Level.set(pos, Terrain.TRAP);
		activate();
	}

	@Override
	public void activate() {
		Char target = Actor.findChar(pos);
		
		//find the closest char that can be aimed at
		if (target == null){
			for (Char ch : Actor.chars()){
				Ballistica bolt = new Ballistica(pos, ch.pos, Ballistica.PROJECTILE);
				if (bolt.collisionPos == ch.pos &&
						(target == null || Dungeon.level.trueDistance(pos, ch.pos) < Dungeon.level.trueDistance(pos, target.pos))){
					target = ch;
				}
			}
		}
		
		if (target != null) {
			if (Dungeon.level.heroFOV[pos] || Dungeon.level.heroFOV[target.pos]) {
				Sample.INSTANCE.play(Assets.SND_RAY, 1f, 1f, 3f);
				ShatteredPixelDungeon.scene().add(new Beam.LightRay(DungeonTilemap.tileCenterToWorld(pos), target.sprite.center()));
			}
			if (target.buff(Marked.class) != null){
				target.damage(Random.NormalIntRange(Dungeon.depth+10,Dungeon.depth*3+20), new ToughGhost.Damamdawawd() );
				Sample.INSTANCE.play(Assets.SND_DEGRADE, 1f, 1f, 4f);
				Buff.detach(target,Marked.class);
			}else{
				Buff.affect(target, Marked.class, 50+ Random.Int( 31 ));
			}
		}

	}
}
