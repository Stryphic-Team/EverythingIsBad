package com.dna.everythingisbad.world;

import com.dna.everythingisbad.world.structures.WorldGenQuestionMarkStructure;
import com.dna.everythingisbad.world.utils.BiomeUtils;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
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
        if (random.nextFloat() < 0.005) {
            int xPos = chunkX * 16 + 8;
            int zPos = chunkZ * 16 + 8;
            Biome biome = world.getBiomeProvider().getBiome(new BlockPos(xPos,1,zPos));
            BlockPos position = world.getTopSolidOrLiquidBlock(new BlockPos(xPos,0,zPos));
            if(BiomeUtils.isOverworld(biome)  && world.getBlockState(position).getBlock() != Blocks.WATER){
                int decision = random.nextInt(2);
                if(decision == 1) {
                    WorldGenQuestionMarkBlock block = new WorldGenQuestionMarkBlock();
                    block.generate(world, random, new BlockPos(xPos, position.getY(), zPos));
                }else{
                    WorldGenQuestionMarkStructure worldGenQuestionMarkStructure = new WorldGenQuestionMarkStructure();
                    worldGenQuestionMarkStructure.generate(world,random,position.up(3));
                }
            }

        }
    }

}
