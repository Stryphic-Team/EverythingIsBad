package com.dna.everythingisbad.block.buildingblocks;

import com.dna.everythingisbad.block.BlockBase;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockPoopBricks extends BlockBase {
    public BlockPoopBricks(String name, Material material) {
        //material determines sound, map color, tool?, flammability, etc
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        setSoundType(SoundType.STONE);
        setHardness(2f);
        setResistance(15);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
}
