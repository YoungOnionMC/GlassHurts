package com.youngonion.glasshurts.event;

import net.minecraft.util.DamageSource;

public class GlassDamageSource {

    public static DamageSource GLASS_DAMAGE = new DamageSource("glass_damage").setDamageBypassesArmor();
    public static DamageSource GLASS_EATING_DAMAGE = new DamageSource("glass_eating_damage").setDamageBypassesArmor();
}
