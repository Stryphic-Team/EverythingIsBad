package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModFluids;
import com.dna.everythingisbad.utils.ModConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

public class PlayerHandler {

    public static void playerDied(EntityPlayer player){
        if(ModConfig.BLOOD_SPAWNS_ON_DEATH) {
            player.getEntityWorld().setBlockState(
                    new BlockPos(player.posX, player.posY, player.posZ),
                    ModFluids.BLOOD.getBlockFluidBase().getDefaultState()
            );
        }
    }
    public static void playerRespawn(EntityPlayer player){

    }
    public static void playerJoined(EntityPlayerMP player) {
        int current = player.getEntityData().getInteger("times_pooped");

        Main.logger.info(current);
    }
    public static void playerPooped(EntityPlayerMP player, int amount) {
        int current = player.getEntityData().getInteger("times_pooped");
        player.getEntityData().setInteger("times_pooped",current + amount);
        player.writeEntityToNBT(player.getEntityData());
    }
    public static void playerJoined(EntityPlayer player) {
        int current = player.getEntityData().getInteger("times_pooped");
        Main.logger.info(current);
    }
    public static void playerPooped(EntityPlayer player, int amount) {
        int current = player.getEntityData().getInteger("times_pooped");
        player.getEntityData().setInteger("times_pooped",current + amount);
        player.writeEntityToNBT(player.getEntityData());

    }
}
