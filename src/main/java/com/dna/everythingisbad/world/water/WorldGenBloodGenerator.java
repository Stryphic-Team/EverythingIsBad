package com.dna.everythingisbad.world.water;

import com.dna.everythingisbad.init.ModFluids;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeHell;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Random;

public class WorldGenBloodGenerator implements IWorldGenerator {
    public static WorldGenBloodGenerator INSTANCE = new WorldGenBloodGenerator();
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int xPos = chunkX * 16 + 8;
        int zPos = chunkZ * 16 + 8;
        int yPos = 0;
        BiomeProvider bp = world.getBiomeProvider();
        Biome biome = bp.getBiome(new BlockPos(xPos,yPos,zPos));
        // make sure you're in the hell biome
        if(random.nextFloat() < 0.15 && biome.getBiomeClass().equals(BiomeHell.class)) {

            //Way of finding a sort of "top layer" in the nether
            // (by finding the first air block)
            int hite = 0;
            for (int i=0;i<127;i++){
                if (world.getBlockState(new BlockPos(xPos,i,zPos)).getBlock() == Blocks.AIR){
                    break;
                }
                hite++;
            }
            // get the solid block directly below that air block.
            hite--;
            // and make a blood lake here
            if (hite<=123){
                WorldGenLakes lake = new WorldGenLakes(ModFluids.BLOOD.getBlockFluidBase());
                lake.generate(world, random, new BlockPos(xPos, hite, zPos));
            }
        }

    }
}
