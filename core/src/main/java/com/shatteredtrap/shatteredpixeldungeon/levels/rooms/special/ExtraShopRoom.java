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
import com.shatteredtrap.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.npcs.Shopkeeper;
import com.shatteredtrap.shatteredpixeldungeon.items.Ankh;
import com.shatteredtrap.shatteredpixeldungeon.items.Generator;
import com.shatteredtrap.shatteredpixeldungeon.items.Heap;
import com.shatteredtrap.shatteredpixeldungeon.items.Honeypot;
import com.shatteredtrap.shatteredpixeldungeon.items.Item;
import com.shatteredtrap.shatteredpixeldungeon.items.MerchantsBeacon;
import com.shatteredtrap.shatteredpixeldungeon.items.Stylus;
import com.shatteredtrap.shatteredpixeldungeon.items.Torch;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.LeatherArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.MailArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.PlateArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.ScaleArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.shatteredtrap.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredtrap.shatteredpixeldungeon.items.bags.MagicalHolster;
import com.shatteredtrap.shatteredpixeldungeon.items.bags.PotionBandolier;
import com.shatteredtrap.shatteredpixeldungeon.items.bags.ScrollHolder;
import com.shatteredtrap.shatteredpixeldungeon.items.bags.VelvetPouch;
import com.shatteredtrap.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredtrap.shatteredpixeldungeon.items.food.SmallRation;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.exotic.ExoticPotion;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.exotic.ExoticScroll;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.StoneOfAugmentation;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.BattleAxe;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Greatsword;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.HandAxe;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Longsword;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Mace;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Shortsword;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Sword;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.WarHammer;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.Bolas;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.FishingSpear;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.Javelin;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.Shuriken;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.ThrowingHammer;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.ThrowingSpear;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.Tomahawk;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.Trident;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.darts.TippedDart;
import com.shatteredtrap.shatteredpixeldungeon.levels.Level;
import com.shatteredtrap.shatteredpixeldungeon.levels.Terrain;
import com.shatteredtrap.shatteredpixeldungeon.levels.painters.Painter;
import com.shatteredtrap.shatteredpixeldungeon.plants.Plant;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class ExtraShopRoom extends SpecialRoom {

	private ArrayList<Item> itemsToSpawn;
	
	@Override
	public int minWidth() {
		if (itemsToSpawn == null) itemsToSpawn = generateItems();
		return Math.max(7, (int)(Math.sqrt(itemsToSpawn.size())+3));
	}
	
	@Override
	public int minHeight() {
		if (itemsToSpawn == null) itemsToSpawn = generateItems();
		return Math.max(7, (int)(Math.sqrt(itemsToSpawn.size())+3));
	}
	
	public void paint( Level level ) {
		
		Painter.fill( level, this, Terrain.WALL );
		Painter.fill( level, this, 1, Terrain.EMPTY_SP );

		placeShopkeeper( level );

		placeItems( level );
		
		for (Door door : connected.values()) {
			door.set( Door.Type.REGULAR );
		}

	}

	protected void placeShopkeeper( Level level ) {

		int pos = level.pointToCell(center());

		Mob shopkeeper = new Shopkeeper();
		shopkeeper.pos = pos;
		level.mobs.add( shopkeeper );

	}

	protected void placeItems( Level level ){

		if (itemsToSpawn == null)
			itemsToSpawn = generateItems();

		Point itemPlacement = new Point(entrance());
		if (itemPlacement.y == top){
			itemPlacement.y++;
		} else if (itemPlacement.y == bottom) {
			itemPlacement.y--;
		} else if (itemPlacement.x == left){
			itemPlacement.x++;
		} else {
			itemPlacement.x--;
		}

		for (Item item : itemsToSpawn) {

			if (itemPlacement.x == left+1 && itemPlacement.y != top+1){
				itemPlacement.y--;
			} else if (itemPlacement.y == top+1 && itemPlacement.x != right-1){
				itemPlacement.x++;
			} else if (itemPlacement.x == right-1 && itemPlacement.y != bottom-1){
				itemPlacement.y++;
			} else {
				itemPlacement.x--;
			}

			int cell = level.pointToCell(itemPlacement);

			if (level.heaps.get( cell ) != null) {
				do {
					cell = level.pointToCell(random());
				} while (level.heaps.get( cell ) != null || level.findMob( cell ) != null);
			}

			level.drop( item, cell ).type = Heap.Type.FOR_SALE;
		}

	}
	
	protected static ArrayList<Item> generateItems() {

		ArrayList<Item> itemsToSpawn = new ArrayList<>();
		int basicItems = 8 +  Random.Int( 11 );
		int specialItems = 4 +  Random.Int( 6 );

		for(int i = 0;i<basicItems;i++){
			int rand = Random.Int( 4 );
			switch(rand){
				case 0:
					itemsToSpawn.add(Generator.random(Generator.Category.SEED));
					break;
				case 1:
					itemsToSpawn.add(Generator.random(Generator.Category.STONE));
					break;
				case 2:
					if(Random.Int( 2 )==0){
						itemsToSpawn.add(Generator.random(Generator.Category.SCROLL));
					}else{
						Scroll s = (Scroll)Generator.random(Generator.Category.SCROLL);
						try {
							itemsToSpawn.add(ExoticScroll.regToExo.get(s.getClass()).newInstance());
						}catch(Exception e){

						}
					}
					break;
				case 3:
					if(Random.Int( 2 )==0){
						itemsToSpawn.add(Generator.random(Generator.Category.POTION));
					}else{
						Potion p = (Potion)Generator.random(Generator.Category.POTION);
						try {
							itemsToSpawn.add(ExoticPotion.regToExo.get(p.getClass()).newInstance());
						}catch(Exception e){

						}
					}
					break;
			}
		}
		for(int i = 0;i<specialItems;i++){
			int rand = Random.Int( 11 );
			switch(rand){
				case 0:
					itemsToSpawn.add(Generator.random(Generator.Category.RING).identify());
					break;
				case 1:
					itemsToSpawn.add(Generator.random(Generator.Category.WAND).identify());
					break;
				case 2:
					itemsToSpawn.add(Generator.random(Generator.Category.WEAPON).identify());
					break;
				case 3:
					itemsToSpawn.add(Generator.random(Generator.Category.ARMOR).identify());
					break;
				case 4:
					itemsToSpawn.add(Generator.random(Generator.Category.MISSILE));
					break;
				case 5:
					itemsToSpawn.add(Generator.random(Generator.Category.RING).identify());
					break;
				case 6:
					itemsToSpawn.add(Generator.random(Generator.Category.WAND).identify());
					break;
				case 7:
					itemsToSpawn.add(Generator.random(Generator.Category.WEAPON).identify());
					break;
				case 8:
					itemsToSpawn.add(Generator.random(Generator.Category.ARMOR).identify());
					break;
				case 9:
					itemsToSpawn.add(Generator.random(Generator.Category.MISSILE));
					break;
				case 10:
					itemsToSpawn.add(Generator.random(Generator.Category.ARTIFACT).identify());
					break;
			}
		}

		Random.shuffle(itemsToSpawn);
		return itemsToSpawn;
	}

}
