package com.dna.everythingisbad.world;

import com.dna.everythingisbad.init.ModBiomes;
import com.dna.everythingisbad.utils.RandomUtils;
import com.dna.everythingisbad.world.structures.*;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class StructureGenerator implements IWorldGenerator {
    public static StructureGenerator INSTANCE = new StructureGenerator();
    public Biome[] EXCLUDED_BIOMES = new Biome[]{
            Biomes.HELL,
            Biomes.VOID,
            ModBiomes.HEAVEN,
            Biomes.SKY,
    };
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int xPos = chunkX * 16;
        int zPos = chunkZ * 16;
        if (!biomeExcluded(world.getBiome(new BlockPos(xPos, 64, zPos))) && !isWaterChunk(world, chunkX, chunkZ)) {
            if (world.getBiome(new BlockPos(xPos, 64, zPos)) == Biomes.PLAINS || world.getBiome(new BlockPos(xPos, 64, zPos)) == Biomes.ICE_PLAINS) {
                //NOTE: Should have tested to see it spawns before changing conditions for spawn
                if (random.nextFloat() < 0.0001f) {
                    WorldGenTwinTowers twinTowers = new WorldGenTwinTowers();
                    BlockPos position = new BlockPos(xPos,1, zPos);
                    if (world.getTopSolidOrLiquidBlock(position).getY() > 30) {
                        twinTowers.generate(world, random, world.getTopSolidOrLiquidBlock(position).down(20));
                    }
                }
            }
            if (random.nextFloat() < 0.0005f) {
                WorldGenWoolBlock worldGenWoolBlock = new WorldGenWoolBlock();
                BlockPos position = new BlockPos(xPos, 150, zPos);
                worldGenWoolBlock.generate(world, random, position);
            }
            if (random.nextFloat() < 0.005f) {
                WorldGenVillageCasino worldGenVillageCasino = new WorldGenVillageCasino();
                BlockPos position = new BlockPos(xPos, 1, zPos);

                worldGenVillageCasino.generate(world, random, world.getTopSolidOrLiquidBlock(position));

            }
            if(RandomUtils.withinChance(200)){
                WorldGenLandMine worldGenLandMine = new WorldGenLandMine();
                BlockPos position = new BlockPos(xPos,1,zPos);

                worldGenLandMine.generate(world,random,position);
            }
        }
    }
    public boolean biomeExcluded(Biome biome){
        for(Biome excludedBiome : EXCLUDED_BIOMES){
            if(excludedBiome == biome){
                return true;
            }
        }
        return false;
    }
    public boolean isWaterChunk(World world,int chunkX, int chunkZ){
        for(int x = chunkX * 16;x < chunkX * 16 + 16;x++){
            for(int z = chunkZ * 16;z < chunkZ * 16 + 16;z++){
                if(world.getBlockState(world.getTopSolidOrLiquidBlock(new BlockPos(x,64,z))) == Blocks.WATER.getDefaultState()){
                    return true;
                }
            }
        }
        return false;
    }
}
