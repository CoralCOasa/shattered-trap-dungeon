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

package com.shatteredtrap.shatteredpixeldungeon.levels;

import com.shatteredtrap.shatteredpixeldungeon.Assets;
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.npcs.Wandmaker;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.AnnihilationTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.BargainTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.BlazingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.BlizzardTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.CannonTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.ChaosTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.ChestnutTrap;
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
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.FloodTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.FrostCurseTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.FrostTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.FuryTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.GoblinTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.GrimTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.GuardianTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.HealingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.InfernoTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.LotteryTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.MarkingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.MimicTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.MultiTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.OvergrowthTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.PitfallTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.RearmTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.RegrowthTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.RockfallTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.SharpnelTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.SlicingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.SmokescreenTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.SpookTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.StormTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.TransmutationTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.WarpingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.WeakeningTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.WornDartTrap;
import com.watabou.noosa.Halo;
import com.shatteredtrap.shatteredpixeldungeon.effects.particles.FlameParticle;
import com.shatteredtrap.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredtrap.shatteredpixeldungeon.levels.painters.PrisonPainter;
import com.shatteredtrap.shatteredpixeldungeon.levels.rooms.Room;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.AlarmTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.BurningTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.ChillingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.ConfusionTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.FlockTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.GrippingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.OozeTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.PoisonDartTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.ShockingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.SummoningTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.TeleportationTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.ToxicTrap;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.watabou.noosa.Group;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class PrisonLevel extends RegularLevel {

	{
		color1 = 0x6a723d;
		color2 = 0x88924c;
	}
	
	@Override
	protected ArrayList<Room> initRooms() {
		return Wandmaker.Quest.spawnRoom(super.initRooms());
	}
	
	@Override
	protected int standardRooms() {
		//6 to 8, average 6.66
		return 6+Random.chances(new float[]{4, 2, 2});
	}
	
	@Override
	protected int specialRooms() {
		//1 to 3, average 1.83
		return 1+Random.chances(new float[]{3, 4, 3});
	}
	
	@Override
	protected Painter painter() {
		return new PrisonPainter()
				.setWater(feeling == Feeling.WATER ? 0.90f : 0.30f, 4)
				.setGrass(feeling == Feeling.GRASS ? 0.80f : 0.20f, 3)
				.setTraps(nTraps(), trapClasses(), trapChances());
	}
	
	@Override
	public String tilesTex() {
		return Assets.TILES_PRISON;
	}
	
	@Override
	public String waterTex() {
		return Assets.WATER_PRISON;
	}

	@Override
	protected Class<?>[] trapClasses() {
		return //Dungeon.depth == 1 ?
				//new Class<?>[]{WornDartTrap.class } :
				new Class<?>[]{AlarmTrap.class, BlazingTrap.class, BurningTrap.class, ChaosTrap.class,
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
						TransmutationTrap.class, RearmTrap.class, BargainTrap.class, MultiTrap.class
				};
	}

	@Override
	protected float[] trapChances() {
		return //Dungeon.depth == 1 ?
				//new float[]{1} :
				new float[]{17, 2, 27, 4,
						27, 10, 3, 5,
						2, 1, 3, 2,
						1, 5, 4, 10,
						3, 1, 17, 1,
						4, 17, 2, 27,
						9, 5, 27, 9,
						3, 10, 10, 27,
						2, 3, 3, 12,
						3, 3, 5, 2,
						3, 6, 6, 2, 1, 4, 1,
						4, 5, 4, 3, 1, 3, 8, 1};
	}

	@Override
	public String tileName( int tile ) {
		switch (tile) {
			case Terrain.WATER:
				return Messages.get(PrisonLevel.class, "water_name");
			default:
				return super.tileName( tile );
		}
	}

	@Override
	public String tileDesc(int tile) {
		switch (tile) {
			case Terrain.EMPTY_DECO:
				return Messages.get(PrisonLevel.class, "empty_deco_desc");
			case Terrain.BOOKSHELF:
				return Messages.get(PrisonLevel.class, "bookshelf_desc");
			default:
				return super.tileDesc( tile );
		}
	}
	
	@Override
	public Group addVisuals() {
		super.addVisuals();
		addPrisonVisuals(this, visuals);
		return visuals;
	}

	public static void addPrisonVisuals(Level level, Group group){
		for (int i=0; i < level.length(); i++) {
			if (level.map[i] == Terrain.WALL_DECO) {
				group.add( new Torch( i ) );
			}
		}
	}
	
	public static class Torch extends Emitter {
		
		private int pos;
		
		public Torch( int pos ) {
			super();
			
			this.pos = pos;
			
			PointF p = DungeonTilemap.tileCenterToWorld( pos );
			pos( p.x - 1, p.y + 2, 2, 0 );
			
			pour( FlameParticle.FACTORY, 0.15f );
			
			add( new Halo( 12, 0xFFFFCC, 0.4f ).point( p.x, p.y + 1 ) );
		}
		
		@Override
		public void update() {
			if (visible = (pos < Dungeon.level.heroFOV.length && Dungeon.level.heroFOV[pos])) {
				super.update();
			}
		}
	}
}