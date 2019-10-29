package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.network.messagestypes.MessageTransactionStatus;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ATMSync implements IMessageHandler<MessageTransactionStatus, IMessage> {
    static float balance = 0;
    static float bankBalance = 0;
    @Override
    public IMessage onMessage(MessageTransactionStatus message, MessageContext ctx) {

        if(message.isPass()) {
            if(AutomatedTellerMachineGuiContainer.currentState == AutomatedTellerMachineGuiContainer.GuiState.STATE_DEPOSIT || AutomatedTellerMachineGuiContainer.currentState == AutomatedTellerMachineGuiContainer.GuiState.STATE_WITHDRAW) {
                AutomatedTellerMachineGuiContainer.currentState = AutomatedTellerMachineGuiContainer.GuiState.STATE_MAIN;
            }
            AutomatedTellerMachineGuiContainer.currentBalance = message.getBalance();
            AutomatedTellerMachineGuiContainer.bankBalance = message.getBankBalance();
        }
        return null;
    }
}
