package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.entityhandlers.LivingHandlerBase;
import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.entityhandlers.WorldHandlerBase;
import com.dna.everythingisbad.entityhandlers.handlers.*;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerPropertiesCapability;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;

public class CommonEventHandler {
    public static ArrayList<PlayerHandlerBase> PLAYER_HANDLERS = new ArrayList<PlayerHandlerBase>();
    public static ArrayList<LivingHandlerBase> LIVING_HANDLERS = new ArrayList<LivingHandlerBase>();
    public static ArrayList<WorldHandlerBase> WORLD_HANDLERS = new ArrayList<WorldHandlerBase>();

    //Player Handlers
    public static PlayerHandlerBase PLAYER_RELIGION_HANDLER = new PlayerReligionHandler();
    public static PlayerHandlerBase PLAYER_COMMON_COLD_HANDLER = new PlayerCommonColdHandler();
    public static PlayerHandlerBase PLAYER_SOUL_HANDLER = new PlayerSoulHandler();
    public static PlayerHandlerBase PLAYER_BLINDNESS_HANDLER = new PlayerBlindnessHandler();
    public static PlayerHandlerBase PLAYER_BABY_HANDLER = new PlayerBabyHandler();
    public static PlayerHandlerBase PLAYER_QUESTION_MARK_BLOCK_HANDLER = new PlayerQuestionMarkBlockHandler();
    public static PlayerHandlerBase PLAYER_GRASS_BREAK_HANDLER = new PlayerGrassBreakHandler();
    public static PlayerHandlerBase PLAYER_HEART_RATE_HANDLER = new PlayerHeartRateHandler();
    public static PlayerHandlerBase PLAYER_JOB_HANDLER = new PlayerJobHandler();
    public static PlayerHandlerBase PLAYER_ADDICTION_HANDLER = new PlayerAngelDustAddictionHandler();
    public static PlayerHandlerBase PLAYER_TOBACCO_ADDICTION_HANDLER = new PlayerTobaccoAddictionHandler();
    public static PlayerHandlerBase PLAYER_MONEY_HANDLER = new PlayerMoneyHandler();
    public static PlayerHandlerBase PLAYER_COLLEGE_STUDENT_HANDLER = new PlayerCollegeStudentHandler();
    //public static PlayerScreenYellowHandler PLAYER_SCREEN_YELLOW_HANDLER = new PlayerScreenYellowHandler();
    //Living Handlers
    public static LivingHandlerBase LIVING_JESUS_HEAL_HANDLER = new LivingJesusHealHandler();
    public static LivingHandlerBase LIVING_VILLAGER_BABY_HANDLER = new LivingVillagerBabyHandler();
    public static LivingHandlerBase LIVING_FLUID_HANDLER = new LivingFluidHandler();
    public static LivingHandlerBase LIVING_HEAVEN_HANDLER = new LivingHeavenHandler();
    public static LivingHandlerBase LIVING_POTION_EFFECT_HANDLER = new LivingPotionEffectHandler();
    public static LivingHandlerBase LIVING_POOP_HANDLER = new LivingPoopHandler();
    //World Handlers
    public static WorldHandlerBase WORLD_BANK_HANDLER = new WorldBankHandler();




