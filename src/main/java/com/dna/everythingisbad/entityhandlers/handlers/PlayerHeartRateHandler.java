package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.network.PacketHandler;
import com.dna.everythingisbad.network.messagestypes.MessageHeartRateSync;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
        if (entitydata.getFloat("heart_rate") == 0f){
            entitydata.setFloat("heart_rate",70f);
            player.writeEntityToNBT(entitydata);
        }
        // Ditto with the target heart rate
        if (entitydata.getFloat("target_heart_rate") == 0f){
            entitydata.setFloat("target_heart_rate",140f);
            player.writeEntityToNBT(entitydata);
        }
    }

    @Override
    public void playerRespawn(EntityPlayer player) {
        super.playerRespawn(player);
        // Resets the player's heart rate upon respawn back to 70
        NBTTagCompound entitydata = player.getEntityData();
        entitydata.setFloat("heart_rate",70f);
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
        // Updating the heart rate once per second
        if (player.ticksExisted % 20 == 19){
            float target = player.getEntityData().getFloat("target_heart_rate");
            float oldrate = player.getEntityData().getFloat("heart_rate");
            float newrate;
            float dist = target-oldrate;
            newrate = (float)( (float)oldrate + ((1f/45f) * (float)dist));

            NBTTagCompound entitydata = player.getEntityData();
            entitydata.setFloat("heart_rate",newrate);
            player.writeEntityToNBT(entitydata);
        }

        // Giving the PlayerSP and the heart rate monitor item the PlayerMP's heart rate
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

                float heartRate = tagz.getFloat("heart_rate");
                itemtagz.setFloat("item_heart_rate", heartRate);
                stack.setTagCompound(itemtagz);
            }
        }

        float heartRateAndShit = player.getEntityData().getFloat("heart_rate");
        EntityPlayerMP mp = (EntityPlayerMP)player;
        PacketHandler.INSTANCE.sendTo(new MessageHeartRateSync(heartRateAndShit), mp);
    }

    @Override
    public void clientPlayerTick(EntityPlayer player) {
        super.clientPlayerTick(player);
    }
}
