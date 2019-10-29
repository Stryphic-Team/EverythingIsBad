package com.dna.everythingisbad.world;

import com.dna.everythingisbad.init.ModBiomes;
import com.dna.everythingisbad.utils.RandomUtils;
import com.dna.everythingisbad.world.structures.WorldGenStupidFacility;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.Random;

public class WorldGenStupidFacilityGenerator implements IWorldGenerator {
    static boolean generating = false;
    static int minSize = 5;
    static int maxSize = 30;
    static int size = 0;
    static int selectedSize = 0;
    static int currentChunkX = 0;
    static int currentChunkZ = 0;
    static int currentYPos = 0;
    static BlockPos firstPosition;
    static ArrayList<ChunkPosition> usedChunks = new ArrayList<>();
    static int chunksGenerated = 0;

    public static WorldGenStupidFacilityGenerator INSTANCE = new WorldGenStupidFacilityGenerator();
    public static Biome[] EXCLUDED_BIOMES = new Biome[]{
            Biomes.HELL,
            Biomes.VOID,
            ModBiomes.HEAVEN,
            Biomes.SKY
    };


    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        chunksGenerated++;
        if(!generating) {
            if (RandomUtils.withinChance(10000)) {
                firstPosition = world.getTopSolidOrLiquidBlock(new BlockPos(chunkX * 16, 0, chunkZ * 16));
                if(world.getBlockState(firstPosition).getBlock() != Blocks.WATER) {
                    generating = true;
                    size = 0;
                    currentYPos = firstPosition.getY();
                    selectedSize = RandomUtils.fromRangeI(minSize, maxSize);
                    currentChunkX = chunkX;
                    currentChunkZ = chunkZ;
                }
            }
            if(RandomUtils.withinChance(100)){
                    generating = false;
            }
        }
        if(generating) {
            int randomSign;
            if (size < selectedSize + 1) {
                randomSign = random.nextInt(2);
                if (randomSign == 0) {
                    randomSign = -1;
                }

                // Either steps/decrements the X or Z of the chunk, and the block pos by +-16
                if (random.nextBoolean()) {
                    currentChunkX += randomSign;
                } else {
                    currentChunkZ += randomSign;
                }

                // If there is no building already there then place one down here!
                if (!isChunkUsed(currentChunkX, currentChunkZ)) {

                    Chunk currentChunk = chunkProvider.provideChunk(currentChunkX, currentChunkZ);
                    currentChunk.markLoaded(true);

                    WorldGenStupidFacility worldGenStupidFacility = new WorldGenStupidFacility();
                    worldGenStupidFacility.generate(world, random, new BlockPos(currentChunkX * 16, currentYPos, currentChunkZ * 16));
                    usedChunks.add(new ChunkPosition(currentChunkX, currentChunkZ));
                    size++;
                }

            } else {
                generating = false;
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

    public boolean isChunkUsed( int x, int z){
        for (ChunkPosition chunkPosition : usedChunks){
            if (x == chunkPosition.getX() && z == chunkPosition.getZ()){
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
