package com.dna.everythingisbad.world.water;

import com.dna.everythingisbad.init.ModFluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenGodsPeeGenerator implements IWorldGenerator {

    public static WorldGenGodsPeeGenerator INSTANCE = new WorldGenGodsPeeGenerator();
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int xPos = chunkX * 16 + 8;
        int zPos = chunkZ * 16 + 8;





        for(int i = 0;i<16;i++){
            for(int j = 0;j<16;j++){
                BlockPos top = world.getTopSolidOrLiquidBlock(new BlockPos(chunkX*i,1,chunkZ*j));

                if(world.getBlockState(top).getBlock().getUnlocalizedName().equals("tile.water")){
                    world.setBlockState(top.up(1), ModFluids.DEVILS_PEE.getBlockFluidBase().getDefaultState());
                }
            }
        }


    }
}
