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

package com.shatteredtrap.shatteredpixeldungeon.items.wands;

import com.shatteredtrap.shatteredpixeldungeon.Assets;
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.Actor;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Chill;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.FrostMeteor;
import com.shatteredtrap.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredtrap.shatteredpixeldungeon.items.Heap;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.MagesStaff;
import com.shatteredtrap.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class WandOfFrost extends DamageWand {

	{
		image = ItemSpriteSheet.WAND_FROST;
	}

	public int min(int lvl){
		return 2+lvl;
	}

	public int max(int lvl){
		return 8+5*lvl;
	}

	@Override
	protected void onZap(Ballistica bolt) {

		Heap heap = Dungeon.level.heaps.get(bolt.collisionPos);
		if (heap != null) {
			heap.freeze();
		}

		Char ch = Actor.findChar(bolt.collisionPos);
		if (ch != null){

			int damage = damageRoll();

			if (ch.buff(Frost.class) != null){
				return; //do nothing, can't affect a frozen target
			}
			if (ch.buff(Chill.class) != null){
				//7.5% less damage per turn of chill remaining
				float chill = ch.buff(Chill.class).cooldown();
				damage = (int)Math.round(damage * Math.pow(0.9f, chill));
			} else {
				ch.sprite.burst( 0xFF99CCFF, level() / 2 + 2 );
			}

			if(ch instanceof FrostMeteor){
				damage = 0;
			}
			processSoulMark(ch, chargesPerCast());
			ch.damage(damage, this);

			if (ch.isAlive()){
				if (Dungeon.level.water[ch.pos])
					Buff.prolong(ch, Chill.class, 4+level());
				else
					Buff.prolong(ch, Chill.class, 2+level());
			}
		} else {
			Dungeon.level.press(bolt.collisionPos, null, true);
		}
	}

	@Override
	protected void fx(Ballistica bolt, Callback callback) {
		MagicMissile.boltFromChar(curUser.sprite.parent,
				MagicMissile.FROST,
				curUser.sprite,
				bolt.collisionPos,
				callback);
		Sample.INSTANCE.play(Assets.SND_ZAP);
	}

	@Override
	public void onHit(MagesStaff staff, Char attacker, Char defender, int damage) {
		Chill chill = defender.buff(Chill.class);
		if (chill != null && Random.IntRange(2, 10) <= chill.cooldown()){
			//need to delay this through an actor so that the freezing isn't broken by taking damage from the staff hit.
			new FlavourBuff(){
				{actPriority = VFX_PRIO;}
				public boolean act() {
					Buff.affect(target, Frost.class, Frost.duration(target) * Random.Float(1f, 2f));
					return super.act();
				}
			}.attachTo(defender);
		}
	}

	@Override
	public void staffFx(MagesStaff.StaffParticle particle) {
		particle.color(0x88CCFF);
		particle.am = 0.6f;
		particle.setLifespan(2f);
		float angle = Random.Float(PointF.PI2);
		particle.speed.polar( angle, 2f);
		particle.acc.set( 0f, 1f);
		particle.setSize( 0f, 1.5f);
		particle.radiateXY(Random.Float(1f));
	}

}
