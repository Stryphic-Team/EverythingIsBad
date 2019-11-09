package com.dna.everythingisbad.commands;

import com.dna.everythingisbad.commands.utils.CommandOutputHelper;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CommandGetStatistics extends ModCommandBase {
    public CommandGetStatistics(String name) {
        super(name);
        addAlias("stats");
        addAlias("getstats");

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
            if(args.length == 0) {
                sendStatistics(sender, (EntityPlayer) sender.getCommandSenderEntity());
            }else{
                EntityPlayer player = server.getPlayerList().getPlayerByUsername(args[0]);
                if(player != null){
                    sendStatistics(sender, (EntityPlayer) sender.getCommandSenderEntity());
                }else{
                    throw new PlayerNotFoundException(args[0]);
                }
            }
        }
    }
    public void sendStatistics(ICommandSender sender, EntityPlayer player){
        CommandOutputHelper.sendBorder(sender,player);
        CommandOutputHelper.sendPlayerReligion(sender,player);
        CommandOutputHelper.sendStudentStatus(sender,player);
        CommandOutputHelper.sendPlayerBalance(sender,player);
        CommandOutputHelper.sendBankBalance(sender,player);
        CommandOutputHelper.sendPlayerBlindness(sender,player);
        CommandOutputHelper.sendPlayerCommonColdImmunity(sender,player);
        CommandOutputHelper.sendPlayerTimesPooped(sender,player);
        CommandOutputHelper.sendAddictionLevel(sender,player);
        CommandOutputHelper.sendBorder(sender,player);
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        ArrayList<String> completions = new ArrayList<String>();
        if(args.length == 1) {
            for (String username : server.getPlayerList().getOnlinePlayerNames()) {
                if (username.startsWith(args[0])){
                    completions.add(username);
                }
            }
        }
        return completions;
    }
}
