package com.dna.everythingisbad.init;


import com.dna.everythingisbad.utils.FluidBuilder;

public class ModFluids {

    public static final FluidBuilder DEVILS_PEE = new FluidBuilder("devils_pee");
    public static final FluidBuilder DIARIA = new FluidBuilder("diaria");

    public static FluidBuilder[] FLUIDS = new FluidBuilder[]{
            DEVILS_PEE,DIARIA
    };


    public static void register()
    {
        for(FluidBuilder builder:FLUIDS){
            builder.registerFluid();
        }
    }
    public static void registerBlocks(){
        for(FluidBuilder builder:FLUIDS){
            builder.registerBlock();
        }
    }
    public static void registerRenderers(){
        for(FluidBuilder builder:FLUIDS){
            builder.registerRender();
        }
    }

}
