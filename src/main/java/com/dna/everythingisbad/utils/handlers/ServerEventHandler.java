package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.item.ItemPoop;
import com.dna.everythingisbad.utils.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.server.FMLServerHandler;
import scala.util.Random;

import java.util.List;

/**
 * This is for registering events that need to be run on a tick by tick basis
 * on the server side only.
 */
public class ServerEventHandler {
    Random random = new Random();
    //20 ticks per second * 60 seconds * one minecraft day per 20 minutes

    int poop_interval = ModConfig.AUTO_POOP_INTERVAL; // How often do you poop
    int tick_count = random.nextInt(poop_interval)-1; // Starting at a random point in the day-night cycle
    boolean in_server = true;
    /**
     * NEVER EVER EVER EVER EVER EVER EVER EVER SET TIMER EVENTS TO HIGH PRIORITY
     */
    /**
     * Ticks every tick and triggers sub events
     * @param event
     */
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
            if(ModConfig.IS_DEBUG) {
                Main.logger.info("Players Online:" + player_list.size());
            }
            for(EntityPlayerMP player:player_list){
                int random_amount = random.nextInt(ModConfig.AUTO_POOP_MAX-1)+1;
                ItemStack stack = new ItemStack(ModItems.POOP_ITEM,random_amount,3);
                ItemPoop item = (ItemPoop)stack.getItem();
                player.inventory.addItemStackToInventory(stack);
                Block blockAtPlayerPos = player.getServerWorld().getBlockState(new BlockPos(player.posX,player.posY,player.posZ)).getBlock();
                PlayerHandler.playerPooped(player,random_amount);
            }
        }

    }
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void livingTimer(LivingEvent.LivingUpdateEvent event){
        //Main.logger.info("Entity updated: " + event.getEntityLiving().getName());
        PlayerHandler.livingTick(event.getEntityLiving());
//        EntityLivingBase livingBase = event.getEntityLiving();
//        boolean highness_active = livingBase.isPotionActive(ModPotions.POTION_HIGHNESS.getPotion());
//        if(highness_active){
//            if (livingBase instanceof EntityPlayerMP) {
//                // Casting to entityplayermp
//                EntityPlayerMP mp = (EntityPlayerMP)livingBase;
//                int highness_duration = mp.getEntityData().getInteger("highness_duration");
//                //Main.logger.info("Highness duration in client handler: " + highness_duration);
//                PotionEffectHandler.livingEntityHighnessActive(mp, highness_duration);
//            }else{
//                int highness_duration = livingBase.getEntityData().getInteger("highness_duration");
//                PotionEffectHandler.livingEntityHighnessActive(livingBase, highness_duration);
//            }
//        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void playerTimer(TickEvent.PlayerTickEvent event){
//        if(event.player.isDead){
//            PlayerHandler.playerDied(event.player);
//        }
        PlayerHandler.playerTick(event.player);
    }
    @SubscribeEvent(priority = EventPriority.LOW)
    public void playerRespawn(PlayerEvent.PlayerRespawnEvent event){
        PlayerHandler.playerRespawn(event.player);
    }
    @SubscribeEvent
    public void joinedServer(PlayerEvent.PlayerLoggedInEvent event){
        PlayerHandler.playerJoined(event.player);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void playerFinishUseItem(LivingEntityUseItemEvent.Finish event){
        if (event.getItem().getItem() == Items.MILK_BUCKET) {

        }
    }

}
