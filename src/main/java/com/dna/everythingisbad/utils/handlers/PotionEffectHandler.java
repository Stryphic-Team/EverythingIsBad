package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.init.ModPotions;
import com.dna.everythingisbad.utils.helpers.TimeHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * This is for listening for potion events
 */
public class PotionEffectHandler {
    /**
     * Checks when the player has the highness effect active
     * and applies side effects
     * @param event
     */
    public static void weedActive(TickEvent.PlayerTickEvent event){
        boolean isActive = false;
        int potion_duration = ModPotions.POTION_HIGHNESS.getDuration();
        int levitation_time = TimeHelper.fromMinutes(1); //1m
        int nausea_time =
                TimeHelper.fromMinutes(
                    TimeHelper.toMinutes(
                        potion_duration
                    )/2); //Half time
        //We don't need tick count because we can get the time left on the potion
        if (event.player.isPotionActive(ModPotions.POTION_HIGHNESS.getPotion())){
            isActive = true;
        }
        if (isActive){
            EntityPlayer player_instance = event.player;
            //Time in ticks left
            int time_left = player_instance.getActivePotionEffect(ModPotions.POTION_HIGHNESS.getPotion()).getDuration();

            if(time_left == potion_duration) {
                //Mining Fatigue
                player_instance.addPotionEffect(new PotionEffect(Potion.getPotionById(4), potion_duration, 4));
                //Weakness
                player_instance.addPotionEffect(new PotionEffect(Potion.getPotionById(18), potion_duration, 4));
                //Hunger
                player_instance.addPotionEffect(new PotionEffect(Potion.getPotionById(17), potion_duration, 4));
            }
            if(time_left == nausea_time) {
                //Nausea
                player_instance.addPotionEffect(new PotionEffect(Potion.getPotionById(9),nausea_time,1));
            }
            if(time_left == levitation_time) {
                //Levitation
                player_instance.addPotionEffect(new PotionEffect(Potion.getPotionById(25), levitation_time, 1));
            }
        }
    }
}
