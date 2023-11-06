package com.youngonion.glasshurts.items;

import com.youngonion.glasshurts.Tags;
import com.youngonion.glasshurts.event.GlassDamageSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GlassShard extends ItemFood {

    public GlassShard() {
        super(0, 0, false);
        setRegistryName(Tags.MODID, "glass_shard");
        setTranslationKey(Tags.MODID + ".glass_shard");
        setAlwaysEdible();
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        entityLiving.attackEntityFrom(GlassDamageSource.GLASS_EATING_DAMAGE, 4.0f);

        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
