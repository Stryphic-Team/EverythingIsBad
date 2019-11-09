package com.dna.everythingisbad.commands;

import com.dna.everythingisbad.commands.utils.CommandOutputHelper;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandTopBalance extends ModCommandBase {
    public CommandTopBalance(String name) {
        super(name);
        addAlias("topbal");
        addAlias("baltop");
    }

    @Override
    public String getDocumentation() {
        return "Gets a list of all the balances listed from greatest to least";
    }

    @Override
    public String getUsage(ICommandSender iCommandSender) {
        return "/everythingisbad topbalance";
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] strings) throws CommandException {

        CommandOutputHelper.sendTopBalance(iCommandSender,(EntityPlayer) iCommandSender, (strings.length > 0 ? Integer.parseInt(strings[0]) : 1) - 1);

    }

    @Override
    public boolean checkPermission(MinecraftServer p_checkPermission_1_, ICommandSender p_checkPermission_2_) {
        return true;
    }

}
