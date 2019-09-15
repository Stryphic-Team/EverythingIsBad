package com.dna.everythingisbad.init;


import com.dna.everythingisbad.utils.prototypes.FluidPrototype;
import net.minecraft.block.material.Material;

public class ModFluids {

    public static FluidPrototype DEVILS_PEE = new FluidPrototype("devils_pee");
    public static FluidPrototype DIARIA = new FluidPrototype("diaria");
    public static FluidPrototype BLOOD = new FluidPrototype("blood", Material.WATER);
    public static FluidPrototype URINE = new FluidPrototype("urine");

    public static FluidPrototype[] FLUIDS = new FluidPrototype[]{
            DEVILS_PEE,DIARIA,BLOOD,URINE
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
