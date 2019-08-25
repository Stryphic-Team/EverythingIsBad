package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModPotions;
import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.utils.ClientUtils;
import com.dna.everythingisbad.utils.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import scala.util.Random;

/**
 * This is for events that happen on the tick by tick basis
 * on the client side only
 */

public class ClientTimingHandler {
    Random random = new Random();
    //20 ticks per second * 60 seconds * one minecraft day per 20 minutes
    int poop_interval = ModConfig.AUTO_POOP_INTERVAL; // How often do you poop
    int tick_count = random.nextInt(poop_interval)-1; // Starting at a random point in the day-night cycle
    boolean in_server = false;


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void timer(TickEvent.PlayerTickEvent event){
        tick_count++;
        if(tick_count % (poop_interval) == 0 && in_server){
            //gets a random number between 1-6
            int random_amount = random.nextInt(ModConfig.AUTO_POOP_MAX-1)+1;
            ItemStack item = new ItemStack(ModItems.POOP_ITEM,random_amount,3);
            ClientUtils.SpawnItem(item);
            PlayerHandler.playerPooped(event.player,random_amount);
        }
        //PotionEffectHandler.weedActive(event);
        if(event.player.isDead){
            PlayerHandler.playerDied(event.player);
        }
    }
    @SubscribeEvent(priority = EventPriority.LOW)
    public void livingTimer(LivingEvent.LivingUpdateEvent event){
        EntityLivingBase livingBase = event.getEntityLiving();
        boolean highness_active = livingBase.isPotionActive(ModPotions.POTION_HIGHNESS.getPotion());
        if(highness_active){
            PotionEffectHandler.livingEntityHighnessActive(livingBase);
        }
    }

    @SubscribeEvent
    public void joinedServer(PlayerLoggedInEvent event){
        in_server = true;
        PlayerHandler.playerJoined(event.player);
    }

    @SubscribeEvent
    public void leaveServer(PlayerLoggedOutEvent event){
        in_server = false;
    }

    @SubscribeEvent
    public void respawn(PlayerEvent.PlayerRespawnEvent event){

    }
    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void fluidTimer(TickEvent.PlayerTickEvent event){
        EntityPlayer player = event.player;
        Block blockAtPlayerPos = player.getEntityWorld().getBlockState(new BlockPos(player.posX,player.posY,player.posZ)).getBlock();
        if(blockAtPlayerPos.getUnlocalizedName().equals("tile."+ Reference.MOD_ID+":devils_pee")){
            FluidEventHandler.inDevilsPee(player);
        }
    }

}
