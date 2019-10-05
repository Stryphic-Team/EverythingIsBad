package com.dna.everythingisbad.tile.processing;

import com.dna.everythingisbad.tile.TileMachineBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class TileQuarry extends TileMachineBase {
    BlockPos relativePosition = new BlockPos(0,0,0);
    Block currentBlock;
    Block[] nonMinableBlocks = new Block[]{
        Blocks.AIR,
        Blocks.BEDROCK
    };
    boolean drillWorking = true;

    public TileQuarry() {
        super("quarry");
        setFinishedProgress(10);
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
        if(drillWorking) {

            if(pos.getY() - relativePosition.getY() >= 1){
                relativePosition = relativePosition.add(0,1,0);
            }else{
                relativePosition = relativePosition.subtract(new BlockPos(0,relativePosition.getY(),0));
                relativePosition = relativePosition.add(1,0,0);
            }
            if(relativePosition.getX() >= 64){
                relativePosition = relativePosition.subtract(new BlockPos(relativePosition.getX(),0,0));
                relativePosition = relativePosition.add(0,0,1);
            }
            if(relativePosition.getZ() >= 64){
                drillWorking = false;
            }
            BlockPos currentPos = pos.subtract(relativePosition).down().subtract(new BlockPos(1,0,1));;
            IBlockState currentBlock = world.getBlockState(currentPos);
            if(canBeMined(currentBlock.getBlock())) {
                currentBlock.getBlock().dropBlockAsItem(world, pos.up(), currentBlock, 0);
                world.setBlockToAir(currentPos);
                energyHandler.reduceEnergy((int)currentBlock.getBlockHardness(world,currentPos),false);
            }

        }

    }
    public boolean canBeMined(Block currentBlock){
        for(Block block:nonMinableBlocks){
            if(currentBlock == block){
                return false;
            }
        }
        return true;
    }
    @Override
    public void reduceInput() {

//            energyHandler.reduceEnergy(100, true);
//            BlockPos currentPos = pos.subtract(relativePosition).subtract(new BlockPos(17,0,17));
//            if (currentPos.getY() > 3) {
//                relativePosition = relativePosition.add(0, 1, 0);
//            } else {
//                if (relativePosition.getX() <= 64) {
//                    relativePosition = new BlockPos(relativePosition.getX() + 16, 0, relativePosition.getZ());
//                }else{
//                    relativePosition = new BlockPos(0, 0, relativePosition.getZ()+16);
//                }
//                if(relativePosition.getZ() >= 64){
//                    drillWorking = false;
//                }
//
//            }
//        }
    }
}