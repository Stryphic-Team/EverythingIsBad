package com.dna.everythingisbad.fluid;

import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidDevilsPee extends Fluid {

    public FluidDevilsPee(String fluidName) {
        super(CommonUtils.createUnlocalizedName(fluidName),
            new ResourceLocation(Reference.MOD_ID, "blocks/fluids/devils_pee_still"),
            new ResourceLocation(Reference.MOD_ID, "blocks/fluids/devils_pee_flow")
        );
    }
}
