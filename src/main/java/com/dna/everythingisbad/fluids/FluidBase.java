package com.dna.everythingisbad.fluids;

import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidBase extends Fluid {
    public FluidBase(String name, ResourceLocation still, ResourceLocation flow)
    {
        super(CommonUtils.createUnlocalizedName(name), still, flow);
        this.setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
    }
}
