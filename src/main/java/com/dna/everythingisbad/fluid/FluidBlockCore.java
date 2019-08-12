package com.dna.everythingisbad.fluid;

import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;

public class FluidBlockCore extends BlockFluidClassic {

    private String name;
    public FluidBlockCore(String name,FluidCore fluid) {
        super(fluid, Material.WATER);
        this.name = name;


        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        displacements.put(this, false);
    }


}
