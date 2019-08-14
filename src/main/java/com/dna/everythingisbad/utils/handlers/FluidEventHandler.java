package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.init.ModPotions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

public class FluidEventHandler {
    public static void inDevilsPee(EntityPlayer player){

        player.addPotionEffect(new PotionEffect(ModPotions.POTION_HIGHNESS.getPotion(),24000,4));

    }
}
