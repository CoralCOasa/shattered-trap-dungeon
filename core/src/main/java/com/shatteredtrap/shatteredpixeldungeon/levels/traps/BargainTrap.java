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

package com.shatteredtrap.shatteredpixeldungeon.levels.traps;

import com.shatteredtrap.shatteredpixeldungeon.Assets;
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredtrap.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredtrap.shatteredpixeldungeon.effects.Speck;
import com.shatteredtrap.shatteredpixeldungeon.items.Generator;
import com.shatteredtrap.shatteredpixeldungeon.items.Item;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredtrap.shatteredpixeldungeon.items.potions.exotic.ExoticPotion;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.Scroll;
import com.shatteredtrap.shatteredpixeldungeon.items.scrolls.exotic.ExoticScroll;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class BargainTrap extends Trap {

	{
		color = YELLOW;
		shape = SNOWFLAKE;
	}

	@Override
	public void activate() {

		if (Dungeon.hero.pos == pos && !Dungeon.hero.flying){

			Item iitem = generateItem();
			float fff = Random.Float(1f,2.25f);
			if(iitem.price()/fff<=Dungeon.gold){
				CellEmitter.center( pos ).start( Speck.factory( Speck.COIN ), 0.1f, 8 );
				Sample.INSTANCE.play( Assets.SND_GOLD );
				Dungeon.gold-=iitem.price()/fff;
				Dungeon.level.drop( iitem, Dungeon.hero.pos ).sprite.drop();
				GLog.w("You purchased an awesome "+iitem.name()+ " for " +(int)(iitem.price()/fff)+" gold! Thank you for your business!");
			}else{
				GLog.w("You are too poor to afford this awesome "+iitem.name()+"! You are missing out!");
			}
		}
	}

	protected static Item generateItem() {

		int basicItems = 8 +  Random.Int( 11 );
		int specialItems = 4 +  Random.Int( 6 );

		if(Random.Int(2)==0){
			for(int i = 0;i<basicItems;i++){
				int rand = Random.Int( 4 );
				switch(rand){
					case 0:
						return(Generator.random(Generator.Category.SEED));
					case 1:
						return(Generator.random(Generator.Category.STONE));
					case 2:
						if(Random.Int( 2 )==0){
							return(Generator.random(Generator.Category.SCROLL));
						}else{
							Scroll s = (Scroll)Generator.random(Generator.Category.SCROLL);
							try {
								return(ExoticScroll.regToExo.get(s.getClass()).newInstance());
							}catch(Exception e){

							}
						}
					case 3:
						if(Random.Int( 2 )==0){
							return(Generator.random(Generator.Category.POTION));
						}else{
							Potion p = (Potion)Generator.random(Generator.Category.POTION);
							try {
								return(ExoticPotion.regToExo.get(p.getClass()).newInstance());
							}catch(Exception e){

							}
						}
				}
			}
		}

		for(int i = 0;i<specialItems;i++){
			int rand = Random.Int( 11 );
			switch(rand){
				case 0:
					return(Generator.random(Generator.Category.RING).identify());

				case 1:
					return(Generator.random(Generator.Category.WAND).identify());

				case 2:
					return(Generator.random(Generator.Category.WEAPON).identify());

				case 3:
					return(Generator.random(Generator.Category.ARMOR).identify());

				case 4:
					return(Generator.random(Generator.Category.MISSILE));

				case 5:
					return(Generator.random(Generator.Category.RING).identify());

				case 6:
					return(Generator.random(Generator.Category.WAND).identify());

				case 7:
					return(Generator.random(Generator.Category.WEAPON).identify());

				case 8:
					return(Generator.random(Generator.Category.ARMOR).identify());

				case 9:
					return(Generator.random(Generator.Category.MISSILE));
				case 10:
					return(Generator.random(Generator.Category.ARTIFACT).identify());
			}
		}

		return null;
	}
}
