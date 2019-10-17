package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

public class PlayerHeartRateHandler extends PlayerHandlerBase {
    @Override
    public void playerJoined(EntityPlayer player) {
        super.playerJoined(player);
        NBTTagCompound entitydata = player.getEntityData();
        // If player's heart rate is nonexistent then it assigns it to normal 70
        if (entitydata.getInteger("heart_rate") == 0){
            entitydata.setInteger("heart_rate",70);
            player.writeEntityToNBT(entitydata);
        }
    }

    @Override
    public void playerRespawn(EntityPlayer player) {
        super.playerRespawn(player);
        // Resets the player's heart rate upon respawn back to 70
        NBTTagCompound entitydata = player.getEntityData();
        entitydata.setInteger("heart_rate",70);
        player.writeEntityToNBT(entitydata);
    }

    // TODO
    // target heart rates:
    // standing still: 70
    // walking: 100
    // sprinting: 140

    // devils cabbage: -20 with each ingestion
    // angel dust: +50 with each ingestion

    // chance of heart attack begins around 150 (above normal sprinting heartrate)
    // and is really high by 200 (sprinting with angel dust)

    @Override
    public void playerTick(EntityPlayer player) {
        super.playerTick(player);
        InventoryPlayer deeta = player.inventory;
        for (ItemStack stack : deeta.mainInventory) {
            if (stack.getItem() == ModItems.HEART_RATE_MONITOR_ITEM) {
                NBTTagCompound tagz = player.getEntityData();
                NBTTagCompound itemtagz;

                if (stack.getTagCompound() != null){
                     itemtagz = stack.getTagCompound();
                }else{
                     itemtagz = new NBTTagCompound();
                }

                int heartRate = tagz.getInteger("heart_rate");
                itemtagz.setInteger("item_heart_rate", heartRate);
                stack.setTagCompound(itemtagz);
            }
        }
    }
}
