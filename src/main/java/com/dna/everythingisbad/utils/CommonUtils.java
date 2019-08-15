package com.dna.everythingisbad.utils;

import com.dna.everythingisbad.reference.Reference;

/**
 * This is helper class for the client and the server
 */
public class CommonUtils {
    /**
     * Creates a namespaced name with global mod id
     * @param name
     * @return
     */
    public static String createUnlocalizedName(String name){
        return Reference.MOD_ID + ":" + name;
    }

}
