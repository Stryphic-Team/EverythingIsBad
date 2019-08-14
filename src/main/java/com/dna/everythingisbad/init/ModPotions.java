package com.dna.everythingisbad.init;

import com.dna.everythingisbad.utils.PotionBuilder;

public class ModPotions {
    public static PotionBuilder POTION_HIGHNESS = new PotionBuilder("highness",24000,false,4351539,0,0);
    public static PotionBuilder POTION_COMMON_COLD = new PotionBuilder("common_cold",24000,true,4390707,1,0);
    public static PotionBuilder[] POTIONS = new PotionBuilder[]{
            POTION_HIGHNESS,POTION_COMMON_COLD
    };
    public static void init(){
        for(PotionBuilder potion:POTIONS){
            potion.registerPotion();
        }
    }
}
