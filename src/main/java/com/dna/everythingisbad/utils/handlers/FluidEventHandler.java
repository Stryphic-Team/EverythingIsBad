package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.init.ModPotions;
import com.dna.everythingisbad.init.ModSoundEvents;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

/**
 * This is for checking when a player is in a particular type of fluid
 */
public class FluidEventHandler {
    static int song_timer = 0; // Number of ticks in the song
    /**
     * this detects when a player is in DevilsPee
     * @param player
     */
    public static void inDevilsPee(EntityPlayer player){
        World worldIn = player.getEntityWorld();
        player.addPotionEffect(new PotionEffect(ModPotions.POTION_HIGHNESS.getPotion(),24000,4));

        if (song_timer<=0) {
            song_timer=3600;
            worldIn.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, ModSoundEvents.SOUND_EVENT_GODS_PEE, SoundCategory.RECORDS, 1F, 1f);
        }else{
            song_timer--;
        }
    }
}
