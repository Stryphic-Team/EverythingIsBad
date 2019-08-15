package com.dna.everythingisbad.init;


import com.dna.everythingisbad.utils.FluidPrototype;

public class ModFluids {

    public static FluidPrototype DEVILS_PEE = new FluidPrototype("devils_pee");
    public static FluidPrototype DIARIA = new FluidPrototype("diaria");

    public static FluidPrototype[] FLUIDS = new FluidPrototype[]{
            DEVILS_PEE,DIARIA
    };


    public static void register()
    {
        for(FluidPrototype builder:FLUIDS){
            builder.registerFluid();
        }
    }
    public static void registerBlocks(){
        for(FluidPrototype builder:FLUIDS){
            builder.registerBlock();
        }
    }
    public static void registerRenderers(){
        for(FluidPrototype builder:FLUIDS){
            builder.registerRender();
        }
    }

}
