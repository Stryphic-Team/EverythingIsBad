package com.dna.everythingisbad.world;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenAloeGenerator implements IWorldGenerator {
    public static WorldGenAloeGenerator INSTANCE = new WorldGenAloeGenerator();
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (random.nextFloat() < 0.06) {
            int xPos = chunkX * 16 + 8;
            int zPos = chunkZ * 16 + 8;
            WorldGenAloe alo = new WorldGenAloe();
            BlockPos top = world.getTopSolidOrLiquidBlock(new BlockPos(xPos, 1, zPos));
            alo.generate(world, random, new BlockPos(xPos, top.getY(), zPos));
        }
    }
}
