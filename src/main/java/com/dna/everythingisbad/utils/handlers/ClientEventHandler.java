package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.client.RenderYellow;
import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
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
    public void joinedServer(EntityJoinWorldEvent event){
        if (event.getEntity() instanceof EntityPlayerSP){
            for (PlayerHandlerBase playerHandler : CommonEventHandler.PLAYER_HANDLERS) {
                playerHandler.clientPlayerJoined((EntityPlayerSP)event.getEntity());
            }
        }

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

    @SubscribeEvent
    public void renderGameOverlay(RenderGameOverlayEvent event){
        for (PlayerHandlerBase playerHandler : CommonEventHandler.PLAYER_HANDLERS) {
            playerHandler.gameOverlayEvent(event);
        }
    }

//    @SubscribeEvent(priority = EventPriority.HIGH)
//    public void renderGameOverlayEvent (RenderGameOverlayEvent event){
//        yellow.yelo();
//    }
}
