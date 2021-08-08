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
import com.shatteredtrap.shatteredpixeldungeon.Bones;
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.Actor;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Bat;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Brute;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.DM300;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.DartSpitter;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Goblin;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.ShockerBlock;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Spinner;
import com.shatteredtrap.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredtrap.shatteredpixeldungeon.effects.Speck;
import com.shatteredtrap.shatteredpixeldungeon.items.Heap;
import com.shatteredtrap.shatteredpixeldungeon.items.Item;
import com.shatteredtrap.shatteredpixeldungeon.items.keys.SkeletonKey;
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
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredtrap.shatteredpixeldungeon.tiles.DungeonTileSheet;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Group;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;
import com.watabou.utils.Rect;

public class CavesBossLevel extends Level {
	
	{
		color1 = 0x534f3e;
		color2 = 0xb9d661;

		viewDistance = Math.min(6, viewDistance);
	}

	private static final int WIDTH = 32;
	private static final int HEIGHT = 32;

	private static final int ROOM_LEFT		= WIDTH / 2 - 3;
	private static final int ROOM_RIGHT		= WIDTH / 2 + 1;
	private static final int ROOM_TOP		= HEIGHT / 2 - 2;
	private static final int ROOM_BOTTOM	= HEIGHT / 2 + 2;
	
	private int arenaDoor;
	private boolean enteredArena = false;
	private boolean keyDropped = false;
	
	@Override
	public String tilesTex() {
		return Assets.TILES_CAVES;
	}
	
	@Override
	public String waterTex() {
		return Assets.WATER_CAVES;
	}
	
