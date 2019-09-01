package com.dna.everythingisbad.world;

import com.dna.everythingisbad.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenAloe extends WorldGenDeadBush implements IWorldGenerator {
    public static WorldGenAloe INSTANCE = new WorldGenAloe();
    @Override
    public void generate(Random rand, int chunkx, int chunkz, World worldIn, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        BlockPos position = new BlockPos(chunkx*16,255,chunkz*16);
        for (IBlockState iblockstate = worldIn.getBlockState(position); (iblockstate.getBlock().isAir(iblockstate, worldIn, position) || iblockstate.getBlock().isLeaves(iblockstate, worldIn, position)) && position.getY() > 0; iblockstate = worldIn.getBlockState(position))
        {
            position = position.down();
        }

        for (int i = 0; i < 4; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && Blocks.DEADBUSH.canBlockStay(worldIn, blockpos, Blocks.DEADBUSH.getDefaultState()))
            {
                worldIn.setBlockState(blockpos, ModBlocks.ALOE_BLOCK.getDefaultState(), 2);
            }
        }
    }
}
