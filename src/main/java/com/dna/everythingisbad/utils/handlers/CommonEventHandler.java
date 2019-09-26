package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.entityproperties.InitializedPlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class CommonEventHandler {
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

    @SubscribeEvent
    public void playerTimer(TickEvent.PlayerTickEvent event){
//        if(event.player.isDead){
//            PlayerHandler.playerDied(event.player);
//        }
        if(event.player instanceof EntityPlayerMP) {
            PlayerHandler.playerTick((EntityPlayerMP) event.player);
        }
    }
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void livingTimer(LivingEvent.LivingUpdateEvent event){

        PlayerHandler.livingTick(event.getEntityLiving());

    }
    @SubscribeEvent
    public void joinedServer(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event){
        PlayerHandler.playerJoined(event.player);
    }
}
