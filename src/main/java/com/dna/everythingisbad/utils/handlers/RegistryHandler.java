package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModEntities;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModPotions;
import com.dna.everythingisbad.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

@EventBusSubscriber
public class RegistryHandler {


    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }
    @SubscribeEvent
    public static void onEntitiesRegistered(RegistryEvent.Register<EntityEntry> event)
    {

        ModEntities.init();

        event.getRegistry().registerAll(ModEntities.ENTITIES);
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
    }
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event)
    {
        for(Item item : ModItems.ITEMS)
        {
            if(item instanceof IHasModel)
            {
                ((IHasModel)item).registerModels();
            }
        }

        for(Block block : ModBlocks.BLOCKS)
        {
            if(block instanceof IHasModel)
            {
                ((IHasModel)block).registerModels();
            }
        }

    }
    @SubscribeEvent
    public static void weedActive(TickEvent.PlayerTickEvent event){
        boolean isActive = false;
        int hunger_interval = 20 * 3; //3s
        int levitation_time = 20 * 60; //1m
        int nausea_time = 20 * 90; //1.5m
        int tick_count = 1; //Doesn't need to be a float
        if (event.player.isPotionActive(ModPotions.POTION_HIGHNESS.getPotion())){
            isActive = true;
        }else{
            isActive = false;
        }
        tick_count++;
        if (isActive){

            EntityPlayer player_instance = event.player;
            int potion_duration = player_instance.getActivePotionEffect(ModPotions.POTION_HIGHNESS.getPotion()).getDuration();
            //Mining Fatigue
            player_instance.addPotionEffect(new PotionEffect(Potion.getPotionById(4),100,4));
            //Weakness
            player_instance.addPotionEffect(new PotionEffect(Potion.getPotionById(18),100,4));
            //Hunger
            player_instance.addPotionEffect(new PotionEffect(Potion.getPotionById(17),100,4));

            if(potion_duration == nausea_time) {
                //Nausea
                player_instance.addPotionEffect(new PotionEffect(Potion.getPotionById(9),nausea_time,1));
            }
            if(potion_duration == levitation_time) {
                //Levitation
                player_instance.addPotionEffect(new PotionEffect(Potion.getPotionById(25), levitation_time, 1));
            }
        }
    }
}
