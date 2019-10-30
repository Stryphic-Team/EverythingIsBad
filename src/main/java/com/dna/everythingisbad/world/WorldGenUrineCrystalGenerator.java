package com.dna.everythingisbad.world;

import com.dna.everythingisbad.utils.RandomUtils;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenUrineCrystalGenerator implements IWorldGenerator {
    public static WorldGenUrineCrystalGenerator INSTANCE = new WorldGenUrineCrystalGenerator();
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int posx = (chunkX * 16) + 8;
        int posz = (chunkZ * 16) + 8;

        if (world.getBiome(new BlockPos(posx,0,posz)) == Biomes.HELL && random.nextFloat() < 0.01){
            // Starting from the top and going down... until it finds a block which is air,
            // and the block above it is netherrack
            for (int y = 127; y > 3; y--){
                BlockPos pos = new BlockPos(posx,y,posz);
                if (world.getBlockState(pos).getBlock() == Blocks.AIR &&
                    world.getBlockState(pos.up(1)).getBlock() == Blocks.NETHERRACK){

                    WorldGenUrineCrystal wguc = new WorldGenUrineCrystal();
                    wguc.generate(world, random ,pos);
                    break;
                }
            }
        }
    }
}
