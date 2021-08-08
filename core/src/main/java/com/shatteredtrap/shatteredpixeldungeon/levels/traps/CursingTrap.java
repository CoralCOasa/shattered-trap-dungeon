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
import com.shatteredtrap.shatteredpixeldungeon.Badges;
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.Statistics;
import com.shatteredtrap.shatteredpixeldungeon.actors.Actor;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Corruption;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.PinCushion;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.SoulMark;
import com.shatteredtrap.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Crab;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredtrap.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredtrap.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredtrap.shatteredpixeldungeon.items.EquipableItem;
import com.shatteredtrap.shatteredpixeldungeon.items.Heap;
import com.shatteredtrap.shatteredpixeldungeon.items.Item;
import com.shatteredtrap.shatteredpixeldungeon.items.KindOfWeapon;
import com.shatteredtrap.shatteredpixeldungeon.items.KindofMisc;
import com.shatteredtrap.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.missiles.MissileWeapon;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredtrap.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

import java.util.ArrayList;
import java.util.Collections;

public class CursingTrap extends Trap {

	{
		color = VIOLET;
		shape = WAVES;
	}

	@Override
	public void activate() {
		if (Dungeon.level.heroFOV[ pos ]) {
			CellEmitter.get(pos).burst(ShadowParticle.UP, 5);
			Sample.INSTANCE.play(Assets.SND_CURSED);
		}

		Heap heap = Dungeon.level.heaps.get( pos );
		if (heap != null){
			for (Item item : heap.items){
				if (item.isUpgradable() && !(item instanceof MissileWeapon))
					curse(item);
			}
		}

		if (Dungeon.hero.pos == pos && !Dungeon.hero.flying){
			curse(Dungeon.hero);
		}
		Char cha = Actor.findChar(pos);
		Mob ch = new Crab();
		if (cha instanceof Mob){
			ch = (Mob)cha;
		}
		if(cha instanceof Mob && !ch.properties().contains(Char.Property.BOSS)&& !ch.isImmune(Corruption.class)){
			ch.HP = ch.HT;
			for (Buff buff : ch.buffs()) {
				if (buff.type == Buff.buffType.NEGATIVE
						&& !(buff instanceof SoulMark)) {
					buff.detach();
				} else if (buff instanceof PinCushion){
					buff.detach();
				}
			}
			if (ch.alignment == Char.Alignment.ENEMY){
				ch.rollToDropLoot();
			}

			Buff.affect(ch, Corruption.class);

			Statistics.enemiesSlain++;
			Badges.validateMonstersSlain();
			Statistics.qualifiedForNoKilling = false;
			if (ch.EXP > 0 && Dungeon.hero.lvl <= ch.maxLvl) {
				Dungeon.hero.sprite.showStatus(CharSprite.POSITIVE, Messages.get(ch, "exp", ch.EXP));
				Dungeon.hero.earnExp(ch.EXP, ch.getClass());
			} else {
				Dungeon.hero.earnExp(0, ch.getClass());
			}
		}
	}

	public static void curse(Hero hero){
		//items the trap wants to curse because it will create a more negative effect
		ArrayList<Item> priorityCurse = new ArrayList<>();
		//items the trap can curse if nothing else is available.
		ArrayList<Item> canCurse = new ArrayList<>();

		KindOfWeapon weapon = hero.belongings.weapon;
		if (weapon instanceof Weapon && !weapon.cursed){
			if (((Weapon) weapon).enchantment == null)
				priorityCurse.add(weapon);
			else
				canCurse.add(weapon);
		}

		Armor armor = hero.belongings.armor;
		if (armor != null && !armor.cursed){
			if (armor.glyph == null)
				priorityCurse.add(armor);
			else
				canCurse.add(armor);
		}

		KindofMisc misc1 = hero.belongings.misc1;
		if (misc1 != null){
			canCurse.add(misc1);
		}

		KindofMisc misc2 = hero.belongings.misc2;
		if (misc2 != null){
			canCurse.add(misc2);
		}

		Collections.shuffle(priorityCurse);
		Collections.shuffle(canCurse);

		//int numCurses = Random.Int(2) == 0 ? 1 : 2;
		int numCurses = 1;

		for (int i = 0; i < numCurses; i++){
			if (!priorityCurse.isEmpty()){
				curse(priorityCurse.remove(0));
			} else if (!canCurse.isEmpty()){
				curse(canCurse.remove(0));
			}
		}

		EquipableItem.equipCursed(hero);
		GLog.n( Messages.get(CursingTrap.class, "curse") );
	}

	private static void curse(Item item){
		item.cursed = item.cursedKnown = true;

		if (item instanceof Weapon){
			Weapon w = (Weapon) item;
			if (w.enchantment == null){
				w.enchant(Weapon.Enchantment.randomCurse());
			}
		}
		if (item instanceof Armor){
			Armor a = (Armor) item;
			if (a.glyph == null){
				a.inscribe(Armor.Glyph.randomCurse());
			}
		}
	}
}
