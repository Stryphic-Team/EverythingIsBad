package com.dna.everythingisbad.utils;

import com.dna.everythingisbad.potion.PotionDrugBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionCreator {
    private Potion potion;
    private PotionType potionType;
    private int duration;

    public PotionCreator(String name,int duration){
        String namespaced_name = CommonUtils.createUnlocalizedName(name);
        //creates the base of the potion
        potion = new PotionDrugBase(name,false,0,0,0);
        //assigns a new potion effect to the the new potion
        potionType = new PotionType(name,new PotionEffect[] {
            new PotionEffect(potion,duration)
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
}
