package com.dna.everythingisbad.item.food;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.utils.IHasModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ItemFoodBase extends ItemFood implements IHasModel {
    public ItemFoodBase(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this,0, "inventory");

    }
    public void CureEffect(EntityLivingBase entityLiving, Potion potion){

        String potionname = potion.getName();
        PotionEffect therighteffect = null;
        for (PotionEffect effect:entityLiving.getActivePotionEffects()){
            if (effect.getEffectName().equals(potionname)) {
                therighteffect = effect;
            }
        }
        if (therighteffect != null){ entityLiving.removePotionEffect(therighteffect.getPotion()); }
    }
}
