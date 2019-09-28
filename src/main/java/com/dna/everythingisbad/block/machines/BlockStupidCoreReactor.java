package com.dna.everythingisbad.block.machines;

import com.dna.everythingisbad.tile.generators.TileStupidCoreReactor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockStupidCoreReactor extends BlockGeneratorBase {
    public BlockStupidCoreReactor(String name) {
        super(name);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileStupidCoreReactor();
    }
}
