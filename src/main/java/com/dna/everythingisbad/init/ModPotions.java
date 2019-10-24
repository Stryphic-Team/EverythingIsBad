package com.dna.everythingisbad.init;

import com.dna.everythingisbad.utils.prototypes.PotionPrototype;

public class ModPotions {
    public static PotionPrototype POTION_HIGHNESS = new PotionPrototype("highness",24000,false,4351539,0,0);
    public static PotionPrototype POTION_COMMON_COLD = new PotionPrototype("common_cold",24000,true,4390707,1,0);
    public static PotionPrototype POTION_HEPATITIS = new PotionPrototype("hepatitis",12000,true,0xFFFF00,2,0);
    public static PotionPrototype POTION_ADRENALINE = new PotionPrototype("adrenaline",3600,false,0xabcdef,3,0);
    public static PotionPrototype POTION_WITHDRAWAL = new PotionPrototype("withdrawal",1000,true, 0xff5a35, 4, 0);
    public static PotionPrototype POTION_TOBACCO_WITHDRAWAL = new PotionPrototype("tobacco_withdrawal", 1000,true,0xff5a35,5,0);
    public static PotionPrototype POTION_STIMULATION = new PotionPrototype("stimulation",3600,false,0xff8c00,6,0);

    public static PotionPrototype[] POTIONS = new PotionPrototype[]{
            POTION_HIGHNESS,POTION_COMMON_COLD,POTION_HEPATITIS,POTION_ADRENALINE,POTION_WITHDRAWAL,
            POTION_TOBACCO_WITHDRAWAL,POTION_STIMULATION
    };

    public static void init(){
        for(PotionPrototype potion:POTIONS){
            potion.registerPotion();
        }
    }
}
