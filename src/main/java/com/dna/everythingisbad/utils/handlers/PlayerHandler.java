package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.init.ModFluids;
import com.dna.everythingisbad.utils.ModConfig;
import net.minecraft.entity.player.EntityPlayer;
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
        //deadPlayers.put(player.getUniqueID().toString(),false);
    }
}
