package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModPotions;
import com.dna.everythingisbad.utils.helpers.TimeHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.HashMap;
import java.util.Random;

/**
 * This is for listening for potion events
 */
public class PotionEffectHandler {

    static Random rand = new Random();
    static int potion_duration = ModPotions.POTION_HIGHNESS.getDuration();
    static int levitation_time = TimeHelper.fromMinutes(1); //1m
    static int nausea_time =
            TimeHelper.fromMinutes(
                    TimeHelper.toMinutes(
                            potion_duration
                    )/2); //Half time

    //public static HashMap<EntityLivingBase,Boolean> potionEffectFirstTimes = new HashMap<EntityLivingBase,Boolean>();

    public static void livingEntityHighnessActive(EntityLivingBase entity){

        // time_left: the amount of ticks left in the potion
        // highness_duration: the amount of ticks remaining there were on the first tick.
        // used to calculate when to give you the nausea, mining fatigue, etc.
        int time_left = entity.getActivePotionEffect(ModPotions.POTION_HIGHNESS.getPotion()).getDuration();
        int highness_duration = entity.getEntityData().getInteger("highness_duration");
        if (highness_duration == -1) {
            entity.getEntityData().setInteger("highness_duration", time_left);
            entity.writeEntityToNBT(entity.getEntityData());
            Main.logger.info("First potion effect tick!");

            // This happens if you get highness while already currently high.

        }else if(time_left>highness_duration && highness_duration!=0){
            entity.getEntityData().setInteger("highness_duration",time_left);
            entity.writeEntityToNBT(entity.getEntityData());
            Main.logger.info("New potion effect tick!");

            // This happens if you get high for the first time.
            // (Because 0 is the default value. From now on it will become -1,
            // as to prevent conflicts? while the nbt tag... loads?
        }else if(highness_duration == 0){
            entity.getEntityData().setInteger("highness_duration", -1);
            entity.writeEntityToNBT(entity.getEntityData());
            Main.logger.info("First time ever getting effect!");
        }

        // Second tick stuff?
        if(time_left == highness_duration-1) {
            //Mining Fatigue
            entity.addPotionEffect(new PotionEffect(Potion.getPotionById(4), time_left, 4));
            //Weakness
            entity.addPotionEffect(new PotionEffect(Potion.getPotionById(18), time_left, 4));
            //Hunger
            entity.addPotionEffect(new PotionEffect(Potion.getPotionById(17), time_left, 4));
        }
        // Halfway through the highness duration
        if(time_left == highness_duration/2) {
            //Nausea
            entity.addPotionEffect(new PotionEffect(Potion.getPotionById(9),time_left,1));
        }
        if(time_left == highness_duration/20) {
            //Levitation
            entity.addPotionEffect(new PotionEffect(Potion.getPotionById(25), time_left, 1));
        }
        // Last tick stuff
        if(time_left == 1){
            // Gives the player a stupid core, with a chance proportional to
            // the duration they underwent over the intended duration (24000 ticks).
            // So, if you only undergo 6000 ticks of highness then you only have a
            // 25% chance of getting a Stupid Core.
            if (rand.nextFloat() > (highness_duration/24000)){
                ItemStack itemstack = new ItemStack(ModItems.STUPID_CORE_ITEM,1);
                if (entity instanceof EntityPlayer){
                    EntityPlayer player = (EntityPlayer)entity;
                    if (!player.inventory.addItemStackToInventory(itemstack)) {
                        player.dropItem(itemstack, false);
                    }else{
                        player.inventory.addItemStackToInventory(itemstack);
                    }
                }else{
                    entity.dropItem(ModItems.STUPID_CORE_ITEM,1);
                }
            }

            // Sets to -1 whenever the player is not high
            entity.getEntityData().setInteger("highness_duration",-1);
            entity.writeEntityToNBT(entity.getEntityData());
        }
    }
    /**
     * Checks when the player has the highness effect active
     * and applies side effects
     * @param event
     */
    @Deprecated
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

    // Restores status effects, for example, after rejoining the game,
    // or drinking milk which removes them; this gives them back.
    public static void restoreEffects(EntityLivingBase entity){

        int time_left = entity.getActivePotionEffect(ModPotions.POTION_HIGHNESS.getPotion()).getDuration();
        int highness_duration = entity.getEntityData().getInteger("highness_duration");

        if(time_left <= highness_duration) {
            //Mining Fatigue
            entity.addPotionEffect(new PotionEffect(Potion.getPotionById(4), time_left, 4));
            //Weakness
            entity.addPotionEffect(new PotionEffect(Potion.getPotionById(18), time_left, 4));
            //Hunger
            entity.addPotionEffect(new PotionEffect(Potion.getPotionById(17), time_left, 4));
        }
        if(time_left <= highness_duration/2) {
            //Nausea
            entity.addPotionEffect(new PotionEffect(Potion.getPotionById(9),time_left,1));
        }
        if(time_left <= highness_duration/20) {
            //Levitation
            entity.addPotionEffect(new PotionEffect(Potion.getPotionById(25), time_left, 1));
        }
    }
}
