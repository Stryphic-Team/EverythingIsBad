package com.dna.everythingisbad.block.buildingblocks;

import com.dna.everythingisbad.block.BlockBase;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.item.ItemBlock;

public class BlockUrineBricks extends BlockBase {
    public BlockUrineBricks(String name){
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        setHardness(2f);
        setResistance(15);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
}
