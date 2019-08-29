package com.dna.everythingisbad.block;

import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockQuestionMark extends BlockBase {
    public BlockQuestionMark(String name) {
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
}
