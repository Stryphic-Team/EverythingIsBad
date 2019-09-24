package com.dna.everythingisbad.block.buildingblocks;

import net.minecraft.block.BlockStairs;
import net.minecraft.util.EnumFacing;

public class BlockPoopStairs extends BlockStairsBase {

    public BlockPoopStairs(String name) {
        super(name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(HALF, BlockStairs.EnumHalf.BOTTOM).withProperty(SHAPE, BlockStairs.EnumShape.STRAIGHT));
    }
}
