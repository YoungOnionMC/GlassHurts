package com.youngonion.glasshurts;

import com.youngonion.glasshurts.items.GlassShard;
import com.youngonion.glasshurts.items.LeatherGloves;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME, acceptedMinecraftVersions = "[1.12.2]")
public class GlassHurts {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MODID);

    public static GlassShard GLASS_SHARDS = new GlassShard();
    public static LeatherGloves LEATHER_GLOVES = new LeatherGloves();

    @EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc. (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        // register to the event bus so that we can listen to events
        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("I am " + Tags.MODNAME + " + at version " + Tags.VERSION);
    }

    @SubscribeEvent
    // Register recipes here (Remove if not needed)
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        IForgeRegistry<IRecipe> r = event.getRegistry();
        GameRegistry.addShapelessRecipe(new ResourceLocation("glass_shard_upcraft"), new ResourceLocation(Tags.MODID), new ItemStack(Blocks.GLASS),
                Ingredient.fromItems(GLASS_SHARDS), Ingredient.fromItems(GLASS_SHARDS), Ingredient.fromItems(GLASS_SHARDS), Ingredient.fromItems(GLASS_SHARDS));

        GameRegistry.addShapedRecipe(new ResourceLocation("leather_gloves_craft"), new ResourceLocation(Tags.MODID), new ItemStack(LEATHER_GLOVES),
                "LL ", "LSN", "LI ",
                'L', Ingredient.fromItems(Items.LEATHER),
                'S', Ingredient.fromItems(Items.STRING),
                'N', Ingredient.fromItems(Items.IRON_NUGGET),
                'I', Ingredient.fromItems(Items.IRON_INGOT));
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(GLASS_SHARDS, 0, new ModelResourceLocation(GLASS_SHARDS.getRegistryName(), ""));
        ModelLoader.setCustomModelResourceLocation(LEATHER_GLOVES, 0, new ModelResourceLocation(LEATHER_GLOVES.getRegistryName(), ""));
    }

    @SubscribeEvent
    // Register items here (Remove if not needed)
    public void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        registry.register(GLASS_SHARDS);
        registry.register(LEATHER_GLOVES);
    }

    @SubscribeEvent
    // Register blocks here (Remove if not needed)
    public void registerBlocks(RegistryEvent.Register<Block> event) {

    }

    @EventHandler
    // load "Do your mod setup. Build whatever data structures you care about." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
    }

    @EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
    }

    @EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
    }
}
