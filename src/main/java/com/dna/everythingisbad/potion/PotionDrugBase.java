package com.dna.everythingisbad.potion;

import com.dna.everythingisbad.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionDrugBase extends Potion {
    public PotionDrugBase(String name, boolean isBadPotion, int color,int IconIndexX,int IconIndexY){
        super(isBadPotion,color);
        setPotionName("effect." + name); // THIS is what the lang file uses
        setIconIndex(IconIndexX,IconIndexY);
        setRegistryName(new ResourceLocation(Reference.RESOURCE_PREFIX + name));
    }

    @Override
    public boolean hasStatusIcon(){
        ResourceLocation loc = new ResourceLocation(Reference.RESOURCE_PREFIX + "textures/gui/potion_effects.png");
        Minecraft.getMinecraft().getTextureManager().bindTexture(loc);
        return true;
    }

    @Override
    public java.util.List<net.minecraft.item.ItemStack> getCurativeItems()
    {
        // Return an empty array list of items
        java.util.ArrayList<net.minecraft.item.ItemStack> ret = new java.util.ArrayList<net.minecraft.item.ItemStack>();
        return ret;
    }
}
