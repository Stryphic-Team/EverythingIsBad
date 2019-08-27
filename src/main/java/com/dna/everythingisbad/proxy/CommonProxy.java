package com.dna.everythingisbad.proxy;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.*;
import com.dna.everythingisbad.network.PacketHandler;
import com.dna.everythingisbad.utils.ConfigLoader;
import com.dna.everythingisbad.utils.handlers.PlayerInteractionHandler;
import com.dna.everythingisbad.world.trees.WorldGenHappyTreeGenerator;
import com.dna.everythingisbad.world.water.WorldGenGodsPeeGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
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
        new ConfigLoader();
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
        //World Generation Registration
        GameRegistry.registerWorldGenerator(WorldGenHappyTreeGenerator.INSTANCE,100);
        GameRegistry.registerWorldGenerator(WorldGenGodsPeeGenerator.INSTANCE,100);
        MinecraftForge.EVENT_BUS.register(new PlayerInteractionHandler());
        ModEntities.init();
//        Biome.BiomeProperties properties = new Biome.BiomeProperties("poop_biome");
//        properties.setBaseHeight(40);
//        properties.setBaseBiome("poop_biome");
//        properties.setWaterColor(0xff0000);
//
//        ForgeRegistries.BIOMES.register(new BiomePoop(properties));
//        BiomeManager.addSpawnBiome(new BiomePoop(properties));

    }
}
