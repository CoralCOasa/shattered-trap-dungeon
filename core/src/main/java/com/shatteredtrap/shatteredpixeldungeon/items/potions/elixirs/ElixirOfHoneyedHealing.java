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

import com.shatteredtrap.shatteredpixeldungeon.Assets;
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.Actor;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Healing;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredtrap.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Bee;
import com.shatteredtrap.shatteredpixeldungeon.items.Honeypot;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;

public class ElixirOfHoneyedHealing extends Elixir {
	
	{
		image = ItemSpriteSheet.ELIXIR_HONEY;
	}
	
	@Override
	public void apply(Hero hero) {
		Buff.affect( hero, Healing.class ).setHeal((int)(0.8f*hero.HT + 14), 0.25f, 0);
		PotionOfHealing.cure(hero);
		Buff.affect(hero, Hunger.class).satisfy(Hunger.STARVING/5f);
	}
	
	@Override
	public void shatter(int cell) {
		if (Dungeon.level.heroFOV[cell]) {
			Sample.INSTANCE.play( Assets.SND_SHATTER );
			splash( cell );
		}
		
		Char ch = Actor.findChar(cell);
		if (ch != null){
			Buff.affect( ch, Healing.class ).setHeal((int)(0.8f*ch.HT + 14), 0.25f, 0);
			PotionOfHealing.cure(ch);
			if (ch instanceof Bee && ch.alignment != curUser.alignment){
				ch.alignment = Char.Alignment.ALLY;
				((Bee)ch).setPotInfo(-1, null);
				
			}
		}
	}
	
	@Override
	public int price() {
		//prices of ingredients
		return quantity * (30 + 5);
	}
	
	public static class Recipe extends com.shatteredtrap.shatteredpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{PotionOfHealing.class, Honeypot.ShatteredPot.class};
			inQuantity = new int[]{1, 1};
			
			cost = 4;
			
			output = ElixirOfHoneyedHealing.class;
			outQuantity = 1;
		}
		
	}
}
