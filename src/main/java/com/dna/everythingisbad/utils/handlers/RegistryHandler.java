package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.block.IModBlockBase;
import com.dna.everythingisbad.entityproperties.CapabilityProvider;
import com.dna.everythingisbad.entityproperties.InitializedPlayerProperties;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.utils.IHasModel;
import com.dna.everythingisbad.utils.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


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
    @SubscribeEvent
    public static void onConfigChanged(OnConfigChangedEvent event){
        if(event.getModID().equals(Reference.MOD_ID)){
            ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
        }

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
            if(block instanceof IModBlockBase)
            {
                ((IModBlockBase)block).registerModels();
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
    @SubscribeEvent
    public static void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {
            if (!event.getObject().hasCapability(InitializedPlayerProperties.PLAYER_PROPERTIES, null)) {
                event.addCapability(new ResourceLocation(Reference.MOD_ID, "player_data"), new CapabilityProvider());
            }
        }
    }






}
