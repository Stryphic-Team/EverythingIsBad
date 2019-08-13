package com.dna.everythingisbad.init;

import com.dna.everythingisbad.utils.PotionBuilder;

public class ModPotions {
    public static PotionBuilder POTION_HIGHNESS = new PotionBuilder("highness",24000);
    public static PotionBuilder POTION_COMMON_COLD = new PotionBuilder("common_cold",48000);
    public static PotionBuilder[] POTIONS = new PotionBuilder[]{
            POTION_HIGHNESS,POTION_COMMON_COLD
    };
    public static void init(){
        for(PotionBuilder potion:POTIONS){
            potion.registerPotion();
        }
    }
}
