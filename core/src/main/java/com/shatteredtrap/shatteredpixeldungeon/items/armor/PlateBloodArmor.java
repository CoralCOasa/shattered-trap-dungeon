package com.shatteredtrap.shatteredpixeldungeon.items.armor;

import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class PlateBloodArmor extends Armor {

    {
        image = ItemSpriteSheet.ARMOR_PLATE_BLOOD;
    }

    public PlateBloodArmor() {
        super(5);
    }

    @Override
    public int STRReq(int lvl) {
        return super.STRReq(lvl) + 1;
    }

    @Override
    public String info() {
        return super.info()+"\n\n_Special:_ Doubles natural health regeneration.";
    }
}
