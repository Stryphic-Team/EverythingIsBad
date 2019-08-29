package com.dna.everythingisbad.block;

import com.dna.everythingisbad.tile.TileDryerMachine;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockDryerMachine extends BlockMachineBase {
    public BlockDryerMachine(String name) {
        super(name);
    }
    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileDryerMachine();
    }
}
