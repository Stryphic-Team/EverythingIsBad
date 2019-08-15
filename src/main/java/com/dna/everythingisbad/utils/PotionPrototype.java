package com.dna.everythingisbad.utils;

import com.dna.everythingisbad.potion.PotionDrugBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionPrototype {
    private Potion potion;
    private PotionType potionType;
    private PotionEffect potionEffect;
    private int duration;

    /**
     * Takes a name, duration, isBad, color, IconIndexX, IconIndexY and creates a potion
     * and potion effect and registers the item
     * @param name
     * @param duration
     * @param isBadPotion
     * @param color
     * @param IconIndexX
     * @param IconIndexY
     */
    public PotionPrototype(String name, int duration, boolean isBadPotion, int color, int IconIndexX, int IconIndexY){
        String namespaced_name = CommonUtils.createUnlocalizedName(name);
        //creates the base of the potion
        potion = new PotionDrugBase(name,isBadPotion,color,IconIndexX,IconIndexY);
        //assigns a new potion effect to the the new potion
        potionEffect = new PotionEffect(potion,duration);

        potionType = new PotionType(name,new PotionEffect[] {
            potionEffect
        }).setRegistryName(namespaced_name);
        this.duration = duration;
    }

    /**
     * Registers the potion and the potion type for the new potion
     * NEEDS TO BE RUN AFTER INSTANTIATED
     */
    public void registerPotion(){
        ForgeRegistries.POTIONS.register(potion);
        ForgeRegistries.POTION_TYPES.register(potionType);
    }

    /**
     * Gets the duration of the potion effects
     * @return int
     */
    public int getDuration() {
        return duration;
    }

    /**
     * returns the potion type
     * @return PotionType
     */
    public PotionType getPotionType() {
        return potionType;
    }

    /**
     * returns the Potion
     * @return Potion
     */
    public Potion getPotion() {
        return potion;
    }

    /**
     * returns the potion effect
     * @return PotionEffect
     */
    public PotionEffect getPotionEffect() { return potionEffect; }
}
