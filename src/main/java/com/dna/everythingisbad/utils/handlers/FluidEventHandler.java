package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.init.ModPotions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

/**
 * This is for checking when a player is in a particular type of fluid
 */
public class FluidEventHandler {
    /**
     * this detects when a player is in DevilsPee
     * @param player
     */
    public static void inDevilsPee(EntityPlayer player){

        player.addPotionEffect(new PotionEffect(ModPotions.POTION_HIGHNESS.getPotion(),24000,4));

    }
}
