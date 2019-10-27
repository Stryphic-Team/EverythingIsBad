package com.dna.everythingisbad.commands;

import com.dna.everythingisbad.commands.utils.CommandOutputHelper;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandGetBalance extends ModCommandBase {
    public CommandGetBalance(String name) {
        super(name);

    }

    @Override
    public String getDocumentation() {
        return "Retrieves your current balance.";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/everthingisbad balance";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(sender.getCommandSenderEntity() instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();
            CommandOutputHelper.sendPlayerBalance(player);
        }
    }

}
