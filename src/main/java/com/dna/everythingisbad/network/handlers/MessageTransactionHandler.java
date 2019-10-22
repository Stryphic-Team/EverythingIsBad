package com.dna.everythingisbad.network.handlers;

import com.dna.everythingisbad.entityproperties.InitializedPlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.network.messagestypes.MessageTransaction;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageTransactionHandler implements IMessageHandler<MessageTransaction, IMessage> {
    @Override
    public IMessage onMessage(MessageTransaction message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().player;
        if(player != null){
            PlayerProperties playerProperties = player.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);
            if(playerProperties != null){
                if(message.getTransactionType() == MessageTransaction.TransactionType.WITHDRAW){
                    if(playerProperties.getBankBalance() >= message.getAmount()){
                        int currentBankBalance = playerProperties.getBankBalance();
                        int currentBalance = playerProperties.getBalance();
                        playerProperties.setBankBalance(currentBankBalance - message.getAmount());
                        playerProperties.setBalance(currentBalance + message.getAmount());
                    }
                }else if(message.getTransactionType() == MessageTransaction.TransactionType.DEPOSIT){
                    if(playerProperties.getBalance() >= message.getAmount()){
                        int currentBankBalance = playerProperties.getBankBalance();
                        int currentBalance = playerProperties.getBalance();
                        playerProperties.setBankBalance(currentBankBalance + message.getAmount());
                        playerProperties.setBalance(currentBalance - message.getAmount());
                    }
                }
            }
        }
        return null;
    }
}
