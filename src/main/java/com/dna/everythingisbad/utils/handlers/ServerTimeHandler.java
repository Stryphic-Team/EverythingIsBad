package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.item.ItemPoop;
import com.dna.everythingisbad.utils.ModStates;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.server.FMLServerHandler;
import scala.util.Random;

import java.util.List;

public class ServerTimeHandler {
    Random random = new Random();
    //20 ticks per second * 60 seconds * one minecraft day per 20 minutes

    int poop_interval = ModStates.AUTO_POOP_INTERVAL; // How often do you poop
    int tick_count = random.nextInt(poop_interval)-1; // Starting at a random point in the day-night cycle
    boolean in_server = true;


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void timer(TickEvent.ServerTickEvent event){
        tick_count++;

        if(tick_count % (poop_interval) == 0 && in_server){
            //gets a random number between 1-6


            List<EntityPlayerMP> player_list =
                    FMLServerHandler
                    .instance()
                    .getServer()
                    .getPlayerList()
                    .getPlayers();
            if(ModStates.IS_DEBUG) {
                Main.logger.info("Players Online:" + player_list.size());
            }
            for(EntityPlayerMP player:player_list){
                int random_amount = random.nextInt(5)+1;
                ItemStack stack = new ItemStack(ModItems.POOP_ITEM,random_amount,3);
                ItemPoop item = (ItemPoop)stack.getItem();
                player.inventory.addItemStackToInventory(stack);
            }


        }
    }
}
