package com.dna.everythingisbad.world;

import com.dna.everythingisbad.init.ModBiomes;
import com.dna.everythingisbad.utils.RandomUtils;
import com.dna.everythingisbad.world.structures.WorldGenQuestionMarkStructure;
import com.dna.everythingisbad.world.structures.WorldGenStupidFacility;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.HashMap;
import java.util.Random;

public class WorldGenStupidFacilityGenerator implements IWorldGenerator {
    public static WorldGenStupidFacilityGenerator INSTANCE = new WorldGenStupidFacilityGenerator();
    public static Biome[] EXCLUDED_BIOMES = new Biome[]{
            Biomes.HELL,
            Biomes.VOID,
            ModBiomes.HEAVEN,
            Biomes.SKY
    };

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        HashMap<ChunkPosition, Boolean> usedChunks = new HashMap<>();
        if (random.nextFloat() < 0.01) {
            int xPos = chunkX * 16;
            int zPos = chunkZ * 16;

            Biome biome = world.getBiomeProvider().getBiome(new BlockPos(xPos,1,zPos));
            BlockPos firstPosition = world.getTopSolidOrLiquidBlock(new BlockPos(xPos,0,zPos));

            // Cascading world lag prevention
            firstPosition = firstPosition.add(1,0,1);

            int currentChunkX = chunkX;
            int currentChunkZ = chunkZ;
            BlockPos currentPos = firstPosition;

            int randomSign;
            //int randomSize = RandomUtils.fromRangeI(1,15);
            int randomSize = 1;

            if(!biomeExcluded(biome)  && world.getBlockState(firstPosition).getBlock() != Blocks.WATER){
                for (int i = 0; i < randomSize; i++){

                    // If there is no building already there then place one down here!
                    if (!usedChunks.containsKey(new ChunkPosition(currentChunkX, currentChunkZ))){

                        Chunk currentChunk = chunkProvider.provideChunk(currentChunkX,currentChunkZ);
                        currentChunk.markLoaded(true);

                        WorldGenStupidFacility worldGenStupidFacility = new WorldGenStupidFacility();
                        worldGenStupidFacility.generate(world,random,currentPos);
                        usedChunks.put(new ChunkPosition(currentChunkX,currentChunkZ) , true);
                    }

                    // Has a 1/2 chance of being -1 or 1
                    randomSign = random.nextInt(2);
                    if (randomSign == 0){  randomSign = -1; }

                    // Either steps/decrements the X or Z of the chunk, and the block pos by +-16
                    if (random.nextBoolean()){
                        currentChunkX++;
                        currentPos = new BlockPos(currentPos.getX() + (16*randomSign),
                                currentPos.getY(), currentPos.getZ() );
                    }else{
                        currentChunkZ++;
                        currentPos = new BlockPos(currentPos.getX(), currentPos.getY(),
                                currentPos.getZ() + (16*randomSign) );
                    }
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

class ChunkPosition {
    int x;
    int z;
    ChunkPosition (int x, int z){
        this.x = x;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }
}
