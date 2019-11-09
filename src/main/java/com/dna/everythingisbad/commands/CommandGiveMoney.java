package com.dna.everythingisbad.commands;

import com.dna.everythingisbad.commands.utils.CommandOutputHelper;
import com.dna.everythingisbad.commands.utils.InsufficientFundsException;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerPropertiesCapability;
import com.dna.everythingisbad.utils.helpers.FormatHelper;
import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class CommandGiveMoney extends ModCommandBase {
    public CommandGiveMoney(String name) {
        super(name);
        addAlias("givemoney");
    }

    @Override
    public String getDocumentation() {
        return "Gives money to a specified player";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/everythingisbad money <username> <amount>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender iCommandSender, String[] args) throws CommandException {
        if(iCommandSender.getCommandSenderEntity() instanceof EntityPlayer){
            EntityPlayer sender = (EntityPlayer)iCommandSender;
            if(args.length == 2){

                String username = args[0];
                String amountString = args[1];
                try {
                    if (Float.parseFloat(amountString) > 0) {
                        EntityPlayer receiver = server.getPlayerList().getPlayerByUsername(username);
                        if(receiver != null){
                            PlayerProperties receiverProperties = receiver.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
                            PlayerProperties senderProperties = sender.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
                            if(receiverProperties != null && senderProperties != null){
                                float amount = Float.parseFloat(amountString);
                                if(senderProperties.getBalance() >= amount){
                                    senderProperties.setBalance(senderProperties.getBalance() - amount);
                                    receiverProperties.setBalance(receiverProperties.getBalance() + amount);
                                    CommandOutputHelper.sendPositiveMessage(iCommandSender,sender,"You have sent " + FormatHelper.formatMoney(amount) + " to " + receiver.getDisplayNameString());
                                    CommandOutputHelper.sendPositiveMessage(iCommandSender,receiver,"You have received " + FormatHelper.formatMoney(amount) + " from " + sender.getDisplayNameString());
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
        ArrayList<String> completions = new ArrayList<>();
        if(args.length == 1) {
            for (String username : server.getPlayerList().getOnlinePlayerNames()) {
                if (username.startsWith(args[0])){
                    completions.add(username);
                }
            }
            //return server.getPlayerList().getCurrentPlayerCount() > 0 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
        }else if(args.length == 2){
            for(int i = 0;i<20 ;i++){
                completions.add(Integer.toString(i * 5));
            }
            //return completions;
        }
        return completions;
    }

}
