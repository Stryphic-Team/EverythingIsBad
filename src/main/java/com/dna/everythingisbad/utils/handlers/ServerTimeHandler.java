package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.item.ItemPoop;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.management.PlayerList;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.server.FMLServerHandler;

import scala.util.Random;

public class ServerTimeHandler {
    Random random = new Random();
    //20 ticks per second * 60 seconds * one minecraft day per 20 minutes
    //int poop_interval = 20*60*20; // How often do you poop
    int poop_interval = 20*60*20; // How often do you poop
    int tick_count = random.nextInt(poop_interval)-1; // Starting at a random point in the day-night cycle
    boolean in_server = true;
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void timer(TickEvent.ServerTickEvent event){
        tick_count++;

        if(tick_count % (poop_interval) == 0 && in_server){
            //gets a random number between 1-6
            int random_amount = random.nextInt(5)+1;
            ItemStack stack = new ItemStack(ModItems.POOP_ITEM,random_amount,3);
            ItemPoop item = (ItemPoop)stack.getItem();
            PlayerList playerlist = FMLServerHandler.instance().getServer().getPlayerList();
            for(String playerString:playerlist.getOnlinePlayerNames()){
                EntityPlayerMP player = playerlist.getPlayerByUsername(playerString);
                player.inventory.addItemStackToInventory(stack);
            }


        }
    }
}
