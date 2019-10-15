package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.client.RenderYellow;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import scala.util.Random;

/**
 * This is for events that happen on the tick by tick basis
 * on the client side only
 */

public class ClientEventHandler {
    Random random = new Random();
    RenderYellow yellow = new RenderYellow();

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void livingTimer(LivingEvent.LivingUpdateEvent event){
        //PlayerHandler.livingTick(event.getEntityLiving());

    }
    @SubscribeEvent
    public void joinedServer(PlayerLoggedInEvent event){
        //PlayerHandler.playerJoined(event.player);
        //event.player.getEntityData().setInteger("highness_duration",);
    }

    @SubscribeEvent
    public void leaveServer(PlayerLoggedOutEvent event){
        //PlayerHandler.playerLeft(event.player);
    }

    @SubscribeEvent
    public void respawn(PlayerEvent.PlayerRespawnEvent event){
        //PlayerHandler.playerRespawn(event.player);
    }



    @SubscribeEvent(priority = EventPriority.LOW)
    public void smeltItem(PlayerEvent.ItemSmeltedEvent event){
        //PlayerHandler.playerSmelted(event, event.player);
    }

    @SubscribeEvent
    public void lootLoad(LootTableLoadEvent event) {

    }


//    @SubscribeEvent(priority = EventPriority.HIGH)
//    public void renderGameOverlayEvent (RenderGameOverlayEvent event){
//        yellow.yelo();
//    }
}
