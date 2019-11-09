package com.dna.everythingisbad.world.utils;

import com.dna.everythingisbad.init.ModBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class BiomeUtils {
    static Biome[] nonOverworld = new Biome[]{
            Biomes.HELL,
            Biomes.VOID,
            ModBiomes.HEAVEN,
            Biomes.SKY
    };
    static Biome[] waterBiomes = new Biome[]{
        Biomes.OCEAN,
        Biomes.RIVER,
        Biomes.FROZEN_RIVER,
        Biomes.DEEP_OCEAN,
        Biomes.FROZEN_OCEAN
    };

    public static boolean isOverworld(Biome biome){
        for(Biome nonOverworld:nonOverworld){
            if(biome == nonOverworld){
                return false;
            }
        }
        return true;
    }
    public static boolean isWet(Biome biome){
        for(Biome waterBiome:waterBiomes){
            if(biome == waterBiome){
                return true;
            }
        }
        return false;
    }
    public static boolean isWet(World world, BlockPos blockPos){
        return isWet(world.getBiome(blockPos));
    }
    public static boolean isOverworld(World world, BlockPos blockPos){
        return isOverworld(world.getBiome(blockPos));
    }

}
