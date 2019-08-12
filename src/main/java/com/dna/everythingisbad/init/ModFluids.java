package com.dna.everythingisbad.init;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.fluid.FluidCore;
import com.dna.everythingisbad.utils.FluidCreator;

import java.util.ArrayList;
import java.util.HashMap;

public class ModFluids {
    //    public static BlockFluidClassic DEVILS_PEE =
//        new FluidDevilsPeeBlock(new FluidDevilsPee("devils_pee")); // Yeah


    public static ArrayList<FluidCreator> FLUIDS = new ArrayList<FluidCreator>();
    private static HashMap<String,FluidCreator> FLUID_MAP = new HashMap<String,FluidCreator>();
    public static void init(){

        FLUIDS.add(new FluidCreator(new FluidCore("devils_pee",0x00ff00)));
        for(FluidCreator fluidcreator:FLUIDS){
            fluidcreator.getBlockFluidClassic().setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
           FLUID_MAP.put(fluidcreator.getName(),fluidcreator);
        }
    }
    public static FluidCreator getById(String id){
        return FLUID_MAP.get(id);
    }

}
