package com.dna.everythingisbad.world;

import com.dna.everythingisbad.init.ModBiomes;
import com.dna.everythingisbad.world.structures.WorldGenQuestionMarkStructure;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenQuestionMarkBlockGenerator implements IWorldGenerator {
    public static WorldGenQuestionMarkBlockGenerator INSTANCE = new WorldGenQuestionMarkBlockGenerator();
    public static Biome[] EXCLUDED_BIOMES = new Biome[]{
            Biomes.HELL,
            Biomes.VOID,
            ModBiomes.HEAVEN,
            Biomes.SKY
    };
    public WorldGenQuestionMarkBlockGenerator(){

    }
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (random.nextFloat() < 0.005) {
            int xPos = chunkX * 16 + 8;
            int zPos = chunkZ * 16 + 8;
            Biome biome = world.getBiomeProvider().getBiome(new BlockPos(xPos,1,zPos));
            if(!biomeExcluded(biome)){
                int decision = random.nextInt(2);
                if(decision == 1) {
                    WorldGenQuestionMarkBlock block = new WorldGenQuestionMarkBlock();
                    BlockPos top = world.getTopSolidOrLiquidBlock(new BlockPos(xPos, 1, zPos));
                    block.generate(world, random, new BlockPos(xPos, top.getY(), zPos));
                }else{
                    WorldGenQuestionMarkStructure worldGenQuestionMarkStructure = new WorldGenQuestionMarkStructure();
                    BlockPos position = world.getTopSolidOrLiquidBlock(new BlockPos(xPos,0,zPos));
                    worldGenQuestionMarkStructure.generate(world,random,position.up(3));
                }
            }

        }
    }
    public static boolean biomeExcluded(Biome biome){
        for(Biome excludedBiome : EXCLUDED_BIOMES){
            if(excludedBiome == biome){
                return true;
            }
        }
        return false;
    }
}
