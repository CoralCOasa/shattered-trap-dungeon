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

package com.shatteredtrap.shatteredpixeldungeon.items.potions;

import com.shatteredtrap.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredtrap.shatteredpixeldungeon.items.Item;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.exotic.ExoticPotion;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredtrap.shatteredpixeldungeon.plants.Plant;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.HashMap;

public class AlchemicalCatalyst extends Potion {
	
	{
		image = ItemSpriteSheet.POTION_CATALYST;
		
	}
	
	private static HashMap<Class<? extends Potion>, Float> potionChances = new HashMap<>();
	static{
		potionChances.put(PotionOfHealing.class,        3f);
		potionChances.put(PotionOfMindVision.class,     2f);
		potionChances.put(PotionOfFrost.class,          2f);
		potionChances.put(PotionOfLiquidFlame.class,    2f);
		potionChances.put(PotionOfToxicGas.class,       2f);
		potionChances.put(PotionOfHaste.class,          2f);
		potionChances.put(PotionOfInvisibility.class,   2f);
		potionChances.put(PotionOfLevitation.class,     2f);
		potionChances.put(PotionOfParalyticGas.class,   2f);
		potionChances.put(PotionOfPurity.class,         2f);
		potionChances.put(PotionOfExperience.class,     1f);
	}
	
	@Override
	public void apply(Hero hero) {
		try {
			Potion p = Random.chances(potionChances).newInstance();
			p.anonymize();
			p.apply(hero);
		} catch (Exception e) {
			ShatteredPixelDungeon.reportException(e);
		}
	}
	
	@Override
	public void shatter(int cell) {
		try {
			Potion p = Random.chances(potionChances).newInstance();
			p.anonymize();
			curItem = p;
			p.shatter(cell);
		} catch (Exception e) {
			ShatteredPixelDungeon.reportException(e);
		}
	}
	
	@Override
	public boolean isKnown() {
		return true;
	}
	
	@Override
	public int price() {
		return 40 * quantity;
	}
	
	public static class Recipe extends com.shatteredtrap.shatteredpixeldungeon.items.Recipe {
		
		@Override
		public boolean testIngredients(ArrayList<Item> ingredients) {
			boolean potion = false;
			boolean secondary = false;
			
			for (Item i : ingredients){
				if (i instanceof Plant.Seed || i instanceof Runestone){
					secondary = true;
				//if it is a regular or exotic potion
				} else if (ExoticPotion.regToExo.containsKey(i.getClass())
						|| ExoticPotion.regToExo.containsValue(i.getClass())) {
					potion = true;
				}
			}
			
			return potion && secondary;
		}
		
		@Override
		public int cost(ArrayList<Item> ingredients) {
			for (Item i : ingredients){
				if (i instanceof Plant.Seed){
					return 1;
				} else if (i instanceof Runestone){
					return 2;
				}
			}
			return 1;
		}
		
		@Override
		public Item brew(ArrayList<Item> ingredients) {
			
			for (Item i : ingredients){
				i.quantity(i.quantity()-1);
			}
			
			return sampleOutput(null);
		}
		
		@Override
		public Item sampleOutput(ArrayList<Item> ingredients) {
			return new AlchemicalCatalyst();
		}
	}
	
}
