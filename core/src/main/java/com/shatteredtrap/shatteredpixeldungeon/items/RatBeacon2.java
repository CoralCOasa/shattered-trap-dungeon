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

import com.shatteredtrap.shatteredpixeldungeon.Assets;
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.Actor;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredtrap.shatteredpixeldungeon.actors.blobs.Regrowth;
import com.shatteredtrap.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Goblin;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.SpectralSamurai;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.SpectralSummoner;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.SpectralWizard;
import com.shatteredtrap.shatteredpixeldungeon.effects.Flare;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.shatteredtrap.shatteredpixeldungeon.levels.Terrain;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.AlarmTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.AnnihilationTrap;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.BargainTrap;
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
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.MultiTrap;
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
import com.shatteredtrap.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredtrap.shatteredpixeldungeon.scenes.InterlevelScene;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredtrap.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class RatBeacon2 extends Item {
	public static final String AC_CHANNEL = "CHANNEL";
	{
		image = ItemSpriteSheet.RATBEACON2;
		unique = true;
		defaultAction = AC_CHANNEL;
	}

	@Override
	public ArrayList<String> actions(Hero hero ) {
		ArrayList<String> actions = new ArrayList<>();
		actions.add( AC_CHANNEL );
		return actions;
	}

	@Override
	public void execute(final Hero hero, String action ) {
		Sample.INSTANCE.play(Assets.SND_BEACON, 1.2f, 1.2f, 0.7f);
		//spawn spectral rat
		Mob test;
		int ratId = Random.Int(3);
		if(ratId==1){
			test = new SpectralSamurai();
		}else if(ratId==2){
			test = new SpectralWizard();
		}else{
			test = new SpectralSummoner();
		}
		test.state = test.WANDERING;
		do {
			test.pos = Random.Int(Dungeon.level.length());
		} while (
				Dungeon.level.solid[test.pos] ||
						Dungeon.level.pit[test.pos] ||
						Dungeon.level.distance(test.pos, Dungeon.hero.pos) < 8 ||
						Actor.findChar(test.pos) != null);
		GameScene.add( test );
		//spawn traps
		for (int i = 0; i < Dungeon.level.length(); i++){
			if (Dungeon.level.map[i] == Terrain.EMPTY && Random.Int( 15 ) == 0) {
				Class<? extends Trap> trapClass;
				trapClass = Random.oneOf(levelTraps[0]);
				Trap t = null;
				try {
					t = (Trap) trapClass.newInstance();
				} catch (IllegalAccessException e) {
				} catch (InstantiationException e) {
				}
				Dungeon.level.map[i] = Terrain.SECRET_TRAP;
				t.reveal();
				t.hide();
				Dungeon.level.setTrap(t, i);
				//ScrollOfMagicMapping.discover(i );
				if(t.visible){
					GameScene.discoverTile( i, Dungeon.level.map[i] );
					Dungeon.level.discover( i );
				}
			}
		}
		new Flare( 4, 70 ).color( 0xD69632, true ).show( hero.sprite, 4f );
		hero.spendAndNext(1f);
		GameScene.flash(0xFFFF00);
		GLog.w("The beacon energizes the dungeon!");
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
					TransmutationTrap.class, RearmTrap.class, BargainTrap.class, MultiTrap.class},
	};

	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public boolean isIdentified() {
		return true;
	}
}
