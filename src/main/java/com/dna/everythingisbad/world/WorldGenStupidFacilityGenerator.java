package com.dna.everythingisbad.world;

import com.dna.everythingisbad.init.ModBiomes;
import com.dna.everythingisbad.utils.RandomUtils;
import com.dna.everythingisbad.world.structures.WorldGenStupidFacility;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenStupidFacilityGenerator implements IWorldGenerator {
    private static boolean generating = false;
    private static int minSize = 20;
    private static int maxSize = 100;
    private static int size = 0;
    private static int selectedSize = 0;
    private static int currentChunkX = 0;
    private static int currentChunkZ = 0;
    private static int startChunkX = 0;
    private static int startChunkZ = 0;
    private static int currentYPos = 0;
    private static BlockPos firstPosition;
    //private static ArrayList<ChunkPosition> usedChunks = new ArrayList<>();
    private static int[][] structureMap;
    private static int chunksGenerated = 0;

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
                    currentChunkX = 0;
                    currentChunkZ = 0;
                    startChunkX = chunkX;
                    startChunkZ= chunkZ;

                    structureMap = generateStructureMap(20,20,maxSize);

                }
            }

        }
        if(generating) {

            //usedChunks.add(new ChunkPosition(currentChunkX, currentChunkZ));

//            if(chunkX - startChunkX < structureMap.length - 1 && chunkX - startChunkX >= 0){
//                if(chunkZ - startChunkZ < structureMap[0].length - 1 && chunkZ - startChunkZ >= 0){
//
//                }
//            }

            if (currentChunkX < structureMap.length - 1) {

                currentChunkX++;
            } else {
                currentChunkZ++;
                currentChunkX = 0;
            }
            if (currentChunkZ >= structureMap[0].length - 1) {
                generating = false;
            }else{
                //chunkProvider.provideChunk((currentChunkX + startChunkX),(currentChunkZ + startChunkZ));
                WorldGenStupidFacility worldGenStupidFacility = new WorldGenStupidFacility();
                worldGenStupidFacility.generate(
                        world,
                        random,
                        new BlockPos(
                                (currentChunkX + startChunkX) * 16,
                                currentYPos,
                                (currentChunkZ + startChunkZ) * 16
                        ).add(1,0,1),
                        structureMap[currentChunkX][currentChunkZ]
                );
            }
//            if(world.getWorldTime() % 500 == 499){
//                generating = false;
//            }

        }


                // If there is no building already there then place one down here!
//                if (!isChunkUsed(currentChunkX, currentChunkZ)) {
//
//                    //Chunk currentChunk = chunkProvider.provideChunk(currentChunkX, currentChunkZ);
//                    //currentChunk.markLoaded(true);
//
//
//                    size++;
//                }else{
//                    randomSign = random.nextInt(2);
//                    if (randomSign == 0) {
//                        randomSign = -1;
//                    }
//
//                    // Either steps/decrements the X or Z of the chunk, and the block pos by +-16
//                    if (random.nextBoolean()) {
//                        currentChunkX += randomSign;
//
//                    } else {
//                        currentChunkZ += randomSign;
//
//                    }
//                }
//
//            } else {
//                generating = false;
//            }


    }
    public int[][] generateStructureMap(int width,int height,int max){
        int[][] map = new int[width][height];
        int currentX = RandomUtils.fromRangeI(0,width);
        int currentZ = RandomUtils.fromRangeI(0,height);
        for(int i = 0;i<max;i++){
            int randomSign = RandomUtils.random.nextBoolean() ? -1 : 1;

            if(RandomUtils.random.nextBoolean()){
                currentX += randomSign;

                if(currentX >= width || currentX < 0 || map[currentX][currentZ] != 0){
                    currentX -= randomSign;
                }
            }else{
                currentZ += randomSign;
                if(currentZ >= height || currentZ < 0 || map[currentX][currentZ] != 0){
                    currentZ -= randomSign;
                }
            }
            if(map[currentX][currentZ] == 0){
                map[currentX][currentZ] = RandomUtils.fromRangeI(1,6);
            }

        }
        return map;
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


