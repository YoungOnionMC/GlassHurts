package com.youngonion.glasshurts.items;

import com.youngonion.glasshurts.Tags;
import net.minecraft.item.Item;

public class LeatherGloves extends Item {

    public static final int MAX_DURABILITY = 250;

    public LeatherGloves() {
        setRegistryName(Tags.MODID, "leather_gloves");
        setTranslationKey(Tags.MODID + ".leather_gloves");
        setMaxDamage(MAX_DURABILITY);
    }
}
