package com.dna.everythingisbad.commands;

import com.dna.everythingisbad.commands.utils.InsufficientFundsException;
import com.dna.everythingisbad.entityproperties.InitializedPlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class CommandGiveMoney extends ModCommandBase {
    public CommandGiveMoney(String name) {
        super(name);
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/givemoney <username> <amount>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender iCommandSender, String[] args) throws CommandException {
        if(iCommandSender.getCommandSenderEntity() instanceof EntityPlayer){
            EntityPlayer sender = (EntityPlayer)iCommandSender;
            if(args.length == 2){

                String username = args[0];
                String amountString = args[1];
                try {
                    if (Integer.parseInt(amountString) > 0) {
                        EntityPlayer receiver = server.getPlayerList().getPlayerByUsername(username);
                        if(receiver != null){
                            PlayerProperties receiverProperties = receiver.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);
                            PlayerProperties senderProperties = sender.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);
                            if(receiverProperties != null && senderProperties != null){
                                int amount = Integer.parseInt(amountString);
                                if(senderProperties.getBalance() >= amount){
                                    senderProperties.setBalance(senderProperties.getBalance() - amount);
                                    receiverProperties.setBalance(receiverProperties.getBalance() + amount);
                                }else{
                                    throw new InsufficientFundsException("commands.economy.error.insufficient_funds");
                                }
                            }
                        }else{
                            throw new EntityNotFoundException(username);
                        }
                    }else{
                        throw new NumberInvalidException(amountString);
                    }
                }catch (NumberFormatException e){
                    throw new NumberInvalidException(amountString);
                }


            }else{
                throw new WrongUsageException(getUsage(sender));
            }
        }
    }
    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        return args.length >= 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
    }
}
