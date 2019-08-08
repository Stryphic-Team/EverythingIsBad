package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.Main;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


public class TestHandler {
    @SubscribeEvent
    public void timer(TickEvent event){
        Main.logger.info("A tick happened");
    }
}
