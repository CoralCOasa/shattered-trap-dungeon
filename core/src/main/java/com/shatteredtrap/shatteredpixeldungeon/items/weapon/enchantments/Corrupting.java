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

package com.shatteredtrap.shatteredpixeldungeon.items.weapon.enchantments;

import com.shatteredtrap.shatteredpixeldungeon.Badges;
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.Statistics;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Corruption;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.PinCushion;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.SoulMark;
import com.shatteredtrap.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSprite;
import com.watabou.utils.Random;

public class Corrupting extends Weapon.Enchantment {
	
	private static ItemSprite.Glowing BLACK = new ItemSprite.Glowing( 0x440066 );
	
	@Override
	public int proc(Weapon weapon, Char attacker, Char defender, int damage) {
		if (defender.buff(Corruption.class) != null || !(defender instanceof Mob)) return damage;
		
		int level = Math.max( 0, weapon.level() );
		
		// lvl 0 - 15%
		// lvl 1 ~ 17%
		// lvl 2 ~ 19%
		if (damage >= defender.HP
				&& !defender.isImmune(Corruption.class)
				&& Random.Int( level + 40 ) >= 34){
			
			Mob enemy = (Mob) defender;
			Hero hero = (attacker instanceof Hero) ? (Hero) attacker : Dungeon.hero;
			
			enemy.HP = enemy.HT;
			for (Buff buff : enemy.buffs()) {
				if (buff.type == Buff.buffType.NEGATIVE
						&& !(buff instanceof SoulMark)) {
					buff.detach();
				} else if (buff instanceof PinCushion){
					buff.detach();
				}
			}
			if (enemy.alignment == Char.Alignment.ENEMY){
				enemy.rollToDropLoot();
			}
			
			Buff.affect(enemy, Corruption.class);
			
			Statistics.enemiesSlain++;
			Badges.validateMonstersSlain();
			Statistics.qualifiedForNoKilling = false;
			if (enemy.EXP > 0 && hero.lvl <= enemy.maxLvl) {
				hero.sprite.showStatus(CharSprite.POSITIVE, Messages.get(enemy, "exp", enemy.EXP));
				hero.earnExp(enemy.EXP, enemy.getClass());
			} else {
				hero.earnExp(0, enemy.getClass());
			}
			
			return 0;
		}
		
		return damage;
	}
	
	@Override
	public ItemSprite.Glowing glowing() {
		return BLACK;
	}
}
