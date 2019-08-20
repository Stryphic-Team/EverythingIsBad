package com.dna.everythingisbad.world.water;

import com.dna.everythingisbad.init.ModFluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenGodsPeeGenerator implements IWorldGenerator {

    public static WorldGenGodsPeeGenerator INSTANCE = new WorldGenGodsPeeGenerator();
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int xPos = chunkX * 16 + 8;
        int zPos = chunkZ * 16 + 8;
        if(random.nextFloat() < 0.05) {
            WorldGenLakes lake = new WorldGenLakes(ModFluids.DEVILS_PEE.getBlockFluidBase());
            lake.generate(world, random, new BlockPos(xPos, 64, zPos));
        }

    }
}
