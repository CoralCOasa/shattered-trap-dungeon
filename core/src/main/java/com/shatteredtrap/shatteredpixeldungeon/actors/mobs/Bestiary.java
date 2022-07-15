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

package com.shatteredtrap.shatteredpixeldungeon.actors.mobs;

import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Arrays;

public class Bestiary {
	
	public static ArrayList<Class<? extends Mob>> getMobRotation( int depth ){
		ArrayList<Class<? extends Mob>> mobs = standardMobRotation( depth );
		addRareMobs(depth, mobs);
		swapMobAlts(mobs);
		Random.shuffle(mobs);
		return mobs;
	}
	
	//returns a rotation of standard mobs, unshuffled.
	private static ArrayList<Class<? extends Mob>> standardMobRotation( int depth ){
		switch(depth){
			
			// Sewers
			case 1: default:
				//10x rat
				return new ArrayList<Class<? extends Mob>>(Arrays.asList(
						//KeyChain.class));
						Rat.class, Rat.class, Rat.class, Rat.class, Rat.class,
						Rat.class, Rat.class, DancingSkeleton.class, DancingSkeleton.class, DancingSkeleton.class));
			case 2:
				//3x rat, 3x gnoll
				return new ArrayList<>(Arrays.asList(Rat.class, Rat.class, Rat.class,
						Gnoll.class, Gnoll.class, Gnoll.class, FilthJet.class, DancingSkeleton.class));
			case 3:
				//2x rat, 4x gnoll, 1x crab, 1x swarm
				return new ArrayList<>(Arrays.asList(Rat.class, Rat.class,
						Gnoll.class, Gnoll.class, Gnoll.class, Gnoll.class,
						Crab.class, Swarm.class, Orc.class, FilthJet.class, FilthJet.class, DancingSkeleton.class));
			case 4: case 5:
				//1x rat, 2x gnoll, 3x crab, 1x swarm
				return new ArrayList<>(Arrays.asList(Rat.class,
						Gnoll.class, Gnoll.class,
						Crab.class, Crab.class, Crab.class,
						Swarm.class, Orc.class, Orc.class, FilthJet.class));
				
			// Prison
			case 6:
				//3x skeleton, 1x thief, 1x swarm
				return new ArrayList<>(Arrays.asList(Skeleton.class, Skeleton.class, Skeleton.class,
						Thief.class,
						Swarm.class, ToughGhost.class));
			case 7:
				//3x skeleton, 1x thief, 1x shaman, 1x guard
				return new ArrayList<>(Arrays.asList(Skeleton.class, Skeleton.class, Skeleton.class,
						Thief.class,
						Shaman.class,
						Guard.class, Assasin.class, ToughGhost.class));
			case 8:
				//3x skeleton, 1x thief, 2x shaman, 2x guard
				return new ArrayList<>(Arrays.asList(Skeleton.class, Skeleton.class, Skeleton.class,
						Thief.class,
						Shaman.class, Shaman.class,
						Guard.class, Guard.class, Assasin.class, Assasin.class, KeyChain.class));
			case 9: case 10:
				//3x skeleton, 1x thief, 2x shaman, 3x guard
				return new ArrayList<>(Arrays.asList(Skeleton.class, Skeleton.class, Skeleton.class,
						Thief.class,
						Shaman.class, Shaman.class,
						Guard.class, Guard.class, Guard.class, Assasin.class, KeyChain.class, KeyChain.class));
				
			// Caves
			case 11:
				//5x bat, 1x brute
				return new ArrayList<>(Arrays.asList(
						Bat.class, Bat.class, Bat.class, Bat.class, BombSpider.class, BombSpider.class,
						Brute.class, DartSpitter.class));
			case 12:
				//5x bat, 5x brute, 1x spinner
				return new ArrayList<>(Arrays.asList(
						Bat.class, Bat.class, Bat.class, Bat.class, Bat.class,
						Brute.class, Brute.class, Brute.class, Brute.class, Brute.class,
						Spinner.class, DartSpitter.class, DartSpitter.class, BombSpider.class, BombSpider.class));
			case 13:
				//1x bat, 3x brute, 1x shaman, 1x spinner
				return new ArrayList<>(Arrays.asList(
						Bat.class,
						Brute.class, Brute.class, Brute.class,
						Shaman.class,
						Spinner.class, DartSpitter.class, DartSpitter.class, BombSpider.class, ShockerBlock.class));
			case 14: case 15:
				//1x bat, 3x brute, 1x shaman, 4x spinner
				return new ArrayList<>(Arrays.asList(
						Bat.class,
						Brute.class, Brute.class, Brute.class,
						Shaman.class,
						Spinner.class, Spinner.class, Spinner.class, Spinner.class, DartSpitter.class, ShockerBlock.class, ShockerBlock.class, ShockerBlock.class));
				
			// City
			case 16:
				//5x elemental, 5x warlock, 1x monk
				return new ArrayList<>(Arrays.asList(
						Elemental.class, Elemental.class, Elemental.class, Elemental.class,
						Warlock.class, Warlock.class, Warlock.class, Warlock.class, Warlock.class,
						Monk.class, FrostMeteor.class, FrostMeteor.class, BombSpider2.class));
			case 17:
				//2x elemental, 2x warlock, 2x monk
				return new ArrayList<>(Arrays.asList(
						Elemental.class, Elemental.class,
						Warlock.class, Warlock.class,
						Monk.class, Monk.class, ChaosElemental.class, FrostMeteor.class, BombSpider2.class));
			case 18:
				//1x elemental, 1x warlock, 2x monk, 1x golem
				return new ArrayList<>(Arrays.asList(
						Elemental.class,
						Warlock.class,
						Monk.class, Monk.class,
						Golem.class, ChaosElemental.class, FrostMeteor.class, BombSpider2.class));
			case 19: case 20:
				//1x elemental, 1x warlock, 2x monk, 3x golem
				return new ArrayList<>(Arrays.asList(
						Elemental.class,
						Warlock.class,
						Monk.class, Monk.class,
						Golem.class, Golem.class, Golem.class, ChaosElemental.class, FrostMeteor.class));
				
			// Halls
			case 21: case 22:
				//3x succubus, 3x evil eye
				return new ArrayList<>(Arrays.asList(
						Succubus.class, Succubus.class, Succubus.class,
						Eye.class, Eye.class, Eye.class, DemonRat.class, DemonRat.class, Prismancer.class, Hole.class));
			case 23:
				//2x succubus, 4x evil eye, 2x scorpio
				return new ArrayList<>(Arrays.asList(
						Succubus.class, Succubus.class,
						Eye.class, Eye.class, Eye.class, Eye.class,
						Scorpio.class, Scorpio.class, DemonRat.class, DemonRat.class, Prismancer.class, Hole.class));
			case 24: case 25: case 26:
				//1x succubus, 2x evil eye, 3x scorpio
				return new ArrayList<>(Arrays.asList(
						Succubus.class,
						Eye.class, Eye.class,
						Scorpio.class, Scorpio.class, Scorpio.class, DemonRat.class, DemonRat.class, Prismancer.class, Prismancer.class, Hole.class));
		}
		
	}
	
