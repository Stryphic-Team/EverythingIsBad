package com.dna.everythingisbad.potion;

import com.dna.everythingisbad.reference.Reference;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionDrugBase extends Potion {
    public PotionDrugBase(String name, boolean isBadPotion, int color,int IconIndexX,int IconIndexY){
        super(isBadPotion,color);
        setIconIndex(IconIndexX,IconIndexY);
        setRegistryName(new ResourceLocation(Reference.RESOURCE_PREFIX + name));

    }
}
