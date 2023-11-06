package com.youngonion.glasshurts.event;

import com.youngonion.glasshurts.GlassHurts;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Random;

import static net.minecraftforge.fml.common.eventhandler.EventPriority.HIGH;

@Mod.EventBusSubscriber
public class BlockBreak {

    @SubscribeEvent(priority = HIGH)
    public static void onBlockHarvest(BlockEvent.HarvestDropsEvent event) {
        IBlockState state = event.getState();
        if(state == Blocks.GLASS.getDefaultState()) {
            Random r = new Random();
            ItemStack shards = new ItemStack(GlassHurts.GLASS_SHARDS, r.nextInt(3));
            event.getDrops().add(0, shards);
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {

        IBlockState state = event.getState();
        EntityPlayer player = event.getPlayer();
        ItemStack stack = player.getHeldItemMainhand();
        ItemStack offStack = player.getHeldItem(EnumHand.OFF_HAND);
        boolean damagePlayer = false;
        // TODO: maybe check for oredict of glass blocks
        if(state == Blocks.GLASS.getDefaultState() || state.getBlock() == Blocks.STAINED_GLASS || state == Blocks.GLASS_PANE.getDefaultState() || state.getBlock() == Blocks.STAINED_GLASS_PANE) {
            if((stack.getItem().getToolClasses(stack).contains("pickaxe") || EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) > 0)) {
                return;
            }

            if(!stack.isEmpty() && stack.getItem() == GlassHurts.LEATHER_GLOVES) {
                stack.damageItem(1, player);
                if(player.getCooldownTracker().hasCooldown(stack.getItem()))
                    damagePlayer = true;
                else
                    player.getCooldownTracker().setCooldown(stack.getItem(), 35);
            }
            else if(!offStack.isEmpty() && offStack.getItem() == GlassHurts.LEATHER_GLOVES) {
                offStack.damageItem(1, player);
                if(player.getCooldownTracker().hasCooldown(offStack.getItem()))
                    damagePlayer = true;
                else
                    player.getCooldownTracker().setCooldown(offStack.getItem(), 35);
            } else {
                damagePlayer = true;
            }

            if(damagePlayer) {
                player.attackEntityFrom(GlassDamageSource.GLASS_DAMAGE, 3);
                player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 90, 3));
            }
        }
    }
}
