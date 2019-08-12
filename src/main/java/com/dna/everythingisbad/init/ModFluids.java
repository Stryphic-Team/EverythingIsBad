package com.dna.everythingisbad.init;


import com.dna.everythingisbad.fluids.FluidLiquid;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids {

    public static final Fluid DEVILS_PEE = new FluidLiquid("devils_pee", new ResourceLocation(Reference.MOD_ID,"fluids/devils_pee_still"), new ResourceLocation(Reference.MOD_ID,"fluids/devils_pee_flow"));

    public static void registerFluids()
    {
        registerFluid(DEVILS_PEE);
    }

    public static void registerFluid(Fluid fluid)
    {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
    }

}
