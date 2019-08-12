package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModPotions;
import com.dna.everythingisbad.utils.ClientUtils;
import com.dna.everythingisbad.utils.ModStates;
import ibxm.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import scala.util.Random;


public class ClientTimingHandler {
    Random random = new Random();
    //20 ticks per second * 60 seconds * one minecraft day per 20 minutes
    int poop_interval = ModStates.AUTO_POOP_INTERVAL; // How often do you poop
    int tick_count = random.nextInt(poop_interval)-1; // Starting at a random point in the day-night cycle
    boolean in_server = false;


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void timer(TickEvent.PlayerTickEvent event){
        tick_count++;
        if(tick_count % (poop_interval) == 0 && in_server){
            //gets a random number between 1-6
            int random_amount = random.nextInt(5)+1;
            ItemStack item = new ItemStack(ModItems.POOP_ITEM,random_amount,3);
            ClientUtils.SpawnItem(item);
        }
        PotionEffectHandler.weedActive(event);
    }

    @SubscribeEvent
    public void joinedServer(PlayerLoggedInEvent event){
        in_server = true;
        setBlindness(event.player);
    }

    @SubscribeEvent
    public void leaveServer(PlayerLoggedOutEvent event){
        in_server = false;
    }

    @SubscribeEvent
    public void respawn(PlayerEvent.PlayerRespawnEvent event){
        setBlindness(event.player);
    }

    public void setBlindness(EntityPlayer player){
        int rand = random.nextInt(ModStates.BLINDNESS_CHANCE);
        //Main.logger.info(rand);
        if (rand == 1){
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(15),1000000,255));
        }
    }
}
