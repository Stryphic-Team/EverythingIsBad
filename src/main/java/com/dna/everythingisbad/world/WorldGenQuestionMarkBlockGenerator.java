package com.dna.everythingisbad.world;

import com.dna.everythingisbad.world.trees.WorldGenHappyTreeGenerator;
import com.dna.everythingisbad.world.trees.WorldGenTreeHappy;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenQuestionMarkBlockGenerator implements IWorldGenerator {
    public static WorldGenQuestionMarkBlockGenerator INSTANCE = new WorldGenQuestionMarkBlockGenerator();
    public WorldGenQuestionMarkBlockGenerator(){

    }
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (random.nextFloat() < 0.1) {
            int xPos = chunkX * 16 + 8;
            int zPos = chunkZ * 16 + 8;
            WorldGenQuestionMarkBlock blok = new WorldGenQuestionMarkBlock();
            BlockPos top = world.getTopSolidOrLiquidBlock(new BlockPos(xPos, 1, zPos));
            blok.generate(world, random, new BlockPos(xPos, top.getY(), zPos));
        }
    }
}
