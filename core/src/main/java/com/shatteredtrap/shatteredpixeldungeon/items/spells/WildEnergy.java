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

package com.shatteredtrap.shatteredpixeldungeon.items.spells;

import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.ArtifactRecharge;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Recharging;
import com.shatteredtrap.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredtrap.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredtrap.shatteredpixeldungeon.items.quest.MetalShard;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.ScrollOfRecharging;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfMysticalEnergy;
import com.shatteredtrap.shatteredpixeldungeon.items.wands.CursedWand;
import com.shatteredtrap.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Callback;

public class WildEnergy extends TargetedSpell {
	
	{
		image = ItemSpriteSheet.WILD_ENERGY;
	}
	
	//we rely on cursedWand to do fx instead
	@Override
	protected void fx(Ballistica bolt, Callback callback) {
		affectTarget(bolt, curUser);
	}
	
	@Override
	protected void affectTarget(Ballistica bolt, final Hero hero) {
		CursedWand.cursedZap(this, hero, bolt, new Callback() {
			@Override
			public void call() {
				ScrollOfRecharging.charge(hero);

				hero.belongings.charge(1f);
				for (int i = 0; i < 4; i++){
					if (hero.belongings.misc1 instanceof Artifact) ((Artifact) hero.belongings.misc1).charge(hero);
					if (hero.belongings.misc2 instanceof Artifact) ((Artifact) hero.belongings.misc2).charge(hero);
				}

				Buff.affect(hero, Recharging.class, 8f);
				Buff.affect(hero, ArtifactRecharge.class).prolong( 8 );
				
				detach( curUser.belongings.backpack );
				updateQuickslot();
				curUser.spendAndNext( 1f );
			}
		});
	}
	
	@Override
	public int price() {
		//prices of ingredients, divided by output quantity
		return Math.round(quantity * ((50 + 100) / 5f));
	}
	
	public static class Recipe extends com.shatteredtrap.shatteredpixeldungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{ScrollOfMysticalEnergy.class, MetalShard.class};
			inQuantity = new int[]{1, 1};
			
			cost = 8;
			
			output = WildEnergy.class;
			outQuantity = 5;
		}
		
	}
}
