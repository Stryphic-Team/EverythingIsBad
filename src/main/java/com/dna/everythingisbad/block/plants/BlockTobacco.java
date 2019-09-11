package com.dna.everythingisbad.block.plants;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import com.dna.everythingisbad.utils.IHasModel;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockTobacco extends BlockCrops implements IHasModel {
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 4);
    public BlockTobacco(String name)
    {
        this.setTickRandomly(true);
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
        //this.setDefaultState(this.withAge(0));
    }
    @Override
    public int getMaxAge()
    {
        return 4;
    }
    @Override
    public void registerModels()
    {
        Main.proxy.registerModel(Item.getItemFromBlock(this),0);

    }
    @Override
    protected Item getSeed()
    {
        return ModItems.TOBACCO_SEEDS_ITEM;
    }

    @Override
    protected Item getCrop()
    {
        return ModItems.TOBACCO_LEAF_ITEM;
    }
}
