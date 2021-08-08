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

import com.shatteredtrap.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.AlchemicalCatalyst;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfParalyticGas;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.brews.ShockingBrew;
import com.shatteredtrap.shatteredpixeldungeon.levels.SewerBossLevel;
import com.shatteredtrap.shatteredpixeldungeon.scenes.InterlevelScene;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.Game;

import java.util.ArrayList;

public class RatBeacon1 extends Item {
	public static final String AC_WARP = "WARP";
	{
		image = ItemSpriteSheet.RATBEACON1;
		unique = true;
		defaultAction = AC_WARP;
	}

	@Override
	public ArrayList<String> actions(Hero hero ) {
		ArrayList<String> actions = new ArrayList<>();
		actions.add( AC_WARP );
		return actions;
	}

	@Override
	public void execute(final Hero hero, String action ) {
		InterlevelScene.mode = InterlevelScene.Mode.RETURN;
		InterlevelScene.returnDepth = 5;
		InterlevelScene.returnPos = -1;
		Game.switchScene( InterlevelScene.class );
	}

	public static class Recipe extends com.shatteredtrap.shatteredpixeldungeon.items.Recipe.SimpleRecipe {

		{
			inputs =  new Class[]{DemonBeacon.class};
			inQuantity = new int[]{3};

			cost = 20;

			output = RatBeacon1.class;
			outQuantity = 1;
		}

	}

	@Override
	public boolean isUpgradable() {
		return false;
	}
	
	@Override
	public boolean isIdentified() {
		return true;
	}
}
