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
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.npcs.Imp;
import com.shatteredtrap.shatteredpixeldungeon.levels.painters.CityPainter;
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
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.WarpingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.WeakeningTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.WornDartTrap;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.watabou.noosa.Group;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class CityLevel extends RegularLevel {

	{
		color1 = 0x4b6636;
		color2 = 0xf2f2f2;
	}

	@Override
	protected int standardRooms() {
		//7 to 10, average 7.9
		return 7+Random.chances(new float[]{4, 3, 2, 1});
	}
	
	@Override
	protected int specialRooms() {
		//2 to 3, average 2.33
		return 2 + Random.chances(new float[]{2, 1});
	}
	
	@Override
	public String tilesTex() {
		return Assets.TILES_CITY;
	}
	
	@Override
	public String waterTex() {
		return Assets.WATER_CITY;
	}
	
	@Override
	protected Painter painter() {
		return new CityPainter()
				.setWater(feeling == Feeling.WATER ? 0.90f : 0.30f, 4)
				.setGrass(feeling == Feeling.GRASS ? 0.80f : 0.20f, 3)
				.setTraps(nTraps(), trapClasses(), trapChances());
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
						TransmutationTrap.class, RearmTrap.class
				};
	}

	@Override
	protected float[] trapChances() {
		return //Dungeon.depth == 1 ?
				//new float[]{1} :
				new float[]{4, 25, 3, 4,
						2, 3, 25, 5,
						8, 3, 25, 3,
						2, 15, 15, 7,
						25, 3, 5, 15,
						4, 4, 4, 5,
						6, 15, 2, 9,
						25, 12, 4, 3,
						3, 8, 15, 6,
						5, 8, 4, 2,
						3, 5, 2, 3, 1, 2, 3, 6, 2,
						5, 10, 4, 3, 2, 3};
	}
	
	@Override
	protected void createMobs() {
		Imp.Quest.spawn( this );
		
		super.createMobs();
	}
	
	@Override
	public String tileName( int tile ) {
		switch (tile) {
			case Terrain.WATER:
				return Messages.get(CityLevel.class, "water_name");
			case Terrain.HIGH_GRASS:
				return Messages.get(CityLevel.class, "high_grass_name");
			default:
				return super.tileName( tile );
		}
	}
	
	@Override
	public String tileDesc(int tile) {
		switch (tile) {
			case Terrain.ENTRANCE:
				return Messages.get(CityLevel.class, "entrance_desc");
			case Terrain.EXIT:
				return Messages.get(CityLevel.class, "exit_desc");
			case Terrain.WALL_DECO:
			case Terrain.EMPTY_DECO:
				return Messages.get(CityLevel.class, "deco_desc");
			case Terrain.EMPTY_SP:
				return Messages.get(CityLevel.class, "sp_desc");
			case Terrain.STATUE:
			case Terrain.STATUE_SP:
				return Messages.get(CityLevel.class, "statue_desc");
			case Terrain.BOOKSHELF:
				return Messages.get(CityLevel.class, "bookshelf_desc");
			default:
				return super.tileDesc( tile );
		}
	}
	
	@Override
	public Group addVisuals() {
		super.addVisuals();
		addCityVisuals( this, visuals );
		return visuals;
	}

	public static void addCityVisuals( Level level, Group group ) {
		for (int i=0; i < level.length(); i++) {
			if (level.map[i] == Terrain.WALL_DECO) {
				group.add( new Smoke( i ) );
			}
		}
	}
	
	private static class Smoke extends Emitter {
		
		private int pos;
		
		private static final Emitter.Factory factory = new Factory() {
			
			@Override
			public void emit( Emitter emitter, int index, float x, float y ) {
				SmokeParticle p = (SmokeParticle)emitter.recycle( SmokeParticle.class );
				p.reset( x, y );
			}
		};
		
		public Smoke( int pos ) {
			super();
			
			this.pos = pos;
			
			PointF p = DungeonTilemap.tileCenterToWorld( pos );
			pos( p.x - 6, p.y - 4, 12, 12 );
			
			pour( factory, 0.2f );
		}
		
		@Override
		public void update() {
			if (visible = (pos < Dungeon.level.heroFOV.length && Dungeon.level.heroFOV[pos])) {
				super.update();
			}
		}
	}
	
	public static final class SmokeParticle extends PixelParticle {
		
		public SmokeParticle() {
			super();
			
			color( 0x000000 );
			speed.set( Random.Float( -2, 4 ), -Random.Float( 3, 6 ) );
		}
		
		public void reset( float x, float y ) {
			revive();
			
			this.x = x;
			this.y = y;
			
			left = lifespan = 2f;
		}
		
		@Override
		public void update() {
			super.update();
			float p = left / lifespan;
			am = p > 0.8f ? 1 - p : p * 0.25f;
			size( 6 - p * 3 );
		}
	}
}