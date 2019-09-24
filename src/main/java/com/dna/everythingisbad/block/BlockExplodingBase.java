package com.dna.everythingisbad.block;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.block.BlockTNT;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public abstract class BlockExplodingBase extends BlockTNT implements IModBlockBase {

    protected String name;

    /**
     * Can be used in the case that you would like to base a material through
     * @param name
     */

    public BlockExplodingBase(String name){
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
