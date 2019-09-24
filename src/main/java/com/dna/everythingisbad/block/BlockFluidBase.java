package com.dna.everythingisbad.block;

import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidBase extends BlockFluidClassic {
    public BlockFluidBase(String name, Fluid fluid, Material material)
    {
        super(fluid, material);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        setRegistryName(name);
        //ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(name));
    }


}
