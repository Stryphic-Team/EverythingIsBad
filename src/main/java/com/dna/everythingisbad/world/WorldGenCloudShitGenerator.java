package com.dna.everythingisbad.world;

import com.dna.everythingisbad.init.ModBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenCloudShitGenerator implements IWorldGenerator {
    public static WorldGenCloudShitGenerator INSTANCE = new WorldGenCloudShitGenerator();
    public WorldGenCloudShitGenerator(){

    }
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int xPos = chunkX * 16 + 8;
        int zPos = chunkZ * 16 + 8;
        Biome bingly = world.getBiomeProvider().getBiome(new BlockPos(xPos,1,zPos));
        if (bingly == ModBiomes.HEAVEN){
            WorldGenCloudShit cloudhist = new WorldGenCloudShit();
            BlockPos top = world.getTopSolidOrLiquidBlock(new BlockPos(xPos, 1, zPos));
            cloudhist.generate(world, random, new BlockPos(xPos, top.getY(), zPos));
        }
    }
}
