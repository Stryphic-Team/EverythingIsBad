package com.dna.everythingisbad.commands;

import com.dna.everythingisbad.commands.utils.CommandOutputHelper;
import com.dna.everythingisbad.init.ModCommands;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CommandHelp extends ModCommandBase {

    public CommandHelp(String name) {
        super(name);
        addAlias("h");
    }

    @Override
    public String getDocumentation() {
        return "Displays a help menu";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/everythingisbad help";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(sender instanceof EntityPlayerMP){
            CommandOutputHelper.sendBorder(sender,(EntityPlayer) sender);
            for(ModCommandBase command: ModCommands.COMMANDS) {
                sender.sendMessage(
                        new TextComponentString(command.getUsage(sender)).setStyle(new Style().setColor(TextFormatting.GREEN))
                        .appendSibling(
                        new TextComponentString(" : ").setStyle(new Style().setColor(TextFormatting.DARK_GRAY))
                        )
                        .appendSibling(
                        new TextComponentString(command.getDocumentation()).setStyle(new Style().setColor(TextFormatting.GREEN))
                        ));


            }
            CommandOutputHelper.sendBorder(sender,(EntityPlayer) sender);

        }
    }
}
