package com.dna.everythingisbad.block.buildingblocks;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.utils.IHasModel;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockStairsBase extends BlockStairs implements IHasModel {

    public BlockStairsBase(IBlockState modelState) {
        super(modelState);
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
    }
    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");

    }
}
