package com.dna.everythingisbad.world.trees;

import com.dna.everythingisbad.Main;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
        int xPos = chunkX * 16 + 8;
        int zPos = chunkZ * 16 + 8;
        WorldGenTreeHappy tree = new WorldGenTreeHappy();
        int height_max = 100;
        int height_min = 60;
        Main.logger.info(random.nextInt(height_max)-height_min);
        tree.generate(world,random,new BlockPos(xPos,random.nextInt(height_max + height_min)-height_min,zPos));

    }
}
