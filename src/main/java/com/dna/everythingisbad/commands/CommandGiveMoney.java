package com.dna.everythingisbad.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandGiveMoney extends ModCommandBase {
    public CommandGiveMoney(String name) {
        super(name);
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "Gives you money b***!";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(sender.getCommandSenderEntity() instanceof EntityPlayerMP){
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) sender.getCommandSenderEntity();
            NBTTagCompound nbtTagCompound = entityPlayerMP.getEntityData();
            int currentBalance = nbtTagCompound.getInteger("balance");
            nbtTagCompound.setInteger("balance",currentBalance + 1000);
            entityPlayerMP.writeEntityToNBT(nbtTagCompound);
            entityPlayerMP.sendMessage(new TextComponentString("$"+String.valueOf(currentBalance + 1000)));
        }
    }
}
