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

package com.shatteredtrap.shatteredpixeldungeon.actors.hero;

import com.shatteredtrap.shatteredpixeldungeon.Assets;
import com.shatteredtrap.shatteredpixeldungeon.Badges;
import com.shatteredtrap.shatteredpixeldungeon.Challenges;
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.items.BrokenSeal;
import com.shatteredtrap.shatteredpixeldungeon.items.DemonBeacon;
import com.shatteredtrap.shatteredpixeldungeon.items.Item;
import com.shatteredtrap.shatteredpixeldungeon.items.RatBeacon1;
import com.shatteredtrap.shatteredpixeldungeon.items.RatBeacon2;
import com.shatteredtrap.shatteredpixeldungeon.items.RatDust;
import com.shatteredtrap.shatteredpixeldungeon.items.SpectralGem;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.ClothArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.LeatherTrapperArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.LeatherWardenArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.MailArcaneArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.MailArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.MailWarArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.PlateBloodArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.PlateTimeArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.ScaleCrystalArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.ScaleSolarArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.AlchemistsToolkit;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.CloakOfShadows;
import com.shatteredtrap.shatteredpixeldungeon.items.bags.PotionBandolier;
import com.shatteredtrap.shatteredpixeldungeon.items.bags.ScrollHolder;
import com.shatteredtrap.shatteredpixeldungeon.items.bags.VelvetPouch;
import com.shatteredtrap.shatteredpixeldungeon.items.bags.WeaponRack;
import com.shatteredtrap.shatteredpixeldungeon.items.food.Blandfruit;
import com.shatteredtrap.shatteredpixeldungeon.items.food.Food;
import com.shatteredtrap.shatteredpixeldungeon.items.food.SmallRation;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfInvisibility;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfMindVision;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfStrength;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.elixirs.ElixirOfTrapSkill;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfIdentify;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfLullaby;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfRage;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.shatteredtrap.shatteredpixeldungeon.items.spells.BeaconOfReturning;
import com.shatteredtrap.shatteredpixeldungeon.items.spells.CurseInfusion;
import com.shatteredtrap.shatteredpixeldungeon.items.spells.MagicalPorter;
import com.shatteredtrap.shatteredpixeldungeon.items.spells.ReclaimTrap;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.StoneOfIntuition;
import com.shatteredtrap.shatteredpixeldungeon.items.stones.TreasureBag;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfFireblast;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfMagicMissile;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.WandOfSturdyBolt;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.SpiritBow;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Dagger;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.Gloves;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.MagesStaff;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.WornShortsword;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.ThrowingKnife;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.ThrowingStone;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.watabou.utils.Bundle;
import com.watabou.utils.DeviceCompat;

public enum HeroClass {

	WARRIOR( "warrior", HeroSubClass.BERSERKER, HeroSubClass.GLADIATOR ),
	MAGE( "mage", HeroSubClass.BATTLEMAGE, HeroSubClass.WARLOCK ),
	ROGUE( "rogue", HeroSubClass.ASSASSIN, HeroSubClass.FREERUNNER ),
	HUNTRESS( "huntress", HeroSubClass.SNIPER, HeroSubClass.WARDEN );

	private String title;
	private HeroSubClass[] subClasses;

	HeroClass( String title, HeroSubClass...subClasses ) {
		this.title = title;
		this.subClasses = subClasses;
	}

	public void initHero( Hero hero ) {

		hero.heroClass = this;

		initCommon( hero );

		switch (this) {
			case WARRIOR:
				initWarrior( hero );
				break;

			case MAGE:
				initMage( hero );
				break;

			case ROGUE:
				initRogue( hero );
				break;

			case HUNTRESS:
				initHuntress( hero );
				break;
		}
		
	}

	private static void initCommon( Hero hero ) {
		Item i = new ClothArmor().identify();
		//duDungeon.gold = 200;
		//new LeatherTrapperArmor().identify().collect();
		//new LeatherWardenArmor().identify().upgrade().collect();
		//new MailWarArmor().identify().collect();
		//new MailArcaneArmor().identify().collect();
		//new ScaleCrystalArmor().identify().collect();
		//new ScaleSolarArmor().identify().collect();
		//new PlateTimeArmor().identify().collect();
		//new PlateBloodArmor().identify().collect();
		//new PotionOfStrength().quantity(20).identify().collect();
		//new ReclaimTrap().quantity(12).collect();
		//new ScrollOfMagicMapping().identify().collect();
		//new WandOfSturdyBolt().identify().upgrade().collect();
		//new ElixirOfTrapSkill().quantity(6).collect();
		//new TreasureBag().quantity(63).collect();
		//new WandOfMagicMissile().upgrade(20).identify().collect();
		//new ClothArmor().upgrade(80).identify().collect();
		//new WandOfSturdyBolt().identify().upgrade().collect();
		//new RatBeacon2().collect();
		//new BeaconOfReturning().quantity(4).collect();
		//new RatDust().collect();
		//new SpectralGem().collect();
		//new PotionOfMindVision().identify().collect();
		//new DemonBeacon().quantity(3).collect();
		if (!Challenges.isItemBlocked(i)) hero.belongings.armor = (ClothArmor)i;

		i = new Blandfruit().imbuePotion(new PotionOfHealing() );
		if (!Challenges.isItemBlocked(i)) i.collect();

		if (Dungeon.isChallenged(Challenges.NO_FOOD)){
			new SmallRation().collect();
		}
		new ScrollOfIdentify().identify();
		//new ElixirOfTrapSkill().quantity(10).collect();
		//new ReclaimTrap().quantity(10).collect();
	}

