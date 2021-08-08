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

package com.shatteredtrap.shatteredpixeldungeon.items.potions.elixirs;

import com.shatteredtrap.shatteredpixeldungeon.Badges;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.ArcaneArmor;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Trapskill;
import com.shatteredtrap.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfStrength;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.exotic.PotionOfEarthenArmor;
import com.shatteredtrap.shatteredpixeldungeon.items.quest.GooBlob;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredtrap.shatteredpixeldungeon.utils.GLog;

public class ElixirOfTrapSkill extends Elixir {
	
	{
		image = ItemSpriteSheet.ELIXIR_TRAPSKILL;
	}
	
	@Override
	public void apply(Hero hero) {
		GLog.p( "Trapskill increased!" );
		hero.increaseTrapSkill();
		hero.setCurrentTrapSkill(hero.getTrapSkill());
		hero.setCurrentTrapSkill2(hero.getTrapSkill());
		hero.sprite.showStatus( CharSprite.POSITIVE, "+1 trapskill" );
		//Buff.prolong( hero, Trapskill.class, 999999f );
		Badges.validateTrapSkillAttained();
		updateQuickslot();
	}
	
	@Override
	public int price() {
		//prices of ingredients
		return quantity * (100);
	}

	public static class Recipe extends com.shatteredtrap.shatteredpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{PotionOfStrength.class, ScrollOfUpgrade.class};
			inQuantity = new int[]{1, 1};
			
			cost = 10;
			
			output = ElixirOfTrapSkill.class;
			outQuantity = 1;
		}
		
	}
}
