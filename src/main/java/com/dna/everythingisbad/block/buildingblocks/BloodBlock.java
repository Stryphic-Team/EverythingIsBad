package com.dna.everythingisbad.block.buildingblocks;

import com.dna.everythingisbad.block.BlockFallingBase;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemBlock;

public class BloodBlock extends BlockFallingBase {
    public BloodBlock(String name){
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        setSoundType(SoundType.STONE);
        setHardness(5f);
        setResistance(30f);
        setHarvestLevel("pickaxe",1);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
}
