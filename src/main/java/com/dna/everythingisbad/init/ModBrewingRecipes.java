package com.dna.everythingisbad.init;

import net.minecraft.init.PotionTypes;
import net.minecraft.potion.PotionHelper;

public class ModBrewingRecipes {
    public ModBrewingRecipes(){

    }
    public static void init(){
        PotionHelper.addMix(PotionTypes.AWKWARD,ModItems.DEVILS_CABBAGE_ITEM,ModPotions.POTION_HIGHNESS.getPotionType());
        PotionHelper.addMix(PotionTypes.AWKWARD,ModItems.POOP_ITEM,ModPotions.POTION_HEPATITIS.getPotionType());
    }
}
