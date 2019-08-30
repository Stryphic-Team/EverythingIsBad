package com.dna.everythingisbad.world;

import com.dna.everythingisbad.init.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenQuestionMarkBlock extends WorldGenerator {
    public WorldGenQuestionMarkBlock(){
        super(false);
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int x = position.getX();int y = position.getY();int z = position.getZ();
        if (worldIn.getBlockState(position).getBlock() != Blocks.WATER) {
            BlockPos blockpos = new BlockPos(x,y+3,z);
            this.setBlockAndNotifyAdequately(worldIn, blockpos, ModBlocks.QUESTION_MARK_BLOCK.getDefaultState());
            return true;
        }else{
            return false;
        }
    }
}
