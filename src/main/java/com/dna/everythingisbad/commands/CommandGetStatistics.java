package com.dna.everythingisbad.commands;

import com.dna.everythingisbad.commands.utils.CommandOutputHelper;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandGetStatistics extends ModCommandBase {
    public CommandGetStatistics(String name) {
        super(name);
        addAlias("getstats");
        addAlias("stats");
        addAlias("statistics");
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "Gets the current status of your player";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(sender.getCommandSenderEntity() instanceof EntityPlayer) {
            CommandOutputHelper.sendBorder((EntityPlayer) sender.getCommandSenderEntity());
            CommandOutputHelper.sendPlayerReligion((EntityPlayer) sender.getCommandSenderEntity());
            CommandOutputHelper.sendPlayerBalance((EntityPlayer) sender.getCommandSenderEntity());
            CommandOutputHelper.sendPlayerBlindness((EntityPlayer) sender.getCommandSenderEntity());
            CommandOutputHelper.sendPlayerCommonColdImmunity((EntityPlayer) sender.getCommandSenderEntity());
            CommandOutputHelper.sendPlayerTimesPooped((EntityPlayer) sender.getCommandSenderEntity());
            CommandOutputHelper.sendBorder((EntityPlayer) sender.getCommandSenderEntity());
        }
    }
}
