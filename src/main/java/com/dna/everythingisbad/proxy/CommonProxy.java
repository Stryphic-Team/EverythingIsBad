package com.dna.everythingisbad.proxy;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.*;
import com.dna.everythingisbad.network.PacketHandler;
import com.dna.everythingisbad.world.trees.WorldGenHappyTreeGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy implements IProxy{
    public void registerItemRenderer(Item item, int meta, String id) {}

    @Override
    public void preInit(FMLPreInitializationEvent event) {

        Main.logger.info("Pre Intializing");
        ModFluids.register();
        ModFluids.registerBlocks();
        ModBlocks.init();
        ModEntities.init();



    }

    @Override
    public void init(FMLInitializationEvent event) {

        Main.logger.info("Intializing");
        PacketHandler.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

        Main.logger.info("Post Intializing");
        ModSmeltingRecipes.init();
        ModPotions.init();
        //ModFluids.init();
        GameRegistry.registerWorldGenerator(WorldGenHappyTreeGenerator.INSTANCE,100);

    }
}
