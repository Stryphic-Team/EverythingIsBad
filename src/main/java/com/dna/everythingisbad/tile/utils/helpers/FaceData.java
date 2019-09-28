package com.dna.everythingisbad.tile.utils.helpers;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FaceData{
    public TileEntity tileEntity;
    public EnumFacing facing;
    public FaceData(BlockPos pos, EnumFacing facing,World world){
        this.tileEntity = world.getTileEntity(pos);
        this.facing = facing;
    }
}
