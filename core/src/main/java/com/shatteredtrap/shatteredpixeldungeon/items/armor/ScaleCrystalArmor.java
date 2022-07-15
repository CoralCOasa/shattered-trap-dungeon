package com.shatteredtrap.shatteredpixeldungeon.items.armor;

import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSpriteSheet;

public class ScaleCrystalArmor extends Armor {

    {
        image = ItemSpriteSheet.ARMOR_SCALE_CRYSTAL;
    }

    public ScaleCrystalArmor() {
        super(4);
    }

    @Override
    public int STRReq(int lvl) {
        return super.STRReq(lvl) + 1;
    }

    @Override
    public String info() {
        return super.info()+"\n\n_Special:_ Increases accuracy by 25%.";
    }
}
