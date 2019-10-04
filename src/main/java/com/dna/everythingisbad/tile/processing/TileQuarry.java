package com.dna.everythingisbad.tile.processing;

import com.dna.everythingisbad.tile.TileMachineBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class TileQuarry extends TileMachineBase {
    BlockPos drillDifferential = new BlockPos(0,0,0);
    boolean drillWorking = false;

    public TileQuarry() {
        super("quarry");
        setFinishedProgress(100);
    }


    @Override
    public void update() {
        super.update();
    }

    @Override
    public boolean hasNecessaryItems() {
        return energyHandler.getEnergyStored() > 100;
    }

    @Override
    public void insertOutput() {
        BlockPos currentPos = pos.subtract(drillDifferential).down();
        for(int x = 0;x<16;x++){
            for(int z = 0;z<16;z++){
                IBlockState currentBlock = world.getBlockState(currentPos.add(x,0,z));

                if(currentBlock.getBlock() != Blocks.AIR) {
                    currentBlock.getBlock().dropBlockAsItem(world, pos.up(), currentBlock, 0);
                }
                if(currentBlock.getBlock() != Blocks.BEDROCK) {
                    world.setBlockToAir(currentPos.add(x, 0, z));
                }
            }
        }

    }

    @Override
    public void reduceInput() {
        energyHandler.reduceEnergy(100,true);
        BlockPos currentPos = pos.subtract(drillDifferential);
        if(currentPos.getY() > 3){
            drillDifferential = drillDifferential.add(0,1,0);
        }else{
            drillDifferential = new BlockPos(drillDifferential.getX()+16,0,drillDifferential.getZ());
        }
    }
}
