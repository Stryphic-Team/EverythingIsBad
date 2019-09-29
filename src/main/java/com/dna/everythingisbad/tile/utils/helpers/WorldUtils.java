package com.dna.everythingisbad.tile.utils.helpers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class WorldUtils {
    public static ArrayList<IBlockState> getBlocksInCuboid(World world, SearchBoundingBox boundingBox){
        ArrayList<IBlockState> blocks = new ArrayList<IBlockState>();
        for(int x = boundingBox.getMinX();x<=boundingBox.getMaxX();x++){
            for(int y = boundingBox.getMinY();y<=boundingBox.getMaxY();y++) {
                for (int z = boundingBox.getMinZ(); z<=boundingBox.getMaxZ();z++) {
                    blocks.add(world.getBlockState(new BlockPos(x,y,z)));
                }
            }
        }
        return blocks;
    }
    public static class SearchBoundingBox{
        BlockPos firstPoint;
        BlockPos secondPoint;
        public SearchBoundingBox(BlockPos firstPoint,BlockPos secondPoint){
            this.firstPoint = firstPoint;
            this.secondPoint = secondPoint;

        }
        public int getMinX(){
            return firstPoint.getX();
        }
        public int getMinY(){
            return firstPoint.getY();
        }
        public int getMinZ(){
            return firstPoint.getZ();
        }
        public int getMaxX(){
            return secondPoint.getX();
        }
        public int getMaxY(){
            return secondPoint.getY();
        }
        public int getMaxZ(){
            return secondPoint.getZ();
        }

    }
}
