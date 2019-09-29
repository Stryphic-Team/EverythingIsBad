package com.dna.everythingisbad.tile.utils.helpers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

import java.util.ArrayList;

public class WorldUtils {
    public static ArrayList<IBlockState> getBlocksInCuboid(World world, StructureBoundingBox boundingBox){
        ArrayList<IBlockState> blocks = new ArrayList<IBlockState>();
        for(int x = boundingBox.minX;x<boundingBox.maxX;x++){
            for(int y = boundingBox.minY;y<boundingBox.maxY;y++) {
                for (int z = boundingBox.minZ; z < boundingBox.maxZ; z++) {
                    blocks.add(world.getBlockState(new BlockPos(x,y,z)));
                }
            }
        }
        return blocks;
    }
}
