package com.dna.everythingisbad.block;

import com.dna.everythingisbad.block.BlockBase;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockCloud extends BlockBase {
    public BlockCloud(String name){
        super(Material.LEAVES);
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
}
