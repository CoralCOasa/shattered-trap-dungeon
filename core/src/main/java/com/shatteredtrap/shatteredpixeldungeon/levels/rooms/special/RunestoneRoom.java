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

package com.shatteredtrap.shatteredpixeldungeon.levels.rooms.special;

import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.items.Generator;
import com.shatteredtrap.shatteredpixeldungeon.items.Item;
import com.shatteredtrap.shatteredpixeldungeon.items.keys.IronKey;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredtrap.shatteredpixeldungeon.levels.Level;
import com.shatteredtrap.shatteredpixeldungeon.levels.SewerLevel;
import com.shatteredtrap.shatteredpixeldungeon.levels.Terrain;
import com.shatteredtrap.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.AlarmTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.AnnihilationTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.BlazingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.BlizzardTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.BurningTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.CannonTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.ChaosTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.ChestnutTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.ChillingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.ConfusionTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.CorrosionTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.CryoTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.CursingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.DisarmingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.DisintegrationTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.DisrobingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.DistortionTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.DreamTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.EnchantingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.ExplosiveTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.FlashingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.FlockTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.FloodTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.FrostCurseTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.FrostTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.FuryTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.GoblinTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.GrimTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.GrippingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.GuardianTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.HealingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.InfernoTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.LotteryTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.MarkingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.MimicTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.OozeTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.OvergrowthTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.PitfallTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.PoisonDartTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.RearmTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.RegrowthTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.RockfallTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.SharpnelTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.ShockingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.SlicingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.SmokescreenTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.SpookTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.StormTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.SummoningTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.TeleportationTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.ToxicTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.TransmutationTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.Trap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.WarpingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.WeakeningTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.WornDartTrap;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

public class RunestoneRoom extends SpecialRoom {
	
	@Override
	public int minWidth() { return 6; }
	
	@Override
	public int minHeight() {
		return 6;
	}
	
	@Override
	public void paint( Level level) {
		
		Painter.fill( level, this, Terrain.WALL );
		Painter.fill( level, this, 1, Terrain.TRAP );
		
		Painter.drawInside( level, this, entrance(), 2, Terrain.EMPTY_SP);
		Painter.fill( level, this, 2, Terrain.EMPTY_SP );
		
		int n = Random.NormalIntRange(2, 4);
		int dropPos;
		for (int i = 0; i < n; i++) {
			do {
				dropPos = level.pointToCell(random());
			} while (level.map[dropPos] != Terrain.EMPTY_SP);
			level.drop(prize(level), dropPos);
		}

		for(Point p : getPoints()) {
			int cell = level.pointToCell(p);
			if (level.map[cell] == Terrain.TRAP){
				Class<? extends Trap> trapClass;
				trapClass = Random.oneOf(levelTraps[0]);
				Trap t = null;
				try {
					t = (Trap) trapClass.newInstance();
				} catch (IllegalAccessException e) {
				} catch (InstantiationException e) {
				}
				if(Random.Int(2)==0) {
						level.setTrap(t.reveal(), cell);
				}else{
					level.map[cell] = Terrain.INACTIVE_TRAP;
					t.active = false;
					t.reveal();
					level.setTrap(t, cell);
				}
			}
		}

		entrance().set( Door.Type.LOCKED );
		level.addItemToSpawn( new IronKey( Dungeon.depth ) );
	}

	private static Class<?extends Trap>[][] levelTraps = new Class[][]{
			{AlarmTrap.class, BlazingTrap.class, BurningTrap.class, ChaosTrap.class,
					ChillingTrap.class, ConfusionTrap.class, CorrosionTrap.class, CryoTrap.class,
					CursingTrap.class, DisarmingTrap.class, DisintegrationTrap.class, DisrobingTrap.class,
					DistortionTrap.class, ExplosiveTrap.class, FlashingTrap.class, FlockTrap.class,
					FrostTrap.class, GrimTrap.class, GrippingTrap.class, GuardianTrap.class,
					MimicTrap.class, OozeTrap.class, PitfallTrap.class, PoisonDartTrap.class,
					RegrowthTrap.class, RockfallTrap.class, ShockingTrap.class, SmokescreenTrap.class,
					StormTrap.class, SummoningTrap.class, TeleportationTrap.class, ToxicTrap.class,
					AnnihilationTrap.class, WarpingTrap.class, WeakeningTrap.class, WornDartTrap.class,
					InfernoTrap.class, BlizzardTrap.class, FloodTrap.class, LotteryTrap.class,
					HealingTrap.class, SpookTrap.class, ChestnutTrap.class, SharpnelTrap.class, OvergrowthTrap.class, GoblinTrap.class, DreamTrap.class, CannonTrap.class, EnchantingTrap.class,
					SlicingTrap.class, FuryTrap.class, FrostCurseTrap.class, MarkingTrap.class,
					TransmutationTrap.class, RearmTrap.class},
	};

	private static Item prize( Level level ) {
		
		Item prize = level.findPrizeItem( Runestone.class );
		if (prize == null)
			prize = Generator.random( Generator.Category.STONE );
		
		return prize;
	}
}
