package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.item.ItemPoop;
import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.utils.ModStates;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
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
                Block blockAtPlayerPos = player.getServerWorld().getBlockState(new BlockPos(player.posX,player.posY,player.posZ)).getBlock();

                if(blockAtPlayerPos.getUnlocalizedName().equals("tile."+ Reference.MOD_ID+":devils_pee")){
                    Main.logger.info("In Devils Pee");
                    FluidEventHandler.inDevilsPee(player);
                }

            }


        }
    }
//    @SubscribeEvent
//    public void joinedServer(PlayerEvent.PlayerLoggedInEvent event){
//        setBlindness(event.player);
//    }
//    @SubscribeEvent
//    public void respawn(PlayerEvent.PlayerRespawnEvent event){
//        setBlindness(event.player);
//    }
//
//    public void setBlindness(EntityPlayerMP player){
//        int rand = random.nextInt(ModStates.BLINDNESS_CHANCE);
//        //Main.logger.info(rand);
//        if (rand == 1){
//            player.addPotionEffect(new PotionEffect(Potion.getPotionById(15),1000000,255));
//        }
//    }
}