	private static final String DOOR	= "door";
	private static final String ENTERED	= "entered";
	private static final String DROPPED	= "droppped";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( DOOR, arenaDoor );
		bundle.put( ENTERED, enteredArena );
		bundle.put( DROPPED, keyDropped );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		arenaDoor = bundle.getInt( DOOR );
		enteredArena = bundle.getBoolean( ENTERED );
		keyDropped = bundle.getBoolean( DROPPED );
	}
	
	@Override
	protected boolean build() {
		
		setSize(WIDTH, HEIGHT);

		Rect space = new Rect();

		space.set(
				Random.IntRange(2, 6),
				Random.IntRange(2, 6),
				Random.IntRange(width-6, width-2),
				Random.IntRange(height-6, height-2)
		);

		Painter.fillEllipse( this, space, Terrain.EMPTY );

		exit = space.left + space.width()/2 + (space.top - 1) * width();
		
		map[exit] = Terrain.LOCKED_EXIT;
		
		Painter.fill( this, ROOM_LEFT - 1, ROOM_TOP - 1,
			ROOM_RIGHT - ROOM_LEFT + 3, ROOM_BOTTOM - ROOM_TOP + 3, Terrain.WALL );
		Painter.fill( this, ROOM_LEFT, ROOM_TOP + 1,
			ROOM_RIGHT - ROOM_LEFT + 1, ROOM_BOTTOM - ROOM_TOP, Terrain.EMPTY );

		Painter.fill( this, ROOM_LEFT, ROOM_TOP,
			ROOM_RIGHT - ROOM_LEFT + 1, 1, Terrain.EMPTY_DECO );
		
		arenaDoor = Random.Int( ROOM_LEFT, ROOM_RIGHT ) + (ROOM_BOTTOM + 1) * width();
		map[arenaDoor] = Terrain.DOOR;
		
		entrance = Random.Int( ROOM_LEFT + 1, ROOM_RIGHT - 1 ) +
			Random.Int( ROOM_TOP + 1, ROOM_BOTTOM - 1 ) * width();
		map[entrance] = Terrain.ENTRANCE;
		
		boolean[] patch = Patch.generate( width, height, 0.30f, 6, true );
		for (int i=0; i < length(); i++) {
			if (map[i] == Terrain.EMPTY && patch[i]) {
				map[i] = Terrain.WATER;
			}
		}

		for (int i=0; i < length(); i++) {
			if (map[i] == Terrain.EMPTY && Random.Int( 5 ) == 0) {
				Class<? extends Trap> trapClass;
				trapClass = Random.oneOf(levelTraps[0]);
				Trap t = null;
				try {
					t = (Trap) trapClass.newInstance();
				} catch (IllegalAccessException e) {
				} catch (InstantiationException e) {
				}
				if(Random.Int( 2) == 0){
					map[i] = Terrain.INACTIVE_TRAP;
					t.reveal();
					t.active = false;
					setTrap(t, i);
				}else{
					map[i] = Terrain.TRAP;
					t.reveal();
					setTrap(t, i);
				}
			}
		}
		
		for (int i=width() + 1; i < length() - width(); i++) {
			if (map[i] == Terrain.EMPTY) {
				int n = 0;
				if (map[i+1] == Terrain.WALL) {
					n++;
				}
				if (map[i-1] == Terrain.WALL) {
					n++;
				}
				if (map[i+width()] == Terrain.WALL) {
					n++;
				}
				if (map[i-width()] == Terrain.WALL) {
					n++;
				}
				if (Random.Int( 8 ) <= n) {
					map[i] = Terrain.EMPTY_DECO;
				}
			}
		}
		
		for (int i=0; i < length() - width(); i++) {
			if (map[i] == Terrain.WALL
					&& DungeonTileSheet.floorTile(map[i + width()])
					&& Random.Int( 3 ) == 0) {
				map[i] = Terrain.WALL_DECO;
			}
		}
		
		return true;
	}

	private static Class<?extends Trap>[][] levelTraps = new Class[][]{
			{AlarmTrap.class, BlazingTrap.class, BurningTrap.class,
					ChillingTrap.class, ConfusionTrap.class, CorrosionTrap.class, CryoTrap.class,
					DisintegrationTrap.class,
					ExplosiveTrap.class, FlockTrap.class,
					FrostTrap.class, GrimTrap.class,
					MimicTrap.class, PoisonDartTrap.class,
					RegrowthTrap.class, RockfallTrap.class, ShockingTrap.class, SmokescreenTrap.class,
					StormTrap.class, ToxicTrap.class,
					AnnihilationTrap.class, WornDartTrap.class,
					InfernoTrap.class, BlizzardTrap.class, FloodTrap.class, SpookTrap.class, SharpnelTrap.class, OvergrowthTrap.class, GoblinTrap.class, CannonTrap.class,
					FrostCurseTrap.class, MarkingTrap.class},
	};

	@Override
	protected void createMobs() {
	}
	
	public Actor respawner() {
		return null;
	}
	
	@Override
	protected void createItems() {
		Item item = Bones.get();
		if (item != null) {
			int pos;
			do {
				pos = Random.IntRange( ROOM_LEFT, ROOM_RIGHT ) + Random.IntRange( ROOM_TOP + 1, ROOM_BOTTOM ) * width();
			} while (pos == entrance);
			drop( item, pos ).setHauntedIfCursed(1f).type = Heap.Type.REMAINS;
		}
	}
	
	@Override
	public int randomRespawnCell() {
		int cell = entrance + PathFinder.NEIGHBOURS8[Random.Int(8)];
		while (!passable[cell]){
			cell = entrance + PathFinder.NEIGHBOURS8[Random.Int(8)];
		}
		return cell;
	}
	
	@Override
	public void press( int cell, Char hero ) {
		
		super.press( cell, hero );
		
		if (!enteredArena && outsideEntraceRoom( cell ) && hero == Dungeon.hero) {
			
			enteredArena = true;
			seal();
			
			for (Mob m : mobs){
				//bring the first ally with you
				if (m.alignment == Char.Alignment.ALLY && !m.properties().contains(Char.Property.IMMOVABLE)){
					m.pos = Dungeon.hero.pos + (Random.Int(2) == 0 ? +1 : -1);
					m.sprite.place(m.pos);
					break;
				}
			}
			
			DM300 boss = new DM300();
			boss.state = boss.WANDERING;
			DartSpitter test = new DartSpitter();
			Brute test2 = new Brute();
			ShockerBlock test3 = new ShockerBlock();
			Spinner test4 = new Spinner();
			Goblin test5 = new Goblin();
			int rng = Random.Int( 4 );
			do {
				boss.pos = Random.Int( length() );
			} while (
					!passable[boss.pos] ||
							!outsideEntraceRoom( boss.pos ) ||
							heroFOV[boss.pos]);
			do {
				test.pos = Random.Int( length() );
			} while (
					!passable[test.pos] ||
							!outsideEntraceRoom( test.pos ) ||
							heroFOV[test.pos]);
			do {
				test2.pos = Random.Int( length() );
			} while (
					!passable[test2.pos] ||
							!outsideEntraceRoom( test2.pos ) ||
							heroFOV[test2.pos]);
			do {
				test3.pos = Random.Int( length() );
			} while (
					!passable[test3.pos] ||
							!outsideEntraceRoom( test3.pos ) ||
							heroFOV[test3.pos]);
			do {
				test4.pos = Random.Int( length() );
			} while (
					!passable[test4.pos] ||
							!outsideEntraceRoom( test4.pos ) ||
							heroFOV[test4.pos]);
			do {
				test5.pos = Random.Int( length() );
			} while (
					!passable[test5.pos] ||
							!outsideEntraceRoom( test5.pos ) ||
							heroFOV[test5.pos]);
			GameScene.add( boss );
			if(rng!=0){
				GameScene.add( test );
			}
			if(rng!=1){
				GameScene.add( test2 );
			}
			if(rng!=2){
				GameScene.add( test3 );
			}
			if(rng!=3){
				GameScene.add( test4 );
			}
			GameScene.add( test5 );
			set( arenaDoor, Terrain.WALL );
			GameScene.updateMap( arenaDoor );
			Dungeon.observe();
			
			CellEmitter.get( arenaDoor ).start( Speck.factory( Speck.ROCK ), 0.07f, 10 );
			Camera.main.shake( 3, 0.7f );
			Sample.INSTANCE.play( Assets.SND_ROCKS );
		}
	}
	
	@Override
	public Heap drop( Item item, int cell ) {
		
		if (!keyDropped && item instanceof SkeletonKey) {
			
			keyDropped = true;
			unseal();
			
			CellEmitter.get( arenaDoor ).start( Speck.factory( Speck.ROCK ), 0.07f, 10 );
			
			set( arenaDoor, Terrain.EMPTY_DECO );
			GameScene.updateMap( arenaDoor );
			Dungeon.observe();
		}
		
		return super.drop( item, cell );
	}
	
	private boolean outsideEntraceRoom( int cell ) {
		int cx = cell % width();
		int cy = cell / width();
		return cx < ROOM_LEFT-1 || cx > ROOM_RIGHT+1 || cy < ROOM_TOP-1 || cy > ROOM_BOTTOM+1;
	}
	
	@Override
	public String tileName( int tile ) {
		switch (tile) {
			case Terrain.GRASS:
				return Messages.get(CavesLevel.class, "grass_name");
			case Terrain.HIGH_GRASS:
				return Messages.get(CavesLevel.class, "high_grass_name");
			case Terrain.WATER:
				return Messages.get(CavesLevel.class, "water_name");
			default:
				return super.tileName( tile );
		}
	}
	
	@Override
	public String tileDesc( int tile ) {
		switch (tile) {
			case Terrain.ENTRANCE:
				return Messages.get(CavesLevel.class, "entrance_desc");
			case Terrain.EXIT:
				return Messages.get(CavesLevel.class, "exit_desc");
			case Terrain.HIGH_GRASS:
				return Messages.get(CavesLevel.class, "high_grass_desc");
			case Terrain.WALL_DECO:
				return Messages.get(CavesLevel.class, "wall_deco_desc");
			case Terrain.BOOKSHELF:
				return Messages.get(CavesLevel.class, "bookshelf_desc");
			default:
				return super.tileDesc( tile );
		}
	}

	@Override
	public Group addVisuals() {
		super.addVisuals();
		CavesLevel.addCavesVisuals(this, visuals);
		return visuals;
	}
}
