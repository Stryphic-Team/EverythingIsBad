package com.dna.everythingisbad.utils;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.HashMap;
import java.util.Map;

public class FluidCache {
    public static HashMap<Integer, Fluid> numericFluid = new HashMap<Integer, Fluid>();
    public static HashMap<String,Integer> fluidIntegerMap = new HashMap<String,Integer>();
    public static Map<String, Fluid> fluidMap;

    public static void init(){
        fluidMap =  FluidRegistry.getRegisteredFluids();
        Object[] fluidKeyArray = fluidMap.keySet().toArray();
        for(int i = 0;i<fluidKeyArray.length;i++) {
            fluidIntegerMap.put((String)fluidKeyArray[i], i);
            numericFluid.put(i, fluidMap.get((String)fluidKeyArray[i]));
        }

    }
    public static int toInt(String fluidName){
        if(fluidIntegerMap.containsKey(fluidName)){
            return fluidIntegerMap.get(fluidName);
        }else{
            return -1;
        }

    }
    public static Fluid fromInt(int fluidValue){
        if(numericFluid.containsKey(fluidValue)) {
            return numericFluid.get(fluidValue);
        }else{
            return null;
        }
    }
}
