package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.LivingHandlerBase;
import com.dna.everythingisbad.entityproperties.InitializedPlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.ModConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.WorldServer;

public class LivingPoopHandler extends LivingHandlerBase {
    @Override
    public void livingTick(EntityLivingBase livingBase) {
        super.livingTick(livingBase);
        if(livingBase instanceof EntityPlayer) {
            EntityPlayer entityPlayerMP = (EntityPlayer) livingBase;
            int intervalAlter = random.nextInt(500);
            if (entityPlayerMP.ticksExisted % Math.abs(ModConfig.AUTO_POOP_INTERVAL + intervalAlter) == 0) {

                int random_amount = random.nextInt(ModConfig.AUTO_POOP_MAX - 1) + 1;
                ItemStack item = new ItemStack(ModItems.POOP_ITEM, random_amount, 3);
                entityPlayerMP.inventory.addItemStackToInventory(item);
                //Increments times pooped interval
                PlayerProperties playerProperties = entityPlayerMP.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);
                if(playerProperties != null) {
                    int current = playerProperties.getTimesPooped();
                    playerProperties.setTimesPooped(current+random_amount);
                    playerProperties.saveNBTData(entityPlayerMP.getEntityData());
                }
            }
        }else if(livingBase instanceof EntityAnimal){
            EntityAnimal entityAnimal = (EntityAnimal)livingBase;
            int intervalAlter = random.nextInt(500);
            if (entityAnimal.ticksExisted % Math.abs(ModConfig.AUTO_POOP_INTERVAL + intervalAlter) == 0) {
                if(entityAnimal.getServer() != null) {
                    WorldServer worldServer = (WorldServer) entityAnimal.getServer().getEntityWorld();
                    int random_amount = random.nextInt(ModConfig.AUTO_POOP_MAX - 1) + 1;
                    ItemStack item = new ItemStack(ModItems.POOP_ITEM, random_amount, 3);
                    EntityItem entityItem = new EntityItem(worldServer);
                    entityItem.setPosition(entityAnimal.posX, entityAnimal.posY, entityAnimal.posZ);
                    entityItem.setItem(item);
                    worldServer.spawnEntity(entityItem);
                }
            }
        }
    }
}
