package com.dna.everythingisbad.proxy;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.gui.GuiHandler;
import com.dna.everythingisbad.init.*;
import com.dna.everythingisbad.network.PacketHandler;
import com.dna.everythingisbad.utils.ConfigLoader;
import com.dna.everythingisbad.utils.FluidCache;
import com.dna.everythingisbad.utils.handlers.PlayerInteractionHandler;
import com.dna.everythingisbad.world.StructureGenerator;
import com.dna.everythingisbad.world.WorldGenAloeGenerator;
import com.dna.everythingisbad.world.WorldGenModOres;
import com.dna.everythingisbad.world.WorldGenQuestionMarkBlockGenerator;
import com.dna.everythingisbad.world.trees.WorldGenHappyTreeGenerator;
import com.dna.everythingisbad.world.water.WorldGenBloodGenerator;
import com.dna.everythingisbad.world.water.WorldGenGodsPeeGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy implements IProxy{
    public void registerItemRenderer(Item item, int meta, String id) {}

    @Override
    public void registerModel(Item item, int metadata) {

    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {

        Main.logger.info("Pre Intializing");
        ModFluids.register();
        ModFluids.registerBlocks();
        ModItems.init();
        ModBlocks.init();
        ModBlocks.initOreDictionary();
        ModItems.initOreDictionary();

        ModDimensions.registerDimensions();
        ModBiomes.registerBiomes();
        new ConfigLoader();
    }

    @Override
    public void init(FMLInitializationEvent event) {

        Main.logger.info("Intializing");
        ModTileEntities.register();
        NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
        PacketHandler.init();

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

        Main.logger.info("Post Intializing");
        ModSmeltingRecipes.init();
        ModBrewingRecipes.init();
        ModPotions.init();
        //World Generation Registration
        GameRegistry.registerWorldGenerator(WorldGenHappyTreeGenerator.INSTANCE,100);
        GameRegistry.registerWorldGenerator(WorldGenGodsPeeGenerator.INSTANCE,100);
        GameRegistry.registerWorldGenerator(WorldGenQuestionMarkBlockGenerator.INSTANCE,1);
        GameRegistry.registerWorldGenerator(WorldGenAloeGenerator.INSTANCE,1);
        GameRegistry.registerWorldGenerator(WorldGenBloodGenerator.INSTANCE,30);
        GameRegistry.registerWorldGenerator(StructureGenerator.INSTANCE,30);
        GameRegistry.registerWorldGenerator(WorldGenModOres.INSTANCE,101);
        MinecraftForge.EVENT_BUS.register(new PlayerInteractionHandler());
        ModEntities.init();
        FluidCache.init();
        DryerRecipes.init();
        LiquifierRecipes.init();

    }
}
