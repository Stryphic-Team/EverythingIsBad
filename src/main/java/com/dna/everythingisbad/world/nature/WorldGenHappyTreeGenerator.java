package com.dna.everythingisbad.world.nature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenHappyTreeGenerator implements IWorldGenerator {
    public static WorldGenHappyTreeGenerator INSTANCE = new WorldGenHappyTreeGenerator();

    public WorldGenHappyTreeGenerator(){

    }
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if(random.nextFloat() < 0.1) {
            if (world.getWorldType() != WorldType.FLAT){
                //needs to be within the chunk bounds
                int xPos = chunkX * 16 + 8;
                int zPos = chunkZ * 16 + 8;
                WorldGenTreeHappy tree = new WorldGenTreeHappy();
                BlockPos top = world.getTopSolidOrLiquidBlock(new BlockPos(xPos, 1, zPos));
                tree.generate(world, random, new BlockPos(xPos, top.getY(), zPos));
            }
        }
    }
}
