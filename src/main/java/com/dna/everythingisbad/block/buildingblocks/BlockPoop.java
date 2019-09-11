package com.dna.everythingisbad.block.buildingblocks;

import com.dna.everythingisbad.block.BlockFallingBase;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockPoop extends BlockFallingBase {
    public BlockPoop(String name, Material material) {
        //material determines sound, map color, tool?, flammability, etc
        super(material);
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        setSoundType(SoundType.SLIME);
        setHardness(0.5f);
        setResistance(1);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
}
