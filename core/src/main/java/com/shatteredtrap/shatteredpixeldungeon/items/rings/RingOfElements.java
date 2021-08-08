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

package com.shatteredtrap.shatteredpixeldungeon.items.rings;

import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.Electricity;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.ToxicGas;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Charm;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Corrosion;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Ooze;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Poison;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Weakness;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Eye;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.FilthJet;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Flower;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Prismancer;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Shaman;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.SpectralGod;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.SpectralWizard;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.ToughGhost;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Warlock;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Yog;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfDisintegration;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfFireblast;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfFrost;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfLightning;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfLivingEarth;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfMagicMissile;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfPrismaticLight;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfTransfusion;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfWarding;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.DisintegrationTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.GrimTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.MarkingTrap;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;

import java.text.DecimalFormat;
import java.util.HashSet;

public class RingOfElements extends Ring {
	
	public String statsInfo() {
		if (isIdentified()){
			return Messages.get(this, "stats", new DecimalFormat("#.##").format(100f * (1f - Math.pow(0.80f, soloBonus()))));
		} else {
			return Messages.get(this, "typical_stats", new DecimalFormat("#.##").format(20f));
		}
	}
	
	@Override
	protected RingBuff buff( ) {
		return new Resistance();
	}

	//FIXME probably should add wands here
	public static final HashSet<Class> RESISTS = new HashSet<>();
	static {
		RESISTS.add( Burning.class );
		RESISTS.add( Charm.class );
		RESISTS.add( Chill.class );
		RESISTS.add( Frost.class );
		RESISTS.add( Ooze.class );
		RESISTS.add( Paralysis.class );
		RESISTS.add( Poison.class );
		RESISTS.add( Corrosion.class );
		RESISTS.add( Weakness.class );
		
		RESISTS.add( DisintegrationTrap.class );
		RESISTS.add( GrimTrap.class );
		RESISTS.add(MarkingTrap.class);

		RESISTS.add( WandOfBlastWave.class );
		RESISTS.add( WandOfDisintegration.class );
		RESISTS.add( WandOfFireblast.class );
		RESISTS.add( WandOfFrost.class );
		RESISTS.add( WandOfLightning.class );
		RESISTS.add( WandOfLivingEarth.class );
		RESISTS.add( WandOfMagicMissile.class );
		RESISTS.add( WandOfPrismaticLight.class );
		RESISTS.add( WandOfTransfusion.class );
		RESISTS.add( WandOfWarding.Ward.class );
		
		RESISTS.add( ToxicGas.class );
		RESISTS.add( Electricity.class );
		
		RESISTS.add( Shaman.LightningBolt.class );
		RESISTS.add( Warlock.DarkBolt.class );
		RESISTS.add( Eye.DeathGaze.class );
		RESISTS.add( Yog.BurningFist.DarkBolt.class );
		RESISTS.add(Prismancer.LightningBolt.class);
		RESISTS.add(FilthJet.VileBolt.class);
		RESISTS.add(ToughGhost.Damamdawawd.class);
		RESISTS.add(Flower.DarkBolt.class);
		RESISTS.add(SpectralGod.DarkBolt.class);
		RESISTS.add(SpectralWizard.DarkBolt.class);
	}
	
	public static float resist( Char target, Class effect ){
		if (getBonus(target, Resistance.class) == 0) return 1f;
		
		for (Class c : RESISTS){
			if (c.isAssignableFrom(effect)){
				return (float)Math.pow(0.80, getBonus(target, Resistance.class));
			}
		}
		
		return 1f;
	}
	
	public class Resistance extends RingBuff {
	
	}
}
