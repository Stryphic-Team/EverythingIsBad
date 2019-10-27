package com.dna.everythingisbad.commands;

import com.dna.everythingisbad.commands.utils.CommandOutputHelper;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandGetStatistics extends ModCommandBase {
    public CommandGetStatistics(String name) {
        super(name);

    }

    @Override
    public String getDocumentation() {
        return "Retrieves your current statistics.";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/everythingisbad statistics";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(sender.getCommandSenderEntity() instanceof EntityPlayer) {
            CommandOutputHelper.sendBorder((EntityPlayer) sender.getCommandSenderEntity());
            CommandOutputHelper.sendPlayerReligion((EntityPlayer) sender.getCommandSenderEntity());
            CommandOutputHelper.sendPlayerBalance((EntityPlayer) sender.getCommandSenderEntity());
            CommandOutputHelper.sendBankBalance((EntityPlayer) sender.getCommandSenderEntity());
            CommandOutputHelper.sendPlayerBlindness((EntityPlayer) sender.getCommandSenderEntity());
            CommandOutputHelper.sendPlayerCommonColdImmunity((EntityPlayer) sender.getCommandSenderEntity());
            CommandOutputHelper.sendPlayerTimesPooped((EntityPlayer) sender.getCommandSenderEntity());
            CommandOutputHelper.sendAddictionLevel((EntityPlayer) sender.getCommandSenderEntity());
            CommandOutputHelper.sendBorder((EntityPlayer) sender.getCommandSenderEntity());
        }
    }
}
