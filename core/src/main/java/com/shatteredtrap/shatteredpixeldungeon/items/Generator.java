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

package com.shatteredtrap.shatteredpixeldungeon.items;

import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.ClothArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.LeatherArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.LeatherTrapperArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.LeatherWardenArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.MailArcaneArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.MailArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.MailWarArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.PlateArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.PlateBloodArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.PlateTimeArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.ScaleArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.ScaleCrystalArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.ScaleSolarArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.CapeOfThorns;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.ChaliceOfBlood;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.DriedRose;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.EtherealChains;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.HornOfPlenty;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.LloydsBeacon;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.MasterThievesArmband;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.SandalsOfNature;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.TalismanOfForesight;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.UnstableSpellbook;
import com.shatteredtrap.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredtrap.shatteredpixeldungeon.items.food.Food;
import com.shatteredtrap.shatteredpixeldungeon.items.food.MysteryMeat;
import com.shatteredtrap.shatteredpixeldungeon.items.food.Pasty;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfExperience;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfFrost;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfHaste;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfInvisibility;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfLevitation;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfMindVision;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfParalyticGas;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfPurity;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfStrength;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfToxicGas;
import com.shatteredtrap.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredtrap.shatteredpixeldungeon.items.rings.RingOfAccuracy;
import com.shatteredtrap.shatteredpixeldungeon.items.rings.RingOfElements;
import com.shatteredtrap.shatteredpixeldungeon.items.rings.RingOfEnergy;
import com.shatteredtrap.shatteredpixeldungeon.items.rings.RingOfEvasion;
import com.shatteredtrap.shatteredpixeldungeon.items.rings.RingOfForce;
import com.shatteredtrap.shatteredpixeldungeon.items.rings.RingOfFuror;
import com.shatteredtrap.shatteredpixeldungeon.items.rings.RingOfHaste;
import com.shatteredtrap.shatteredpixeldungeon.items.rings.RingOfMight;
import com.shatteredtrap.shatteredpixeldungeon.items.rings.RingOfSharpshooting;
import com.shatteredtrap.shatteredpixeldungeon.items.rings.RingOfTenacity;
import com.shatteredtrap.shatteredpixeldungeon.items.rings.RingOfWealth;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfRage;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfRetribution;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfTerror;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfTransmutation;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfAffection;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfAntiMagic;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfConfusion;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfDivination;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfEnchantment;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfForesight;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfMysticalEnergy;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPassage;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPetrification;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPolymorph;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPrismaticImage;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPsionicBlast;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.Runestone;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.StoneOfAffection;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.StoneOfAggression;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.StoneOfAugmentation;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.StoneOfBlast;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.StoneOfBlink;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.StoneOfClairvoyance;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.StoneOfDeepenedSleep;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.StoneOfDisarming;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.StoneOfEnchantment;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.StoneOfFlock;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.StoneOfIntuition;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.StoneOfShock;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.Wand;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfCorrosion;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfCorruption;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfDisintegration;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfFireblast;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfFrost;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfLightning;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfLivingEarth;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfMagicMissile;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfPrismaticLight;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfRegrowth;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfSturdyBolt;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfTransfusion;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfWarding;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.AssassinsBlade;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.BattleAxe;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Crossbow;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Dagger;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Dirk;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Flail;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Gauntlet;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Glaive;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Gloves;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Greataxe;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Greatshield;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Greatsword;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.HandAxe;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Longsword;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Mace;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.MagesStaff;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Quarterstaff;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.RoundShield;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.RunicBlade;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Sai;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Scimitar;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Shortsword;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Spear;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Sword;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.WarHammer;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Whip;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.WornShortsword;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.HeavyBoomerang;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.Bolas;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.FishingSpear;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.ForceCube;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.Javelin;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.Kunai;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.Shuriken;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.ThrowingClub;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.ThrowingHammer;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.ThrowingKnife;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.ThrowingSpear;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.ThrowingStone;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.Tomahawk;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.Trident;
import com.shatteredtrap.shatteredpixeldungeon.plants.Blindweed;
import com.shatteredtrap.shatteredpixeldungeon.plants.Dreamfoil;
import com.shatteredtrap.shatteredpixeldungeon.plants.Earthroot;
import com.shatteredtrap.shatteredpixeldungeon.plants.Fadeleaf;
import com.shatteredtrap.shatteredpixeldungeon.plants.Firebloom;
import com.shatteredtrap.shatteredpixeldungeon.plants.Icecap;
import com.shatteredtrap.shatteredpixeldungeon.plants.Plant;
import com.shatteredtrap.shatteredpixeldungeon.plants.Rotberry;
import com.shatteredtrap.shatteredpixeldungeon.plants.Sorrowmoss;
import com.shatteredtrap.shatteredpixeldungeon.plants.Starflower;
import com.shatteredtrap.shatteredpixeldungeon.plants.Stormvine;
import com.shatteredtrap.shatteredpixeldungeon.plants.Sungrass;
import com.shatteredtrap.shatteredpixeldungeon.plants.Swiftthistle;
import com.watabou.utils.Bundle;
import com.watabou.utils.GameMath;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Generator {

	public enum Category {
		WEAPON	( 6,    MeleeWeapon.class),
		WEP_T1	( 0,    MeleeWeapon.class),
		WEP_T2	( 0,    MeleeWeapon.class),
		WEP_T3	( 0,    MeleeWeapon.class),
		WEP_T4	( 0,    MeleeWeapon.class),
		WEP_T5	( 0,    MeleeWeapon.class),
		
		ARMOR	( 4,    Armor.class ),
		
		MISSILE ( 3,    MissileWeapon.class ),
		MIS_T1  ( 0,    MissileWeapon.class ),
		MIS_T2  ( 0,    MissileWeapon.class ),
		MIS_T3  ( 0,    MissileWeapon.class ),
		MIS_T4  ( 0,    MissileWeapon.class ),
		MIS_T5  ( 0,    MissileWeapon.class ),
		
		WAND	( 3,    Wand.class ),
		RING	( 1,    Ring.class ),
		ARTIFACT( 1,    Artifact.class),
		
		FOOD	( 0,    Food.class ),
		
		POTION	( 20,   Potion.class ),
		SEED	( 0,    Plant.Seed.class ), //dropped by grass
		
		SCROLL	( 20,   Scroll.class ),
		EXOTICSCROLL  ( 0,   Scroll.class ),
		STONE   ( 2,    Runestone.class),
		
		GOLD	( 18,   Gold.class );
		
		public Class<?>[] classes;
		public float[] probs;
		
		public float prob;
		public Class<? extends Item> superClass;
		
		private Category( float prob, Class<? extends Item> superClass ) {
			this.prob = prob;
			this.superClass = superClass;
		}
		
		public static int order( Item item ) {
			for (int i=0; i < values().length; i++) {
				if (values()[i].superClass.isInstance( item )) {
					return i;
				}
			}
			
			return item instanceof Bag ? Integer.MAX_VALUE : Integer.MAX_VALUE - 1;
		}
		
		private static final float[] INITIAL_ARTIFACT_PROBS = new float[]{ 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1};
		
		static {
			GOLD.classes = new Class<?>[]{
					Gold.class };
			GOLD.probs = new float[]{ 1 };
			
			POTION.classes = new Class<?>[]{
					PotionOfStrength.class, //2 drop every chapter, see Dungeon.posNeeded()
					PotionOfHealing.class,
					PotionOfMindVision.class,
					PotionOfFrost.class,
					PotionOfLiquidFlame.class,
					PotionOfToxicGas.class,
					PotionOfHaste.class,
					PotionOfInvisibility.class,
					PotionOfLevitation.class,
					PotionOfParalyticGas.class,
					PotionOfPurity.class,
					PotionOfExperience.class};
			POTION.probs = new float[]{ 0, 6, 4, 3, 3, 3, 2, 2, 2, 2, 2, 1 };
			
			SEED.classes = new Class<?>[]{
					Rotberry.Seed.class, //quest item
					Blindweed.Seed.class,
					Dreamfoil.Seed.class,
					Earthroot.Seed.class,
					Fadeleaf.Seed.class,
					Firebloom.Seed.class,
					Icecap.Seed.class,
					Sorrowmoss.Seed.class,
					Stormvine.Seed.class,
					Sungrass.Seed.class,
					Swiftthistle.Seed.class,
					Starflower.Seed.class};
			SEED.probs = new float[]{ 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 1 };
			
			SCROLL.classes = new Class<?>[]{
					ScrollOfUpgrade.class, //3 drop every chapter, see Dungeon.souNeeded()
					ScrollOfIdentify.class,
					ScrollOfRemoveCurse.class,
					ScrollOfMirrorImage.class,
					ScrollOfRecharging.class,
					ScrollOfTeleportation.class,
					ScrollOfLullaby.class,
					ScrollOfMagicMapping.class,
					ScrollOfRage.class,
					ScrollOfRetribution.class,
					ScrollOfTerror.class,
					ScrollOfTransmutation.class
			};
			SCROLL.probs = new float[]{ 0, 6, 4, 3, 3, 3, 2, 2, 2, 2, 2, 1 };

			EXOTICSCROLL.classes = new Class<?>[]{
					ScrollOfEnchantment.class, //3 drop every chapter, see Dungeon.souNeeded()
					ScrollOfDivination.class,
					ScrollOfAntiMagic.class,
					ScrollOfPrismaticImage.class,
					ScrollOfMysticalEnergy.class,
					ScrollOfPassage.class,
					ScrollOfAffection.class,
					ScrollOfForesight.class,
					ScrollOfConfusion.class,
					ScrollOfPsionicBlast.class,
					ScrollOfPetrification.class,
					ScrollOfPolymorph.class
			};
			EXOTICSCROLL.probs = new float[]{ 0, 6, 4, 3, 3, 3, 2, 2, 2, 2, 2, 1 };

			STONE.classes = new Class<?>[]{
					StoneOfEnchantment.class,   //1 is guaranteed to drop on floors 6-19
					StoneOfAugmentation.class,  //1 is sold in each shop
					StoneOfIntuition.class,     //1 additional stone is also dropped on floors 1-3
					StoneOfAggression.class,
					StoneOfAffection.class,
					StoneOfBlast.class,
					StoneOfBlink.class,
					StoneOfClairvoyance.class,
					StoneOfDeepenedSleep.class,
					StoneOfDisarming.class,
					StoneOfFlock.class,
					StoneOfShock.class
			};
			STONE.probs = new float[]{ 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

			WAND.classes = new Class<?>[]{
					WandOfMagicMissile.class,
					WandOfLightning.class,
					WandOfDisintegration.class,
					WandOfFireblast.class,
					WandOfCorrosion.class,
					WandOfBlastWave.class,
					WandOfLivingEarth.class,
					WandOfFrost.class,
					WandOfPrismaticLight.class,
					//WandOfWarding.class, all my homies hate warding
					WandOfSturdyBolt.class,
					WandOfTransfusion.class,
					WandOfCorruption.class,
					WandOfRegrowth.class };
			WAND.probs = new float[]{ 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3, 3, 3 };
			
			//see generator.randomWeapon
			WEAPON.classes = new Class<?>[]{};
			WEAPON.probs = new float[]{};
			
			WEP_T1.classes = new Class<?>[]{
					WornShortsword.class,
					Gloves.class,
					Dagger.class,
					MagesStaff.class
			};
			WEP_T1.probs = new float[]{ 1, 1, 1, 0 };
			
			WEP_T2.classes = new Class<?>[]{
					Shortsword.class,
					HandAxe.class,
					Spear.class,
					Quarterstaff.class,
					Dirk.class
			};
			WEP_T2.probs = new float[]{ 6, 5, 5, 4, 4 };
			
			WEP_T3.classes = new Class<?>[]{
					Sword.class,
					Mace.class,
					Scimitar.class,
					RoundShield.class,
					Sai.class,
					Whip.class
			};
			WEP_T3.probs = new float[]{ 6, 5, 5, 4, 4, 4 };
			
			WEP_T4.classes = new Class<?>[]{
					Longsword.class,
					BattleAxe.class,
					Flail.class,
					RunicBlade.class,
					AssassinsBlade.class,
					Crossbow.class
			};
			WEP_T4.probs = new float[]{ 6, 5, 5, 4, 4, 4 };
			
			WEP_T5.classes = new Class<?>[]{
					Greatsword.class,
					WarHammer.class,
					Glaive.class,
					Greataxe.class,
					Greatshield.class,
					Gauntlet.class
			};
			WEP_T5.probs = new float[]{ 6, 5, 5, 4, 4, 4 };
			
			//see Generator.randomArmor
			ARMOR.classes = new Class<?>[]{
					ClothArmor.class,
					LeatherArmor.class,
					MailArmor.class,
					ScaleArmor.class,
					PlateArmor.class };
			ARMOR.probs = new float[]{ 0, 0, 0, 0, 0 };
			
			//see Generator.randomMissile
			MISSILE.classes = new Class<?>[]{};
			MISSILE.probs = new float[]{};
			
			MIS_T1.classes = new Class<?>[]{
					ThrowingStone.class,
					ThrowingKnife.class
			};
			MIS_T1.probs = new float[]{ 6, 5 };
			
			MIS_T2.classes = new Class<?>[]{
					FishingSpear.class,
					ThrowingClub.class,
					Shuriken.class
			};
			MIS_T2.probs = new float[]{ 6, 5, 4 };
			
			MIS_T3.classes = new Class<?>[]{
					ThrowingSpear.class,
					Kunai.class,
					Bolas.class
			};
			MIS_T3.probs = new float[]{ 6, 5, 4 };
			
			MIS_T4.classes = new Class<?>[]{
					Javelin.class,
					Tomahawk.class,
					HeavyBoomerang.class
			};
			MIS_T4.probs = new float[]{ 6, 5, 4 };
			
			MIS_T5.classes = new Class<?>[]{
					Trident.class,
					ThrowingHammer.class,
					ForceCube.class
			};
			MIS_T5.probs = new float[]{ 6, 5, 4 };
			
			FOOD.classes = new Class<?>[]{
					Food.class,
					Pasty.class,
					MysteryMeat.class };
			FOOD.probs = new float[]{ 4, 1, 0 };
			
			RING.classes = new Class<?>[]{
					RingOfAccuracy.class,
					RingOfEvasion.class,
					RingOfElements.class,
					RingOfForce.class,
					RingOfFuror.class,
					RingOfHaste.class,
					RingOfEnergy.class,
					RingOfMight.class,
					RingOfSharpshooting.class,
					RingOfTenacity.class,
					RingOfWealth.class};
			RING.probs = new float[]{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
			
			ARTIFACT.classes = new Class<?>[]{
					CapeOfThorns.class,
					ChaliceOfBlood.class,
					CloakOfShadows.class,
					HornOfPlenty.class,
					MasterThievesArmband.class,
					SandalsOfNature.class,
					TalismanOfForesight.class,
					TimekeepersHourglass.class,
					UnstableSpellbook.class,
					AlchemistsToolkit.class,
					DriedRose.class,
					LloydsBeacon.class,
					EtherealChains.class
			};
			ARTIFACT.probs = INITIAL_ARTIFACT_PROBS.clone();
		}
	}

	private static final float[][] floorSetTierProbs = new float[][] {
			{0, 70, 20,  8,  2},
			{0, 25, 50, 20,  5},
			{0, 10, 40, 40, 10},
			{0,  5, 20, 50, 25},
			{0,  2,  8, 20, 70}
	};
	
	private static HashMap<Category,Float> categoryProbs = new LinkedHashMap<>();
	
	public static void reset() {
		for (Category cat : Category.values()) {
			categoryProbs.put( cat, cat.prob );
		}
	}
	
	public static Item random() {
		Category cat = Random.chances( categoryProbs );
		if (cat == null){
			reset();
			cat = Random.chances( categoryProbs );
		}
		categoryProbs.put( cat, categoryProbs.get( cat ) - 1);
		return random( cat );
	}
	
	public static Item random( Category cat ) {
		try {
			
			switch (cat) {
			case ARMOR:
				return randomArmor();
			case WEAPON:
				return randomWeapon();
			case MISSILE:
				return randomMissile();
			case ARTIFACT:
				Item item = randomArtifact();
				//if we're out of artifacts, return a ring instead.
				return item != null ? item : random(Category.RING);
			default:
				return ((Item)cat.classes[Random.chances( cat.probs )].newInstance()).random();
			}
			
		} catch (Exception e) {

			ShatteredPixelDungeon.reportException(e);
			return null;
			
		}
	}
	
	public static Item random( Class<? extends Item> cl ) {
		try {
			
			return ((Item)cl.newInstance()).random();
			
		} catch (Exception e) {

			ShatteredPixelDungeon.reportException(e);
			return null;
			
		}
	}

	public static Armor randomArmor(){
		return randomArmor(Dungeon.depth / 5);
	}
	
	public static Armor randomArmor(int floorSet) {

		floorSet = (int)GameMath.gate(0, floorSet, floorSetTierProbs.length-1);

		try {
			Armor a = (Armor)Category.ARMOR.classes[Random.chances(floorSetTierProbs[floorSet])].newInstance();

			//here is the spaghetti, for I am an elite programmer

			int roll = Random.Int(4);
			if (roll==0){
				if (a instanceof LeatherArmor){
					a = new LeatherTrapperArmor();
				}
				if (a instanceof MailArmor){
					a = new MailWarArmor();
				}
				if (a instanceof ScaleArmor){
					a = new ScaleCrystalArmor();
				}
				if (a instanceof PlateArmor){
					a = new PlateBloodArmor();
				}
			}
			if (roll==1){
				if (a instanceof LeatherArmor){
					a = new LeatherWardenArmor();
				}
				if (a instanceof MailArmor){
					a = new MailArcaneArmor();
				}
				if (a instanceof ScaleArmor){
					a = new ScaleSolarArmor();
				}
				if (a instanceof PlateArmor){
					a = new PlateTimeArmor();
				}
			}

			a.random();

			return a;
		} catch (Exception e) {
			ShatteredPixelDungeon.reportException(e);
			return null;
		}
	}

	public static final Category[] wepTiers = new Category[]{
			Category.WEP_T1,
			Category.WEP_T2,
			Category.WEP_T3,
			Category.WEP_T4,
			Category.WEP_T5
	};

	public static MeleeWeapon randomWeapon(){
		return randomWeapon(Dungeon.depth / 5);
	}
	
	public static MeleeWeapon randomWeapon(int floorSet) {

		floorSet = (int)GameMath.gate(0, floorSet, floorSetTierProbs.length-1);

		try {
			Category c = wepTiers[Random.chances(floorSetTierProbs[floorSet])];
			MeleeWeapon w = (MeleeWeapon)c.classes[Random.chances(c.probs)].newInstance();
			w.random();
			return w;
		} catch (Exception e) {
			ShatteredPixelDungeon.reportException(e);
			return null;
		}
	}
	
	public static final Category[] misTiers = new Category[]{
			Category.MIS_T1,
			Category.MIS_T2,
			Category.MIS_T3,
			Category.MIS_T4,
			Category.MIS_T5
	};
	
	public static MissileWeapon randomMissile(){
		return randomMissile(Dungeon.depth / 5);
	}
	
	public static MissileWeapon randomMissile(int floorSet) {
		
		floorSet = (int)GameMath.gate(0, floorSet, floorSetTierProbs.length-1);
		
		try {
			Category c = misTiers[Random.chances(floorSetTierProbs[floorSet])];
			MissileWeapon w = (MissileWeapon)c.classes[Random.chances(c.probs)].newInstance();
			w.random();
			return w;
		} catch (Exception e) {
			ShatteredPixelDungeon.reportException(e);
			return null;
		}
	}

	//enforces uniqueness of artifacts throughout a run.
	public static Artifact randomArtifact() {

		try {
			Category cat = Category.ARTIFACT;
			int i = Random.chances( cat.probs );

			//if no artifacts are left, return null
			if (i == -1){
				return null;
			}
			
			Class<?extends Artifact> art = (Class<? extends Artifact>) cat.classes[i];

			if (removeArtifact(art)) {
				Artifact artifact = art.newInstance();
				
				artifact.random();
				
				return artifact;
			} else {
				return null;
			}

		} catch (Exception e) {
			ShatteredPixelDungeon.reportException(e);
			return null;
		}
	}

	public static boolean removeArtifact(Class<?extends Artifact> artifact) {
		if (spawnedArtifacts.contains(artifact))
			return false;

		Category cat = Category.ARTIFACT;
		for (int i = 0; i < cat.classes.length; i++)
			if (cat.classes[i].equals(artifact)) {
				if (cat.probs[i] == 1){
					cat.probs[i] = 0;
					spawnedArtifacts.add(artifact);
					return true;
				} else
					return false;
			}

		return false;
	}

	//resets artifact probabilities, for new dungeons
	public static void initArtifacts() {
		Category.ARTIFACT.probs = Category.INITIAL_ARTIFACT_PROBS.clone();
		spawnedArtifacts = new ArrayList<>();
	}

	private static ArrayList<Class<?extends Artifact>> spawnedArtifacts = new ArrayList<>();
	
	private static final String GENERAL_PROBS = "general_probs";
	private static final String SPAWNED_ARTIFACTS = "spawned_artifacts";
	
	public static void storeInBundle(Bundle bundle) {
		Float[] genProbs = categoryProbs.values().toArray(new Float[0]);
		float[] storeProbs = new float[genProbs.length];
		for (int i = 0; i < storeProbs.length; i++){
			storeProbs[i] = genProbs[i];
		}
		bundle.put( GENERAL_PROBS, storeProbs);
		
		bundle.put( SPAWNED_ARTIFACTS, spawnedArtifacts.toArray(new Class[0]));
	}

	public static void restoreFromBundle(Bundle bundle) {
		if (bundle.contains(GENERAL_PROBS)){
			float[] probs = bundle.getFloatArray(GENERAL_PROBS);
			for (int i = 0; i < probs.length; i++){
				categoryProbs.put(Category.values()[i], probs[i]);
			}
		} else {
			reset();
		}
		
		initArtifacts();
		
		for ( Class<?extends Artifact> artifact : bundle.getClassArray(SPAWNED_ARTIFACTS) ){
			removeArtifact(artifact);
		}
		
	}
}
