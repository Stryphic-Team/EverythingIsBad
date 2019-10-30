package com.dna.everythingisbad.world.nature;

import com.dna.everythingisbad.init.ModBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import java.util.Random;

public class WorldGenTreeHappy extends WorldGenAbstractTree {
    public WorldGenTreeHappy(){
        super(false);
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        int height = 3 * rand.nextInt(4); // Hite of the trunk
        boolean flag = true;

        // Below are a list of situations where we might say "Okay you cant make tree"
        // Including the tree is too high, or its past the edge of the world. Or you just cant
        // You know

        if (position.getY() >= 1 && position.getY() + height + 1 <= 255)
        {
            for (int j = position.getY(); j <= position.getY() + 1 + height; ++j)
            {
                int k = 1;

                if (j == position.getY())
                {
                    k = 0;
                }

                if (j >= position.getY() + 1 + height - 2)
                {
                    k = 2;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l)
                {
                    for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1)
                    {
                        if (j >= 0 && j < worldIn.getHeight())
                        {
                            if (!this.isReplaceable(worldIn,blockpos$mutableblockpos.setPos(l, j, i1)))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else // Heres where we say "Okay you can make tree"
            {
                IBlockState state = worldIn.getBlockState(position.down());

                if (state.getBlock().canSustainPlant(state, worldIn, position.down(), net.minecraft.util.EnumFacing.UP, (net.minecraft.block.BlockSapling) Blocks.SAPLING) && position.getY() < worldIn.getHeight() - height - 1) {
                    state.getBlock().onPlantGrow(state, worldIn, position.down(), position);

                    // Whatever block we're putting in right now
                    IBlockState block2place = ModBlocks.LEAVES_HAPPY_BLOCK.getDefaultState();
                    // Leaves around it
                    vertStripInDir(worldIn,state,position,"north",height-1,block2place);
                    vertStripInDir(worldIn,state,position,"south",height-1,block2place);
                    vertStripInDir(worldIn,state,position,"west",height-1,block2place);
                    vertStripInDir(worldIn,state,position,"east",height-1,block2place);

                    // Leaves on top
                    BlockPos wokePosition = position.up(height-1);
                    vertStripInDir(worldIn,state,wokePosition.west(),"east",2,block2place);

                    // Now its trunk time
                    block2place = ModBlocks.LOG_HAPPY_BLOCK.getDefaultState();
                    vertStripInDir(worldIn,state,position.west().down(),"east",height,block2place);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *  A chunky function which makes vertical tree strips
     * @param worldIn based in this world
     * @param state (ensuring the block is open
     * @param position at *this* position)
     * @param dir at this direction (N-S-E-W)
     * @param height making strips this high,
     * @param block2place of this block.
     * @return
     */

    public boolean vertStripInDir(World worldIn, IBlockState state, BlockPos position,String dir,int height,IBlockState block2place){
        BlockPos blockpos;
        if (dir.equals("north")) {
            blockpos = position.north().up();
        }else if (dir.equals("south")){
            blockpos = position.south().up();
        }else if (dir.equals("west")){
            blockpos = position.west().up();
        }else if (dir.equals("east")){
            blockpos = position.east().up();
        }else{
            return false;
        }
        for (int I=1;I<=height;I++) {
            state = worldIn.getBlockState(blockpos);
            if (state.getBlock().isAir(state, worldIn, blockpos) || state.getBlock().isLeaves(state, worldIn, blockpos) || state.getMaterial() == Material.VINE) {
                this.setBlockAndNotifyAdequately(worldIn, blockpos,block2place);
            }
            blockpos = blockpos.up();
        }
        return true;
    }
}
