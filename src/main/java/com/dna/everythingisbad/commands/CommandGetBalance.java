package com.dna.everythingisbad.commands;

import com.dna.everythingisbad.commands.utils.CommandOutputHelper;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandGetBalance extends ModCommandBase {
    public CommandGetBalance(String name) {
        super(name);
    }
    @Override
    public String getUsage(ICommandSender sender) {
        return "Gets your cash money number son!";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(sender.getCommandSenderEntity() instanceof EntityPlayerMP){
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) sender.getCommandSenderEntity();
            CommandOutputHelper.sendPlayerBalance(entityPlayerMP);
        }
    }
}