    @SubscribeEvent
    public void livingDamage(LivingDamageEvent event){
        //PlayerHandler.livingDamage(event,event.getEntityLiving());
        if(!event.getEntity().getEntityWorld().isRemote) {
            for (LivingHandlerBase livingHandler : LIVING_HANDLERS) {
                livingHandler.livingDamage(event.getEntityLiving(), event.getSource(), event.getAmount());
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void livingTimer(LivingEvent.LivingUpdateEvent event){
        //PlayerHandler.livingTick(event.getEntityLiving());
        if(!event.getEntity().getEntityWorld().isRemote) {
            for (LivingHandlerBase livingHandler : LIVING_HANDLERS) {
                livingHandler.livingTick(event.getEntityLiving());
            }
        }


    }

    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            if (event.getOriginal().hasCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES, null)) {
                PlayerProperties oldStore = event.getOriginal().getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES, null);
                PlayerProperties newStore = PlayerPropertiesCapability.getPlayerProperties(event.getEntityPlayer());
                newStore.copyFrom(oldStore);
            }
        }
    }
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void playerTimer(TickEvent.PlayerTickEvent event){
        // server side
        if(!event.player.getEntityWorld().isRemote) {
            if (event.phase == TickEvent.Phase.END) {
                for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                    playerHandler.playerTick(event.player);
                }
            }
        // client side (ONLY USED BY THE HEART RATE MONITOR)
        }else{
            if (event.phase == TickEvent.Phase.END) {
                for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                    playerHandler.clientPlayerTick(event.player);
                }
            }
        }
    }

    @SubscribeEvent (priority = EventPriority.LOW)
    public void blockBroken(BlockEvent.BreakEvent event){
        //PlayerHandler.playerBrokeBlock(event);
        if(!event.getPlayer().getEntityWorld().isRemote) {
            for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                playerHandler.playerBreakBlock(event.getPlayer(), event.getState());

            }
        }
    }

    @SubscribeEvent
    public void leaveServer(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent event){
        //PlayerHandler.playerLeft(event.player);
        if(!event.player.getEntityWorld().isRemote) {
            for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                playerHandler.playerLeave(event.player);
            }
        }
    }

    @SubscribeEvent
    public void respawn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent event){
        //PlayerHandler.playerRespawn(event.player);
        if(!event.player.getEntityWorld().isRemote) {
            for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                playerHandler.playerRespawn(event.player);
            }
        }
    }



    @SubscribeEvent(priority = EventPriority.LOW)
    public void smeltItem(net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent event){
        //PlayerHandler.playerSmelted(event, event.player);
        if(!event.player.getEntityWorld().isRemote) {
            for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                playerHandler.playerSmeltItem(event.player, event.smelting);
            }
        }
    }
    @SubscribeEvent
    public void joinedServer(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event){
        //PlayerHandler.playerJoined(event.player);
        if(!event.player.getEntityWorld().isRemote) {


            PlayerProperties playerProperties = event.player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES, null);
            if (playerProperties != null) {
                if (!playerProperties.hasBeenInitialized()) {

                    for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                        playerHandler.playerPreInitialization(event.player);
                        playerHandler.playerInitialization(event.player);
                    }
                    playerProperties.setHasBeenInitialized(true);
                    playerProperties.saveNBTData(event.player.getEntityData());
                    for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                        playerHandler.playerPostInitialization(event.player);
                    }
                }
            }
            for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                playerHandler.playerJoined(event.player);
            }
        }
    }
    @SubscribeEvent
    public void livingDied(LivingDeathEvent event){
        if(!event.getEntity().getEntityWorld().isRemote) {
            for (PlayerHandlerBase playerHandler : PLAYER_HANDLERS) {
                if (event.getEntity() instanceof EntityPlayer) {
                    playerHandler.playerDied((EntityPlayer) event.getEntity());
                }
            }
            for (LivingHandlerBase livingHandler : LIVING_HANDLERS) {
                if (event.getEntity() instanceof EntityCreature) {
                    livingHandler.livingDied((EntityLivingBase) event.getEntity());
                }
            }
        }
    }
    @SubscribeEvent
    public void worldTick(TickEvent.WorldTickEvent event){
        if(event.phase == TickEvent.Phase.END && !event.world.isRemote){
            for(WorldHandlerBase worldHandler: WORLD_HANDLERS){
                worldHandler.worldTick(event.world);
            }
        }
    }
    @SubscribeEvent
    public void playerCaughtFish(ItemFishedEvent event){
        for(PlayerHandlerBase playerHandler: PLAYER_HANDLERS){
            playerHandler.playerCaughtFish(event.getEntityPlayer(),event.getDrops());
        }
    }
    @SubscribeEvent
    public void playerKilledMob(LivingDeathEvent event){
        if(event.getSource().getTrueSource() instanceof EntityPlayer){
            for(PlayerHandlerBase playerHandler: PLAYER_HANDLERS){
                playerHandler.playerKilledLiving((EntityPlayer)event.getSource().getTrueSource(),event.getEntity());
            }
        }
    }
}
