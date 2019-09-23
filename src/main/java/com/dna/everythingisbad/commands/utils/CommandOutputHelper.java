package com.dna.everythingisbad.commands.utils;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;

public class CommandOutputHelper {
    public static void sendPlayerBalance(EntityPlayerMP entityPlayerMP){
        NBTTagCompound nbtTagCompound = entityPlayerMP.getEntityData();
        int currentBalance = nbtTagCompound.getInteger("balance");
        entityPlayerMP.sendMessage(new TextComponentString("Your Balance: $"+String.valueOf(currentBalance)));
    }
}
