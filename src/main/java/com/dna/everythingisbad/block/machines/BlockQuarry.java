package com.dna.everythingisbad.block.machines;

import com.dna.everythingisbad.tile.processing.TileQuarry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockQuarry extends BlockDeviceBase {
    public BlockQuarry(String name) {
        super(name);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileQuarry();
    }
}
