package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.Main;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static java.lang.System.out;

@Mod.EventBusSubscriber
public class KeyHandler {
    @SubscribeEvent
    public void pickupItem(EntityItemPickupEvent event) {
        Main.logger.info("Item picked up!");
    }
}
