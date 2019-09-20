package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.IHasModel;
import com.dna.everythingisbad.utils.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


/**
 * This is going to be deprecated in the future for a new system of registering items, blocks, and entities
 */
@EventBusSubscriber
public class RegistryHandler {


    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event)
    {
        //event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }
    @SubscribeEvent
    public static void onRegisterBlock(RegistryEvent.Register<Block> event){

    }

    /**
     * Registers models for items and blocks
     * @param event
     */
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
    public static void playerInteract(PlayerInteractEvent.EntityInteract event){
        PlayerInteractionHandler.reallyMilked(event);
        PlayerInteractionHandler.wolfFed(event);
        //Main.logger.info("Player interact");

    }

    @SubscribeEvent
    public static void attack(AttackEntityEvent event){
        PlayerInteractionHandler.hitSomeone(event);
    }


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void mobSpawn(EntityJoinWorldEvent event){
        if(ModConfig.MOBS_MOVE_FASTER) {
            Entity entity = event.getEntity();
            if (entity instanceof EntityMob) {
                EntityMob mob = (EntityMob) entity;
                try {
                    mob.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 500000, ModConfig.MOB_SPEED_MULTIPLIER));
                } catch (NullPointerException e) {

                }
            }
        }
    }
}
