package com.dna.everythingisbad.fluid;

import com.dna.everythingisbad.reference.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidCore extends Fluid {
    private ResourceLocation still;
    private ResourceLocation flowing;
    public FluidCore(String name,int color){
        super(
            name,
            new ResourceLocation(Reference.MOD_ID,"blocks/fluid/"+name+"_still"),
            new ResourceLocation(Reference.MOD_ID,"blocks/fluid/"+name+"_flow"),
            color
        );


    }
}
