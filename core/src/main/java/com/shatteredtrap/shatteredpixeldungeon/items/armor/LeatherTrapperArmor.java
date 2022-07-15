package com.shatteredtrap.shatteredpixeldungeon.items.armor;

import com.shatteredtrap.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredtrap.shatteredpixeldungeon.items.BrokenSeal;
import com.shatteredtrap.shatteredpixeldungeon.messages.Messages;
import com.shatteredtrap.shatteredpixeldungeon.sprites.HeroSprite;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredtrap.shatteredpixeldungeon.utils.GLog;

public class LeatherTrapperArmor extends Armor {

    {
        image = ItemSpriteSheet.ARMOR_LEATHER_TRAP;
    }

    public LeatherTrapperArmor() {
        super(2);
    }

    @Override
    public int STRReq(int lvl) {
        return super.STRReq(lvl) + 1;
    }

    @Override
    public boolean doEquip(Hero hero) {

        if (super.doEquip(hero)) {
            hero.increaseTrapSkill();
            updateQuickslot();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean doUnequip(Hero hero, boolean collect, boolean single) {
        if (super.doUnequip(hero, collect, single)) {
            hero.decreaseTrapSkill();
            updateQuickslot();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String info() {
        return super.info()+"\n\n_Special:_ Increases trapskill by 1 when worn.";
    }
}
