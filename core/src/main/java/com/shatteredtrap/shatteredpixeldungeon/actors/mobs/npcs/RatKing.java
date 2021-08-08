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

package com.shatteredtrap.shatteredpixeldungeon.actors.mobs.npcs;

import com.shatteredtrap.shatteredpixeldungeon.Assets;
import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.Actor;
import com.shatteredtrap.shatteredpixeldungeon.actors.Char;
import com.shatteredtrap.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Clown;
import com.shatteredtrap.shatteredpixeldungeon.actors.mobs.Guard;
import com.shatteredtrap.shatteredpixeldungeon.effects.particles.ElmoParticle;
import com.shatteredtrap.shatteredpixeldungeon.items.DemonBeacon;
import com.shatteredtrap.shatteredpixeldungeon.items.RatBeacon1;
import com.shatteredtrap.shatteredpixeldungeon.items.RatBeacon2;
import com.shatteredtrap.shatteredpixeldungeon.levels.traps.AlarmTrap;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredtrap.shatteredpixeldungeon.sprites.RatKingSprite;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class RatKing extends NPC {

	{
		spriteClass = RatKingSprite.class;
		
		state = SLEEPING;
	}
	
	@Override
	public int defenseSkill( Char enemy ) {
		return 1000;
	}
	
	@Override
	public float speed() {
		return 2f;
	}
	
	@Override
	protected Char chooseEnemy() {
		return null;
	}
	
	@Override
	public void damage( int dmg, Object src ) {
	}
	
	@Override
	public void add( Buff buff ) {
	}
	
	@Override
	public boolean reset() {
		return true;
	}
	
	@Override
	public boolean interact() {
		RatBeacon1 beacon = Dungeon.hero.belongings.getItem( RatBeacon1.class );
		sprite.turnTo( pos, Dungeon.hero.pos );
		if (state == SLEEPING) {
			notice();
			yell( Messages.get(this, "not_sleeping") );
			state = WANDERING;
			Clown test = new Clown();
			test.pos = Dungeon.level.randomRespawnCell();
			GameScene.add( test );
			Sample.INSTANCE.play(Assets.SND_ALERT);
		} else if(beacon!=null) {
			beacon.detach(Dungeon.hero.belongings.backpack);
			new RatBeacon2().collect();
			yell("Ah, so it's finally time to start the ritual. Good luck..." );
			this.sprite.emitter().burst( ElmoParticle.FACTORY, 12 );
			die(null);
		}else{
			yell( Messages.get(this, "what_is_it") );
		}
		return true;
	}
	
	@Override
	public String description() {
		return ((RatKingSprite)sprite).festive ?
				Messages.get(this, "desc_festive")
				: super.description();
	}
}
