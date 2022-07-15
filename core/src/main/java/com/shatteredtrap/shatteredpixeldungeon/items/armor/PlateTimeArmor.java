package com.shatteredtrap.shatteredpixeldungeon.items.armor;

import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class PlateTimeArmor extends Armor {

    {
        image = ItemSpriteSheet.ARMOR_PLATE_TIME;
    }

    public PlateTimeArmor() {
        super(5);
    }

    @Override
    public int STRReq(int lvl) {
        return super.STRReq(lvl) + 1;
    }

    @Override
    public String info() {
        return super.info()+"\n\n_Special:_ Weapon attacks have a 20% chance to happen instantly.";
    }
}
