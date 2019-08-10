package com.dna.everythingisbad.init;

import com.dna.everythingisbad.potion.PotionDrugBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModPotions {
    public static final Potion POTION_EFFECT_DRUG_BASE = new PotionDrugBase("highness",false,0,0,0);

    public static final PotionType POTION_DRUG_BASE = new PotionType("highness",new PotionEffect[] {
            new PotionEffect(POTION_EFFECT_DRUG_BASE,24000)
    }).setRegistryName("highness");

    // long versions and other variants can go here

    public static void registerPotions(){
        registerPotion(POTION_DRUG_BASE,POTION_EFFECT_DRUG_BASE);
    }

    public static void registerPotion(PotionType defaultPotion, Potion effect){
        ForgeRegistries.POTIONS.register(effect);
        ForgeRegistries.POTION_TYPES.register(defaultPotion);
    }
}
