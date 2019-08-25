package com.dna.everythingisbad.block;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import com.dna.everythingisbad.utils.IHasModel;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

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
