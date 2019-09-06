package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModFluids;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class PlayerHandler {

    public static void playerDied(EntityPlayer player){
        if(ModConfig.BLOOD_SPAWNS_ON_DEATH) {
            player.getEntityWorld().setBlockState(
                    new BlockPos(player.posX, player.posY, player.posZ),
                    ModFluids.BLOOD.getBlockFluidBase().getDefaultState()
            );
        }
        //PotionEffectHandler.potionEffectFirstTimes.put(player,false);
    }
    public static void playerRespawn(EntityPlayer player){
        // sets to 0 when the player is not high
        player.getEntityData().setInteger("highness_duration",0);
        player.writeEntityToNBT(player.getEntityData());

        ItemStack soulstack = new ItemStack(ModItems.SOUL_ITEM,1,0);
        NBTTagCompound hingydingy = new NBTTagCompound();
        hingydingy.setString("player_name",player.getDisplayNameString());
        soulstack.setTagCompound(hingydingy);
        //Main.logger.info(hingydingy) ;
        //soulstack.getTagCompound().setString("player_name",player.getDisplayNameString());
        player.dropItem(soulstack,false);
    }
    public static void playerJoined(EntityPlayer entityPlayer) {
        //entity player needs to be casted to its respective type when writing nbt data
        if(entityPlayer instanceof EntityPlayerMP){
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) entityPlayer;
            int current_value = entityPlayer.getEntityData().getInteger("highness_duration");
            //Main.logger.info("Current Highness Duration Value: "+current_value);
        }else if(entityPlayer instanceof EntityPlayerSP){
            EntityPlayerSP entityPlayerSP = (EntityPlayerSP) entityPlayer;
            int current_value = entityPlayer.getEntityData().getInteger("highness_duration");
            //Main.logger.info("Current Highness Duration Value: "+current_value);
        }


    }
    public static void playerPooped(EntityPlayer entityPlayer, int amount) {

        if(entityPlayer instanceof EntityPlayerMP){
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) entityPlayer;
            int current = entityPlayerMP.getEntityData().getInteger("times_pooped");
            entityPlayerMP.getEntityData().setInteger("times_pooped",current + amount);
            entityPlayerMP.writeEntityToNBT(entityPlayerMP.getEntityData());
        }else{
            int current = entityPlayer.getEntityData().getInteger("times_pooped");
            entityPlayer.getEntityData().setInteger("times_pooped",current + amount);
            entityPlayer.writeEntityToNBT(entityPlayer.getEntityData());

        }

    }
    public static void playerTick(EntityPlayer player){
        Random rand = new Random();
        World worldIn = player.getEntityWorld();

        BlockPos player_pos = player.getPosition();
        int player_x = player_pos.getX();
        int player_y = player_pos.getY();
        int player_z = player_pos.getZ();

        BlockPos pos_plus_2 = new BlockPos(player_x,player_y+2,player_z);
        Block blockabove = worldIn.getBlockState(pos_plus_2).getBlock();

        if (blockabove == ModBlocks.QUESTION_MARK_BLOCK && player.motionY > 0){
            //Main.logger.info("Under question mark block");
            worldIn.setBlockToAir(pos_plus_2);
            worldIn.setBlockState(pos_plus_2,ModBlocks.EMPTY_BLOCK.getBlockState().getBaseState());
            ItemStack itemstack;

            if (rand.nextInt(101) > 98){
                itemstack = new ItemStack(ModItems.STUPID_CORE_ITEM,1);
                player.dropItem(itemstack, false);
            }else if (rand.nextInt(101) > 90){
                itemstack = new ItemStack(Items.GOLD_INGOT,1);
                player.dropItem(itemstack, false);
            }else if (rand.nextInt(101) > 50){
                if (rand.nextBoolean() == true){
                    itemstack = new ItemStack(Blocks.RED_MUSHROOM,1);
                    player.dropItem(itemstack, false);
                }else{
                    itemstack = new ItemStack(Blocks.BROWN_MUSHROOM,1);
                    player.dropItem(itemstack, false);
                }
            }else{
                itemstack = new ItemStack(Items.GOLD_NUGGET,1);
                player.dropItem(itemstack, false);
            }
;       }
    }
}
