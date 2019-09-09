package com.dna.everythingisbad.world;

import com.dna.everythingisbad.world.structures.WorldGenTwinTowers;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class StructureGenerator implements IWorldGenerator {
    public static StructureGenerator INSTANCE = new StructureGenerator();
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int xPos = chunkX * 16;
        int zPos = chunkZ * 16;
        if(random.nextFloat() < 0.001f){
            WorldGenTwinTowers twinTowers = new WorldGenTwinTowers();
            BlockPos position = new BlockPos(xPos,1,zPos);
            twinTowers.generate(world,random,world.getTopSolidOrLiquidBlock(position).down(20));
        }
    }
}
