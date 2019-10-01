package com.dna.everythingisbad.init;

import com.dna.everythingisbad.utils.prototypes.PotionPrototype;

public class ModPotions {
    public static PotionPrototype POTION_HIGHNESS = new PotionPrototype("highness",24000,false,4351539,0,0);
    public static PotionPrototype POTION_COMMON_COLD = new PotionPrototype("common_cold",24000,true,4390707,1,0);
    public static PotionPrototype POTION_HEPATITIS = new PotionPrototype("hepatitis",12000,true,0xFFFF00,2,0);
    public static PotionPrototype POTION_ADRENALINE = new PotionPrototype("adrenaline",6000,false,0xabcdef,3,0);

    public static PotionPrototype[] POTIONS = new PotionPrototype[]{
            POTION_HIGHNESS,POTION_COMMON_COLD,POTION_HEPATITIS,POTION_ADRENALINE
    };

    public static void init(){
        for(PotionPrototype potion:POTIONS){
            potion.registerPotion();
        }
    }
}
