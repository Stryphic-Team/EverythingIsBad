package com.dna.everythingisbad.block.machines;

import com.dna.everythingisbad.tile.TileFluxTest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;


public class BlockFluxTest extends BlockGeneratorBase {
    public BlockFluxTest(String name){
        super(name);

    }
    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileFluxTest();
    }

}
