package com.dna.everythingisbad.utils;

import com.dna.everythingisbad.potion.PotionDrugBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionBuilder {
    private Potion potion;
    private PotionType potionType;
    private PotionEffect potionEffect;
    private int duration;

    public PotionBuilder(String name, int duration,boolean isBadPotion,int color,int IconIndexX,int IconIndexY){
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
    public void registerPotion(){
        ForgeRegistries.POTIONS.register(potion);
        ForgeRegistries.POTION_TYPES.register(potionType);
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public PotionType getPotionType() {
        return potionType;
    }
    public void setPotionType(PotionType potionType) {
        this.potionType = potionType;
    }
    public Potion getPotion() {
        return potion;
    }
    public void setPotion(Potion potion) {
        this.potion = potion;
    }
    public PotionEffect getPotionEffect() { return potionEffect; }
}
