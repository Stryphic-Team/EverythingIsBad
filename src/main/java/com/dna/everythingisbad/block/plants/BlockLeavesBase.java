package com.dna.everythingisbad.block.plants;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.block.IModBlockBase;
import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.block.BlockLeaves;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public abstract class BlockLeavesBase extends BlockLeaves implements IModBlockBase {

    protected String name;

    /**
     * Can be used in the case that you would like to base a material through
     * @param name
     */
    public BlockLeavesBase(String name){

        this.name = name;
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        addItemToRegistry();
        addBlockToRegistry();
    }
    @Override
    public void addItemToRegistry() {
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.name));
    }

    @Override
    public void addBlockToRegistry() {
        ModBlocks.BLOCKS.add(this);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerModel(Item.getItemFromBlock(this),0);
    }
}
