package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.entityhandlers.LivingHandlerBase;
import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.entityhandlers.handlers.PlayerCommonColdHandler;
import com.dna.everythingisbad.entityhandlers.handlers.PlayerReligionHandler;
import com.dna.everythingisbad.entityhandlers.handlers.PlayerSoulHandler;
import com.dna.everythingisbad.entityproperties.InitializedPlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;

public class CommonEventHandler {
    public static ArrayList<PlayerHandlerBase> PLAYER_HANDLERS = new ArrayList<PlayerHandlerBase>();
    public static ArrayList<LivingHandlerBase> LIVING_HANDLERS = new ArrayList<LivingHandlerBase>();

    public static PlayerHandlerBase PLAYER_RELIGION_HANDLER = new PlayerReligionHandler();
    public static PlayerHandlerBase PLAYER_COMMON_COLD_HANDLER = new PlayerCommonColdHandler();
    public static PlayerHandlerBase PLAYER_SOUL_HANDLER = new PlayerSoulHandler();


    @SubscribeEvent
    public void livingDamage(LivingDamageEvent event){
        PlayerHandler.livingDamage(event,event.getEntityLiving());
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void livingTimer(LivingEvent.LivingUpdateEvent event){

        PlayerHandler.livingTick(event.getEntityLiving());

        for (LivingHandlerBase livingHandler : LIVING_HANDLERS) {
            livingHandler.livingTick(event.getEntityLiving());
        }


    }

    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            if (event.getOriginal().hasCapability(InitializedPlayerProperties.PLAYER_PROPERTIES, null)) {
                PlayerProperties oldStore = event.getOriginal().getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES, null);
                PlayerProperties newStore = InitializedPlayerProperties.getPlayerProperties(event.getEntityPlayer());
                newStore.copyFrom(oldStore);
            }
        }
    }
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void playerTimer(TickEvent.PlayerTickEvent event){
        if(event.player instanceof EntityPlayerMP && event.phase == TickEvent.Phase.END) {
            PlayerHandler.playerTick((EntityPlayerMP) event.player);
        }
        if(event.phase == TickEvent.Phase.END) {
            for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                playerHandler.playerTick(event.player);
            }
        }
    }

    @SubscribeEvent (priority = EventPriority.LOW)
    public void blockBreaken(BlockEvent.BreakEvent event){
        PlayerHandler.playerBrokeBlock(event);
        for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
            playerHandler.playerBreakBlock(event.getPlayer(),event.getState());
        }
    }

    @SubscribeEvent
    public void leaveServer(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent event){
        PlayerHandler.playerLeft(event.player);
        for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
            playerHandler.playerLeave(event.player);
        }
    }

    @SubscribeEvent
    public void respawn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent event){
        PlayerHandler.playerRespawn(event.player);
        for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
            playerHandler.playerRespawn(event.player);
        }
    }



    @SubscribeEvent(priority = EventPriority.LOW)
    public void smeltItem(net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent event){
        PlayerHandler.playerSmelted(event, event.player);
        for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
            playerHandler.playerSmeltItem(event.player,event.smelting);
        }
    }
    @SubscribeEvent
    public void joinedServer(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event){
        //PlayerHandler.playerJoined(event.player);

        for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
            playerHandler.playerJoined(event.player);
        }
        if(!event.player.world.isRemote){
            PlayerProperties playerProperties = event.player.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);
            if(playerProperties != null){
                if(!playerProperties.hasBeenInitialized()){

                    for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                        playerHandler.playerPreInitialization(event.player);
                        playerHandler.playerInitialization(event.player);
                    }
                    playerProperties.setHasBeenInitialized(true);
                    playerProperties.saveNBTData(event.player.getEntityData());
                    for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                        playerHandler.playerPostInitialization(event.player);
                    }
                }
            }
        }


    }
}