	public Badges.Badge masteryBadge() {
		switch (this) {
			case WARRIOR:
				return Badges.Badge.MASTERY_WARRIOR;
			case MAGE:
				return Badges.Badge.MASTERY_MAGE;
			case ROGUE:
				return Badges.Badge.MASTERY_ROGUE;
			case HUNTRESS:
				return Badges.Badge.MASTERY_HUNTRESS;
		}
		return null;
	}

	private static void initWarrior( Hero hero ) {
		(hero.belongings.weapon = new WornShortsword()).identify();
		ThrowingStone stones = new ThrowingStone();
		stones.quantity(3).collect();
		Dungeon.quickslot.setSlot(0, stones);

		if (hero.belongings.armor != null){
			hero.belongings.armor.affixSeal(new BrokenSeal());
		}
		
		new PotionBandolier().collect();
		Dungeon.LimitedDrops.POTION_BANDOLIER.drop();
		
		new PotionOfHealing().identify();
		new ScrollOfRage().identify();
	}

	private static void initMage( Hero hero ) {
		MagesStaff staff;
		
		staff = new MagesStaff(new WandOfMagicMissile());

		(hero.belongings.weapon = staff).identify();
		hero.belongings.weapon.activate(hero);

		Dungeon.quickslot.setSlot(0, staff);

		new ScrollHolder().collect();
		Dungeon.LimitedDrops.SCROLL_HOLDER.drop();
		
		new ScrollOfUpgrade().identify();
		new PotionOfLiquidFlame().identify();
	}

	private static void initRogue( Hero hero ) {
		(hero.belongings.weapon = new Dagger()).identify();

		CloakOfShadows cloak = new CloakOfShadows();
		(hero.belongings.misc1 = cloak).identify();
		hero.belongings.misc1.activate( hero );

		ThrowingKnife knives = new ThrowingKnife();
		knives.quantity(3).collect();

		Dungeon.quickslot.setSlot(0, cloak);
		Dungeon.quickslot.setSlot(1, knives);

		new VelvetPouch().collect();
		Dungeon.LimitedDrops.VELVET_POUCH.drop();
		
		new ScrollOfMagicMapping().identify();
		new PotionOfInvisibility().identify();
	}

	private static void initHuntress( Hero hero ) {

		(hero.belongings.weapon = new Gloves()).identify();
		SpiritBow bow = new SpiritBow();
		bow.identify().collect();

		Dungeon.quickslot.setSlot(0, bow);

		new VelvetPouch().collect();
		Dungeon.LimitedDrops.VELVET_POUCH.drop();
		
		new PotionOfMindVision().identify();
		new ScrollOfLullaby().identify();
	}
	
	public String title() {
		return Messages.get(HeroClass.class, title);
	}
	
	public HeroSubClass[] subClasses() {
		return subClasses;
	}
	
	public String spritesheet() {
		switch (this) {
			case WARRIOR: default:
				return Assets.WARRIOR;
			case MAGE:
				return Assets.MAGE;
			case ROGUE:
				return Assets.ROGUE;
			case HUNTRESS:
				return Assets.HUNTRESS;
		}
	}
	
	public String[] perks() {
		switch (this) {
			case WARRIOR: default:
				return new String[]{
						Messages.get(HeroClass.class, "warrior_perk1"),
						Messages.get(HeroClass.class, "warrior_perk2"),
						Messages.get(HeroClass.class, "warrior_perk3"),
						Messages.get(HeroClass.class, "warrior_perk4"),
						Messages.get(HeroClass.class, "warrior_perk5"),
				};
			case MAGE:
				return new String[]{
						Messages.get(HeroClass.class, "mage_perk1"),
						Messages.get(HeroClass.class, "mage_perk2"),
						Messages.get(HeroClass.class, "mage_perk3"),
						Messages.get(HeroClass.class, "mage_perk4"),
						Messages.get(HeroClass.class, "mage_perk5"),
				};
			case ROGUE:
				return new String[]{
						Messages.get(HeroClass.class, "rogue_perk1"),
						Messages.get(HeroClass.class, "rogue_perk2"),
						Messages.get(HeroClass.class, "rogue_perk3"),
						Messages.get(HeroClass.class, "rogue_perk4"),
						Messages.get(HeroClass.class, "rogue_perk5"),
				};
			case HUNTRESS:
				return new String[]{
						Messages.get(HeroClass.class, "huntress_perk1"),
						Messages.get(HeroClass.class, "huntress_perk2"),
						Messages.get(HeroClass.class, "huntress_perk3"),
						Messages.get(HeroClass.class, "huntress_perk4"),
						Messages.get(HeroClass.class, "huntress_perk5"),
				};
		}
	}
	
	public boolean isUnlocked(){
		//always unlock on debug builds
		if (DeviceCompat.isDebug()) return true;
		
		switch (this){
			case WARRIOR: default:
				return true;
			case MAGE:
				return true;
			case ROGUE:
				return true;
			case HUNTRESS:
				return true;
		}
	}
	
	public String unlockMsg() {
		switch (this){
			case WARRIOR: default:
				return "";
			case MAGE:
				return Messages.get(HeroClass.class, "mage_unlock");
			case ROGUE:
				return Messages.get(HeroClass.class, "rogue_unlock");
			case HUNTRESS:
				return Messages.get(HeroClass.class, "huntress_unlock");
		}
	}

	private static final String CLASS	= "class";
	
	public void storeInBundle( Bundle bundle ) {
		bundle.put( CLASS, toString() );
	}
	
	public static HeroClass restoreInBundle( Bundle bundle ) {
		String value = bundle.getString( CLASS );
		return value.length() > 0 ? valueOf( value ) : ROGUE;
	}
}
