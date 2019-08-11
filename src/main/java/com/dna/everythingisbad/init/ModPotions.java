package com.dna.everythingisbad.init;

import com.dna.everythingisbad.utils.PotionCreator;

public class ModPotions {
    public static PotionCreator POTION_HIGHNESS = new PotionCreator("highness");
    public static PotionCreator[] POTIONS = new PotionCreator[]{
            POTION_HIGHNESS
    };
    public static void init(){
        for(PotionCreator potion:POTIONS){
            potion.registerPotion();
        }
    }
}
