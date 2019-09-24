package com.dna.everythingisbad.world.structures;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenLandMine extends WorldGenStructureBase {
    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        BlockPos topBlock = worldIn.getTopSolidOrLiquidBlock(position).down();
        for(int i = 0;i<5;i++) {
            worldIn.setBlockState(topBlock.down(i+1), Blocks.TNT.getDefaultState());
        }
        for(int i = 0;i<6;i++){
            BlockPos lowerPosition = topBlock.down(5);
            worldIn.setBlockState(lowerPosition.east(i), Blocks.TNT.getDefaultState());
            worldIn.setBlockState(lowerPosition.west(i), Blocks.TNT.getDefaultState());
            worldIn.setBlockState(lowerPosition.north(i), Blocks.TNT.getDefaultState());
            worldIn.setBlockState(lowerPosition.south(i), Blocks.TNT.getDefaultState());
        }
        worldIn.setBlockState(topBlock.up(),Blocks.STONE_PRESSURE_PLATE.getDefaultState());
        return true;
    }
}