	//has a chance to add a rarely spawned mobs to the rotation
	public static void addRareMobs( int depth, ArrayList<Class<?extends Mob>> rotation ){
		
		switch (depth){
			
			// Sewers
			default:
				return;
			case 4:
				if (Random.Float() < 0.01f) rotation.add(Skeleton.class);
				if (Random.Float() < 0.01f) rotation.add(Thief.class);
				if (Random.Float() < 0.2f) rotation.add(DancingSkeleton.class);
				return;
				
			// Prison
			case 6:
				if (Random.Float() < 0.15f) rotation.add(Assasin.class);
				if (Random.Float() < 0.2f)  rotation.add(Shaman.class);
				if (Random.Float() < 0.3f)  rotation.add(Crab.class);
				return;
			case 7:
				if (Random.Float() < 0.15f)  rotation.add(KeyChain.class);
				return;
			case 8:
				if (Random.Float() < 0.15f)  rotation.add(ToughGhost.class);
				if (Random.Float() < 0.02f) rotation.add(Bat.class);
				return;
			case 9:
				if (Random.Float() < 0.02f) rotation.add(Bat.class);
				if (Random.Float() < 0.01f) rotation.add(Brute.class);
				if (Random.Float() < 0.07f)  rotation.add(ToughGhost.class);
				return;
				
			// Caves
			case 11:
				if (Random.Float() < 0.3f)  rotation.add(Guard.class);
				return;
			case 12:
				if (Random.Float() < 0.2f) rotation.add(ShockerBlock.class);
				return;
			case 13:
				if (Random.Float() < 0.02f) rotation.add(Elemental.class);
				return;
			case 14:
				if (Random.Float() < 0.02f) rotation.add(Elemental.class);
				if (Random.Float() < 0.01f) rotation.add(Monk.class);
				if (Random.Float() < 0.15f) rotation.add(BombSpider.class);
				return;
				
			// City
			case 16:
				if (Random.Float() < 0.3f)  rotation.add(Spinner.class);
				if (Random.Float() < 0.2f)  rotation.add(ChaosElemental.class);
				return;
			case 19:
				if (Random.Float() < 0.02f) rotation.add(Succubus.class);
				if (Random.Float() < 0.25f) rotation.add(BombSpider2.class);
				return;
				//halls
			case 22:
				if (Random.Float() < 0.3f)  rotation.add(Golem.class);
				if (Random.Float() < 0.15f)  rotation.add(ChaosElemental.class);
				return;
			case 23:
				if (Random.Float() < 0.15f)  rotation.add(ChaosElemental.class);
				return;
		}
	}
	
	//switches out regular mobs for their alt versions when appropriate
	private static void swapMobAlts(ArrayList<Class<?extends Mob>> rotation){
		for (int i = 0; i < rotation.size(); i++){
			if (Random.Int( 50 ) == 0) {
				Class<? extends Mob> cl = rotation.get(i);
				if (cl == Rat.class) {
					cl = Albino.class;
				} else if (cl == Thief.class) {
					cl = Bandit.class;
				} else if (cl == Brute.class) {
					cl = Shielded.class;
				} else if (cl == Monk.class) {
					cl = Senior.class;
				} else if (cl == Scorpio.class) {
					cl = Acidic.class;
				}
				rotation.set(i, cl);
			}
		}
	}
}
