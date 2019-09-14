package com.dna.everythingisbad.block;

import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockCloud extends BlockBase {

    public BlockCloud(String name){
        super(Material.GROUND);
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        setHardness(0.15f);

        setSoundType(SoundType.SNOW);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
}
