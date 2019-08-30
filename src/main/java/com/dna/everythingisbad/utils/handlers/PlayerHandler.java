package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModFluids;
import com.dna.everythingisbad.utils.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
    public static void playerTick(EntityPlayer player){
        World worldIn = player.getEntityWorld();

        BlockPos player_pos = player.getPosition();
        int player_x = player_pos.getX();
        int player_y = player_pos.getY();
        int player_z = player_pos.getZ();

        BlockPos pos_plus_2 = new BlockPos(player_x,player_y+2,player_z);
        Block blockabove = worldIn.getBlockState(pos_plus_2).getBlock();

        if (blockabove == ModBlocks.QUESTION_MARK_BLOCK && player.motionY > 0){
            Main.logger.info("Under question mark block");
            worldIn.setBlockToAir(pos_plus_2);
            worldIn.setBlockState(pos_plus_2,ModBlocks.EMPTY_BLOCK.getBlockState().getBaseState())
;        }
    }
}
