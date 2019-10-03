package com.dna.everythingisbad.block.machines;

import com.dna.everythingisbad.tile.generators.TileFluxTest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;


public class BlockFluxTest extends BlockGeneratorBase {
    public BlockFluxTest(String name){
        super(name);

    }
    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileFluxTest();
    }

}
