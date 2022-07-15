package com.shatteredtrap.shatteredpixeldungeon.items.armor;

import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class MailArcaneArmor extends Armor {

    {
        image = ItemSpriteSheet.ARMOR_MAIL_ARCANE;
    }

    public MailArcaneArmor() {
        super(3);
    }

    @Override
    public int STRReq(int lvl) {
        return super.STRReq(lvl) + 1;
    }

    @Override
    public String info() {
        return super.info()+"\n\n_Special:_ Increases natural wand recharging rate by 25%.";
    }
}
