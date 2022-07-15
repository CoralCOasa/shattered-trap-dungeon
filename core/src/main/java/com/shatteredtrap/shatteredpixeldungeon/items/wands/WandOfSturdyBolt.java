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
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Recharging;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Vulnerable;
import com.shatteredtrap.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredtrap.shatteredpixeldungeon.effects.SpellSprite;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.enchantments.Blazing;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.enchantments.Blocking;
import com.shatteredtrap.shatteredpixeldungeon.items.weapon.melee.MagesStaff;
import com.shatteredtrap.shatteredpixeldungeon.mechanics.Ballistica;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Callback;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class WandOfSturdyBolt extends DamageWand {

	{
		image = ItemSpriteSheet.WAND_STURDYBOLT;
	}

	public int min(int lvl){
		return 2+lvl;
	}

	public int max(int lvl){
		return 8+2*lvl;
	}

	public float percent(int lvl){
		return 100+25*lvl;
	}

	public int percentint(int lvl){
		return 100+25*lvl;
	}

	@Override
	protected void onZap( Ballistica bolt ) {
				
		Char ch = Actor.findChar( bolt.collisionPos );
		if (ch != null) {

			processSoulMark(ch, chargesPerCast());
			ch.damage(Math.round(Dungeon.hero.drRoll()*(percent(this.level())/100)), this);

			ch.sprite.burst(0x8D32A9, 3);

		} else {
			Dungeon.level.press(bolt.collisionPos, null, true);
		}
	}

	@Override
	public void onHit(MagesStaff staff, Char attacker, Char defender, int damage) {
		new Blocking().proc( staff, attacker, defender, damage);
	}

	@Override
	protected void fx(Ballistica bolt, Callback callback) {
		MagicMissile.boltFromChar(curUser.sprite.parent,
				MagicMissile.WARD,
				curUser.sprite,
				bolt.collisionPos,
				callback);
		Sample.INSTANCE.play(Assets.SND_ZAP);
	}

	@Override
	public String statsDesc() {
		if (levelKnown)
			return Messages.get(this, "stats_desc", percentint(this.level()));
		else
			return Messages.get(this, "stats_desc", percentint(0));
	}

	@Override
	public void staffFx(MagesStaff.StaffParticle particle) {
		particle.color( 0x0000BF );
		particle.am = 0.6f;
		particle.setLifespan(5f);
		particle.speed.polar(Random.Float(PointF.PI2), 0.3f);
		particle.setSize( 1f, 2f);
		particle.radiateXY(2.5f);
	}

	protected int initialCharges() {
		return 2;
	}

}
