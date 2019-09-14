package com.dna.everythingisbad.init;

import com.dna.everythingisbad.world.biomes.BiomeHeaven;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModBiomes {
    public static final Biome HEAVEN = new BiomeHeaven();

    public static void registerBiomes(){
        initBiome(HEAVEN,"Heaven", BiomeManager.BiomeType.COOL, BiomeDictionary.Type.SPARSE);
    }

    public static Biome initBiome(Biome biome, String name, BiomeManager.BiomeType type, BiomeDictionary.Type... types){
        biome.setRegistryName(name);
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome,types);
        BiomeManager.addBiome(type,new BiomeManager.BiomeEntry(biome,0));
        BiomeManager.addSpawnBiome(biome);
        return biome;
    }
}
