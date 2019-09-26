package com.dna.everythingisbad.commands;

import com.dna.everythingisbad.commands.utils.CommandOutputHelper;
import com.dna.everythingisbad.entityproperties.InitializedPlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandGiveMoney extends ModCommandBase {
    public CommandGiveMoney(String name) {
        super(name);
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "Gets your current balance";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(sender.getCommandSenderEntity() instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();
            PlayerProperties playerProperties = player.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);
            if(playerProperties != null) {
                int currentBalance = playerProperties.getBalance();
                playerProperties.setBalance(currentBalance + 1000);
                playerProperties.saveNBTData(player.getEntityData());
                CommandOutputHelper.sendPlayerBalance(player);
            }
        }
    }
}
