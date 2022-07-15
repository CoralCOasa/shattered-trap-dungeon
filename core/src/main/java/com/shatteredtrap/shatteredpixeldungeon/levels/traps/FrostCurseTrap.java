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
import com.shatteredtrap.shatteredpixeldungeon.actors.Actor;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.Freezing;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.FrostCurse;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.MagicalFury;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredtrap.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredtrap.shatteredpixeldungeon.effects.Speck;
import com.shatteredtrap.shatteredpixeldungeon.effects.Splash;
import com.shatteredtrap.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredtrap.shatteredpixeldungeon.scenes.GameScene;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

public class FrostCurseTrap extends Trap {

    {
        color = WHITE;
        shape = DIAMOND;
    }

    @Override
    public void activate() {
        Sample.INSTANCE.play(Assets.SND_CURSED, 0.7f, 0.7f, 1.5f);
        for (int i : PathFinder.NEIGHBOURS9) {
            CellEmitter.get(pos + i).burst(ShadowParticle.UP, 2);
            CellEmitter.center(pos + i).burst(Speck.factory(Speck.BLIZZARD), 1);
            Char ch = Actor.findChar(pos + i);
            if (ch != null) {
                Buff.affect(ch, FrostCurse.class).set(10 + Random.Int(21));
            }
        }
    }
}
