package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.Main;

import com.dna.everythingisbad.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.server.management.PlayerList;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;



public class TestHandler {
    int tick_count = 0;
    boolean in_server = false;

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void timer(TickEvent.ClientTickEvent event){
        tick_count++;
        if(tick_count > 4000 && in_server){
            ItemStack stack = new ItemStack(ModItems.POOP_ITEM);


            FMLClientHandler.instance().getClient().player.inventory.addItemStackToInventory(stack);
            tick_count = 0;
        }
    }
    @SubscribeEvent
    public void joinedServer(PlayerLoggedInEvent event){

        in_server = true;
    }
}
