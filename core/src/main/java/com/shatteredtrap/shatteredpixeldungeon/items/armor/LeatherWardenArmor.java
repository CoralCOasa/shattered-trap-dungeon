package com.shatteredtrap.shatteredpixeldungeon.items.armor;

import com.shatteredtrap.shatteredpixeldungeon.Dungeon;
import com.shatteredtrap.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredtrap.shatteredpixeldungeon.levels.Terrain;
import com.shatteredtrap.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class LeatherWardenArmor extends Armor {

    {
        image = ItemSpriteSheet.ARMOR_LEATHER_NATURE;
    }

    public LeatherWardenArmor() {
        super(2);
    }

    @Override
    public int STRReq(int lvl) {
        return super.STRReq(lvl) + 1;
    }

    @Override
    public String info() {
        return super.info() + "\n\n_Special:_ Increases effective armor tier by 2-3 when standing on grass.";
    }

    @Override
    public int DRMax(int lvl) {
        int tierLevel = tier;
        if (Dungeon.level.map[Dungeon.hero.pos] == Terrain.GRASS) {
            tierLevel += 2 + Random.Int(2);
        }
        int max = tierLevel * (2 + lvl) + augment.defenseFactor(lvl);
        if (lvl > max) {
            return ((lvl - max) + 1) / 2;
        } else {
            return max;
        }
    }
}
