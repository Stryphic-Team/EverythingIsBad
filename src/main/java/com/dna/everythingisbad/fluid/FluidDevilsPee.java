package com.dna.everythingisbad.fluid;

import com.dna.everythingisbad.reference.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.awt.*;

public class FluidDevilsPee extends Fluid {

    public FluidDevilsPee(String fluidName) {
        super(fluidName, new ResourceLocation(Reference.MOD_ID,
                "blocks/fluids/devils_pee_still"), new ResourceLocation(Reference.MOD_ID,
                "blocks/fluids/devils_pee_flow"));
    }
}
