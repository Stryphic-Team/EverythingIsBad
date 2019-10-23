package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.damagesource.ModDamageSources;
import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModPotions;
import com.dna.everythingisbad.network.PacketHandler;
import com.dna.everythingisbad.network.messagestypes.MessageHeartRateSync;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.Mod;

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
            entitydata.setFloat("target_heart_rate",70f);
            player.writeEntityToNBT(entitydata);
        }
    }

    @Override
    public void playerRespawn(EntityPlayer player) {
        super.playerRespawn(player);
        // Resets the player's heart rate upon respawn back to 70
        NBTTagCompound entitydata = player.getEntityData();
        entitydata.setFloat("heart_rate",70f);
        entitydata.setFloat("target_heart_rate",70f);
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

        // Updating the target heart rate once every two seconds
        if (player.ticksExisted % 40 == 39){
            float target;
            // If the player is sprinting
            if (player.isSprinting()) {
                target = 150f;
            // If the player is in water, swimming
            }else if (player.isInWater()){
                target = 140f;
            // If the player is standing still and not jumping, not falling, not walking
            }else if (player.motionX < 0.0001d && player.motionZ < 0.0001d && player.motionY < 0.0001d){
                target = 70f;
            // If the player is walking or jumping
            }else{
                target = 105f;
            }
            // If you have ingested devil's cabbage
            if (player.isPotionActive(ModPotions.POTION_HIGHNESS.getPotion())){
                target = target - 20;
            }
            // If you have ingested angel dust
            if (player.isPotionActive(ModPotions.POTION_ADRENALINE.getPotion())){
                target = target + 80;
            }
            // If you have ingested ciggys and cigars
            if (player.isPotionActive(ModPotions.POTION_STIMULATION.getPotion())){
                target = target + 40;
            }

            NBTTagCompound entitydata = player.getEntityData();

            // Currently drug_sum is disabled because it wouldn't work with any potions
            //float drugSum = entitydata.getFloat("drug_sum");
            entitydata.setFloat("target_heart_rate",target);
            player.writeEntityToNBT(entitydata);
        }

        // Updating the heart rate once per second
        if (player.ticksExisted % 20 == 19){
            float target = player.getEntityData().getFloat("target_heart_rate");
            float oldrate = player.getEntityData().getFloat("heart_rate");
            float newrate;
            float dist = target-oldrate;
            newrate = (float)( (float)oldrate + ((1f/40f) * (float)dist));

            NBTTagCompound entitydata = player.getEntityData();
            entitydata.setFloat("heart_rate",newrate);
            player.writeEntityToNBT(entitydata);

            if (newrate >= 150){
                // Heart attack chance per second = 1 / 10000/(((rate/10)-14)^3)
                // 150bpm: 1/10000
                // 160bpm: 1/1250
                // 170bpm: 1/370
                // 180bpm: 1/156
                // 190bpm: 1/80
                // 200bpm: 1/46
                // 210bpm: 1/29
                // 220bpm: 1/19
                // 230bpm: 1/13
                float heartAttackChance;
                float heartAttackDenom = (newrate/10f)-14f;
                heartAttackChance = 10000f/(float)(Math.pow(heartAttackDenom,3));

                if (random.nextInt((int)heartAttackChance) == 1){
                    player.attackEntityFrom(ModDamageSources.HEART_ATTACK,player.getHealth() + 20f);
                }
            }
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
