package com.dna.everythingisbad.utils;

import com.dna.everythingisbad.reference.Reference;

public class Utils {
    public static String createUnlocalizedName(String name){
        return Reference.MOD_ID + ":" + name;
    }
}
