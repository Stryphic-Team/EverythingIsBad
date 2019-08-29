package com.dna.everythingisbad.block;

import com.dna.everythingisbad.tile.TileStupidCoreMachine;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockStupidCoreMachine extends BlockMachineBase {
    public BlockStupidCoreMachine(String name){
        super(name);
    }
    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileStupidCoreMachine();
    }

}
