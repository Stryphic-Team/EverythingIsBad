package com.dna.everythingisbad.utils.handlers;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.item.ItemPoop;
import com.dna.everythingisbad.utils.ModStates;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.server.FMLServerHandler;
import scala.util.Random;

import java.util.List;


public class ClientTimingHandler {
    Random random = new Random();
    //20 ticks per second * 60 seconds * one minecraft day per 20 minutes
    int poop_interval = ModStates.AUTO_POOP_INTERVAL; // How often do you poop
    int tick_count = random.nextInt(poop_interval)-1; // Starting at a random point in the day-night cycle
    boolean in_server = false;


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void timer(TickEvent.ClientTickEvent event){
        tick_count++;

        if(tick_count % (poop_interval) == 0 && in_server){
            //gets a random number between 1-6
            int random_amount = random.nextInt(5)+1;

            MinecraftServer server_instance = FMLCommonHandler.instance().getMinecraftServerInstance();
            List<EntityPlayerMP> player_list = server_instance.getPlayerList().getPlayers();



            for(EntityPlayerMP player:player_list){
                World world = server_instance.getWorld(player.dimension);
                ItemStack stack = new ItemStack(ModItems.POOP_ITEM,random_amount,3);
                EntityItem item = new EntityItem(world, player.posX,player.posY,player.posZ, stack);
                world.spawnEntity(item);
            }


        }
    }

    @SubscribeEvent
    public void joinedServer(PlayerLoggedInEvent event){
        in_server = true;
    }

    @SubscribeEvent
    public void leaveServer(PlayerLoggedOutEvent event){
        in_server = false;
    }

}