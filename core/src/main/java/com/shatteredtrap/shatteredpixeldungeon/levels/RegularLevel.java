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

import com.shatteredtrap.shatteredpixeldungeon.Bones;
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.Actor;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Amogus;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Assasin;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Bee;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.ChaosElemental;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.DartSpitter;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.DemonRat;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.FilthJet;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Flower;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Goblin;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.NewbornElemental;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Piranha;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Rat;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.RotHeart;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.RotLasher;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Skelecrabat;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.SlaverBoss;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Spook;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Statue;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.npcs.NPC;
import com.shatteredtrap.shatteredpixeldungeon.items.Generator;
import com.shatteredtrap.shatteredpixeldungeon.items.Heap;
import com.shatteredtrap.shatteredpixeldungeon.items.Item;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredtrap.shatteredpixeldungeon.items.journal.GuidePage;
import com.shatteredtrap.shatteredpixeldungeon.items.keys.GoldenKey;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredtrap.shatteredpixeldungeon.journal.Document;
import com.shatteredtrap.shatteredpixeldungeon.levels.builders.Builder;
import com.shatteredtrap.shatteredpixeldungeon.levels.builders.LoopBuilder;
import com.shatteredtrap.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredtrap.shatteredpixeldungeon.levels.rooms.Room;
import com.shatteredtrap.shatteredpixeldungeon.levels.rooms.secret.SecretRoom;
import com.shatteredtrap.shatteredpixeldungeon.levels.rooms.special.PitRoom;
import com.shatteredtrap.shatteredpixeldungeon.levels.rooms.special.ShopRoom;
import com.shatteredtrap.shatteredpixeldungeon.levels.rooms.special.SpecialRoom;
import com.shatteredtrap.shatteredpixeldungeon.levels.rooms.standard.EntranceRoom;
import com.shatteredtrap.shatteredpixeldungeon.levels.rooms.standard.ExitRoom;
import com.shatteredtrap.shatteredpixeldungeon.levels.rooms.standard.StandardRoom;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.BlazingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.BurningTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.ChillingTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.DisintegrationTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.ExplosiveTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.FrostTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.Trap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.WornDartTrap;
import com.shatteredtrap.shatteredpixeldungeon.scenes.GameScene;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public abstract class RegularLevel extends Level {
	
	protected ArrayList<Room> rooms;
	
	protected Builder builder;
	
	protected Room roomEntrance;
	protected Room roomExit;
	
	public int secretDoors;
	protected boolean magicc = false;

	//public void removeMagicc(){
	//	magicc = false;
	//}

	@Override
	protected boolean build() {
		
		builder = builder();
		
		ArrayList<Room> initRooms = initRooms();
		Random.shuffle(initRooms);
		
		do {
			for (Room r : initRooms){
				r.neigbours.clear();
				r.connected.clear();
			}
			rooms = builder.build((ArrayList<Room>)initRooms.clone());
		} while (rooms == null);
		
		return painter().paint(this, rooms);
		
	}
	
	protected ArrayList<Room> initRooms() {
		ArrayList<Room> initRooms = new ArrayList<>();
		initRooms.add ( roomEntrance = new EntranceRoom());
		initRooms.add( roomExit = new ExitRoom());
		
		int standards = standardRooms();
		for (int i = 0; i < standards; i++) {
			StandardRoom s;
			do {
				s = StandardRoom.createRoom();
			} while (!s.setSizeCat( standards-i ));
			i += s.sizeCat.roomValue-1;
			initRooms.add(s);
		}
		
		if (Dungeon.shopOnLevel())
			initRooms.add(new ShopRoom());
		
		int specials = specialRooms();
		SpecialRoom.initForFloor();
		for (int i = 0; i < specials; i++) {
			SpecialRoom s = SpecialRoom.createRoom();
			if (s instanceof PitRoom) specials++;
			initRooms.add(s);
		}
		
		int secrets = SecretRoom.secretsForFloor(Dungeon.depth);
		for (int i = 0; i < secrets; i++)
			initRooms.add(SecretRoom.createRoom());
		
		return initRooms;
	}
	
	protected int standardRooms(){
		return 0;
	}
	
	protected int specialRooms(){
		return 0;
	}
	
	protected Builder builder(){
		return new LoopBuilder()
				.setLoopShape( 2 ,
						Random.Float(0.4f, 0.7f),
						Random.Float(0f, 0.5f));
	}
	
	protected abstract Painter painter();
	
	protected float waterFill(){
		return 0;
	}
	
	protected int waterSmoothing(){
		return 0;
	}
	
	protected float grassFill(){
		return 0;
	}
	
	protected int grassSmoothing(){
		return 0;
	}
	
	protected int nTraps() {
		if(this.feeling==Feeling.STUFF){
			return Random.NormalIntRange( 10, (int)(25+(Dungeon.depth*1.5)) );
		}
		return Random.NormalIntRange( 5, (int)(15+(Dungeon.depth*1.3)) );
	}
	
	protected Class<?>[] trapClasses(){
		return new Class<?>[]{WornDartTrap.class};
	}

	protected float[] trapChances() {
		return new float[]{1};
	}

	@Override
	public void press( int cell, Char ch ) {

		super.press(cell, ch);

		if (!magicc) {
			magicc = true;
			for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])){
				if (!(mob instanceof NPC || mob instanceof Bee || mob instanceof NewbornElemental || mob instanceof Piranha || mob instanceof RotHeart || mob instanceof RotLasher || mob instanceof Statue)){
					if(Random.Int(12)==0){
						mob.pos = randomDestination();
						//if (Dungeon.level.heroFOV[mob.pos]) {
							ScrollOfTeleportation.silencedAppear(mob, mob.pos);
						//}
					}
				}
			}
		}
	}

	@Override
	public int nMobs() {
		switch(Dungeon.depth) {
			case 1:
				//mobs are not randomly spawned on floor 1.
				return 0;
			default:
				return 2 + Dungeon.depth % 5 + Random.Int(5);
		}
	}
	
	@Override
	protected void createMobs() {
		//on floor 1, 10 rats are created so the player can get level 2.
		int mobsToSpawn = Dungeon.depth == 1 ? 10 : nMobs();

		ArrayList<Room> stdRooms = new ArrayList<>();
		for (Room room : rooms) {
			if (room instanceof StandardRoom && room != roomEntrance) {
				for (int i = 0; i < ((StandardRoom) room).sizeCat.roomValue; i++) {
					stdRooms.add(room);
				}
			}
		}
		Random.shuffle(stdRooms);
		Iterator<Room> stdRoomIter = stdRooms.iterator();
		boolean goblinSpawned = false;
		boolean statue1spawn = false;
		boolean statue2spawn = false;
		boolean eliteSpawned = false;
		boolean statuesSpawned = false;

		Mob nob = null;
		//elite enemies
		switch (Dungeon.depth){
			case 2:
				if(!Dungeon.elite1spawned&&Random.Float() < 0.1f){
					nob = new Flower();
					Dungeon.setElite1spawned(true);
				}
				break;
			case 3:
				if(!Dungeon.elite1spawned&&Random.Float() <0.25f){
					nob = new Flower();
					Dungeon.setElite1spawned(true);
				}
				break;
			case 4:
				if(!Dungeon.elite1spawned&&Random.Float() <0.5f){
					nob = new Flower();
					Dungeon.setElite1spawned(true);
				}
				break;

			case 7:
				if(!Dungeon.elite2spawned&&Random.Float() < 0.1f){
					nob = new SlaverBoss();
					Dungeon.setElite2spawned(true);
				}
				break;
			case 8:
				if(!Dungeon.elite2spawned&&Random.Float() <0.25f){
					nob = new SlaverBoss();
					Dungeon.setElite2spawned(true);
				}
				break;
			case 9:
				if(!Dungeon.elite2spawned&&Random.Float() <0.5f){
					nob = new SlaverBoss();
					Dungeon.setElite2spawned(true);
				}
				break;

			case 12:
				if(!Dungeon.elite3spawned&&Random.Float() < 0.1f){
					nob = new Spook();
					Dungeon.setElite3spawned(true);
				}
				break;
			case 13:
				if(!Dungeon.elite3spawned&&Random.Float() <0.25f){
					nob = new Spook();
					Dungeon.setElite3spawned(true);
				}
				break;
			case 14:
				if(!Dungeon.elite3spawned&&Random.Float() <0.5f){
					nob = new Spook();
					Dungeon.setElite3spawned(true);
				}
				break;

			case 17:
				if(!Dungeon.elite4spawned&&Random.Float() < 0.1f){
					nob = new Amogus();
					Dungeon.setElite4spawned(true);
				}
				break;
			case 18:
				if(!Dungeon.elite4spawned&&Random.Float() <0.25f){
					nob = new Amogus();
					Dungeon.setElite4spawned(true);
				}
				break;
			case 19:
				if(!Dungeon.elite4spawned&&Random.Float() <0.5f){
					nob = new Amogus();
					Dungeon.setElite4spawned(true);
				}
				break;

			case 22:
				if(!Dungeon.elite5spawned&&Random.Float() < 0.1f){
					nob = new Skelecrabat();
					Dungeon.setElite5spawned(true);
				}
				break;
			case 23:
				if(!Dungeon.elite5spawned&&Random.Float() <0.25f){
					nob = new Skelecrabat();
					Dungeon.setElite5spawned(true);
				}
				break;
			case 24:
				if(!Dungeon.elite5spawned&&Random.Float() <0.5f){
					nob = new Skelecrabat();
					Dungeon.setElite5spawned(true);
				}
				break;
		}

		while (mobsToSpawn > 0) {
			if (!stdRoomIter.hasNext())
				stdRoomIter = stdRooms.iterator();
			Room roomToSpawn = stdRoomIter.next();
			Mob mob;
			if(!goblinSpawned){
				mob = new Goblin();
				mobsToSpawn++;
				if(Random.Int( 4 )!=0){
					goblinSpawned=true;
				}
			}else if(!statue1spawn&&this.feeling==Feeling.STATUE && !Dungeon.statuesSpawned) {
				mob = new Statue();
				mob.state=mob.SLEEPING;
				mobsToSpawn++;
				statue1spawn=true;
			}else if (!statue2spawn&&this.feeling==Feeling.STATUE && !Dungeon.statuesSpawned) {
				mob = new Statue();
				mob.state=mob.SLEEPING;
				mobsToSpawn++;
				if(Random.Int(2)==0){
					statue2spawn=true;
					Dungeon.setStatuesSpawned(true);
				}
			}else if(nob!=null && !eliteSpawned){
				mob = nob;
				eliteSpawned = true;
				mobsToSpawn++;
			}else{

				mob = createMob();
				}

			mob.pos = pointToCell(roomToSpawn.random());

			if (findMob(mob.pos) == null && passable[mob.pos] && mob.pos != exit) {
				mobsToSpawn--;
				mobs.add(mob);

				//TODO: perhaps externalize this logic into a method. Do I want to make mobs more likely to clump deeper down?
				if (mobsToSpawn > 0 && Random.Int(4) == 0){
					mob = createMob();
					mob.pos = pointToCell(roomToSpawn.random());
					//Dungeon.level.randomRespawnCell();
					if (findMob(mob.pos)  == null && passable[mob.pos] && mob.pos != exit) {
						mobsToSpawn--;
						mobs.add(mob);
					}
				}
			}
		}
		//Goblin g = new Goblin();
		//g.pos = Dungeon.level.randomRespawnCell();
		//mobs.add(g);
		for (Mob m : mobs){
			if (map[m.pos] == Terrain.HIGH_GRASS || map[m.pos] == Terrain.FURROWED_GRASS) {
				map[m.pos] = Terrain.GRASS;
				losBlocking[m.pos] = false;
			}

		}

	}
	
	@Override
	public int randomRespawnCell() {
		int count = 0;
		int cell = -1;
		
		while (true) {
			
			if (++count > 30) {
				return -1;
			}
			
			Room room = randomRoom( StandardRoom.class );
			if (room == null || room == roomEntrance) {
				continue;
			}

			cell = pointToCell(room.random(1));
			if (!heroFOV[cell]
					&& Actor.findChar( cell ) == null
					&& passable[cell]
					&& room.canPlaceCharacter(cellToPoint(cell), this)
					&& cell != exit) {
				return cell;
			}
			
		}
	}
	
	@Override
	public int randomDestination() {
		
		int count = 0;
		int cell = -1;
		
		while (true) {
			
			if (++count > 30) {
				return -1;
			}
			
			Room room = Random.element( rooms );
			if (room == null) {
				continue;
			}
			
			cell = pointToCell(room.random());
			if (passable[cell]) {
				return cell;
			}
			
		}
	}
	
	@Override
	protected void createItems() {
		
		// drops 3/4/5 items 60%/30%/10% of the time
		int nItems = 3 + Random.chances(new float[]{6, 3, 1});
		
		for (int i=0; i < nItems; i++) {
			Heap.Type type = null;
			switch (Random.Int( 20 )) {
			case 0:
				type = Heap.Type.SKELETON;
				break;
			case 1:
			case 2:
			case 3:
			case 4:
				type = Heap.Type.CHEST;
				break;
			case 5:
				type = Dungeon.depth > 1 ? Heap.Type.MIMIC : Heap.Type.CHEST;
				break;
			default:
				type = Heap.Type.HEAP;
			}
			int cell = randomDropCell();
			if (map[cell] == Terrain.HIGH_GRASS || map[cell] == Terrain.FURROWED_GRASS) {
				map[cell] = Terrain.GRASS;
				losBlocking[cell] = false;
			}
			
			Item toDrop = Generator.random();

			if (toDrop == null) continue;

			if ((toDrop instanceof Artifact && Random.Int(2) == 0) ||
					(toDrop.isUpgradable() && Random.Int(4 - toDrop.level()) == 0)){
				Heap dropped = drop( toDrop, cell );
				if (heaps.get(cell) == dropped) {
					dropped.type = Heap.Type.LOCKED_CHEST;
					addItemToSpawn(new GoldenKey(Dungeon.depth));
				}
			} else {
				Heap dropped = drop( toDrop, cell );
				dropped.type = type;
				if (type == Heap.Type.SKELETON){
					dropped.setHauntedIfCursed(0.75f);
				}
			}
			
		}

		for (Item item : itemsToSpawn) {
			int cell = randomDropCell();
			drop( item, cell ).type = Heap.Type.HEAP;
			if (map[cell] == Terrain.HIGH_GRASS || map[cell] == Terrain.FURROWED_GRASS) {
				map[cell] = Terrain.GRASS;
				losBlocking[cell] = false;
			}
		}
		
		Item item = Bones.get();
		if (item != null) {
			int cell = randomDropCell();
			if (map[cell] == Terrain.HIGH_GRASS || map[cell] == Terrain.FURROWED_GRASS) {
				map[cell] = Terrain.GRASS;
				losBlocking[cell] = false;
			}
			drop( item, cell ).setHauntedIfCursed(1f).type = Heap.Type.REMAINS;
		}

		//guide pages
		Collection<String> allPages = Document.ADVENTURERS_GUIDE.pages();
		ArrayList<String> missingPages = new ArrayList<>();
		for ( String page : allPages){
			if (!Document.ADVENTURERS_GUIDE.hasPage(page)){
				missingPages.add(page);
			}
		}

		//these are dropped specially
		missingPages.remove(Document.GUIDE_INTRO_PAGE);
		missingPages.remove(Document.GUIDE_SEARCH_PAGE);

		int foundPages = allPages.size() - (missingPages.size() + 2);

		//chance to find a page scales with pages missing and depth
		if (missingPages.size() > 0 && Random.Float() < (Dungeon.depth/(float)(foundPages + 1))){
			GuidePage p = new GuidePage();
			p.page(missingPages.get(0));
			int cell = randomDropCell();
			if (map[cell] == Terrain.HIGH_GRASS || map[cell] == Terrain.FURROWED_GRASS) {
				map[cell] = Terrain.GRASS;
				losBlocking[cell] = false;
			}
			drop( p, cell );
		}

	}
	
	public ArrayList<Room> rooms() {
		return new ArrayList<>(rooms);
	}
	
	//FIXME pit rooms shouldn't be problematic enough to warrant this
	public boolean hasPitRoom(){
		for (Room r : rooms) {
			if (r instanceof PitRoom) {
				return true;
			}
		}
		return false;
	}
	
	protected Room randomRoom( Class<?extends Room> type ) {
		Random.shuffle( rooms );
		for (Room r : rooms) {
			if (type.isInstance(r)) {
				return r;
			}
		}
		return null;
	}
	
	public Room room( int pos ) {
		for (Room room : rooms) {
			if (room.inside( cellToPoint(pos) )) {
				return room;
			}
		}
		
		return null;
	}
	
	protected int randomDropCell() {
		while (true) {
			Room room = randomRoom( StandardRoom.class );
			if (room != null && room != roomEntrance) {
				int pos = pointToCell(room.random());
				if (passable[pos]
						&& pos != exit
						&& heaps.get(pos) == null) {
					
					Trap t = traps.get(pos);
					
					//items cannot spawn on traps which destroy items
					if (t == null ||
							! (t instanceof BurningTrap || t instanceof BlazingTrap
							|| t instanceof ChillingTrap || t instanceof FrostTrap
							|| t instanceof ExplosiveTrap || t instanceof DisintegrationTrap)) {
						
						return pos;
					}
				}
			}
		}
	}
	
	@Override
	public int fallCell( boolean fallIntoPit ) {
		if (fallIntoPit) {
			for (Room room : rooms) {
				if (room instanceof PitRoom) {
					int result;
					do {
						result = pointToCell(room.random());
					} while (traps.get(result) != null
							|| findMob(result) != null
							|| heaps.get(result) != null);
					return result;
				}
			}
		}
		
		return super.fallCell( false );
	}

	private final String MAGICC = "magicc";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put(MAGICC, magicc);
		bundle.put( "rooms", rooms );
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		magicc = bundle.getBoolean(MAGICC);
		rooms = new ArrayList<>( (Collection<Room>) ((Collection<?>) bundle.getCollection( "rooms" )) );
		for (Room r : rooms) {
			r.onLevelLoad( this );
			if (r instanceof EntranceRoom ){
				roomEntrance = r;
			} else if (r instanceof ExitRoom ){
				roomExit = r;
			}
		}
	}
	
}
