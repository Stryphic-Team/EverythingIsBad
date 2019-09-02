package com.dna.everythingisbad.block;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.tile.TileFluxTest;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
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
