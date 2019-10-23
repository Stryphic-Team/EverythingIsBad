package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.gui.container.AutomatedTellerMachineContainer;
import com.dna.everythingisbad.gui.elements.*;
import com.dna.everythingisbad.network.PacketHandler;
import com.dna.everythingisbad.network.messagestypes.MessageTransaction;
import com.dna.everythingisbad.tile.TileDeviceBase;
import com.dna.everythingisbad.utils.helpers.FormatHelper;
import net.minecraft.entity.player.InventoryPlayer;

public class AutomatedTellerMachineGuiContainer extends DeviceContainerGuiBase{
    private ElementButton withdrawButton;
    private ElementButton depositButton;
    private ElementButton backButton;
    private ElementTextField textFieldWithdraw;
    private ElementTextField textFieldDeposit;
    private ElementButton submitButton;
    public static GuiState currentState = GuiState.STATE_MAIN;
    public static int currentBalance = ATMSync.balance;
    public static int bankBalance = ATMSync.bankBalance;
    private ElementButton balanceButton;
    private ElementText bankBalanceText;
    private ElementText balanceText;
    private ElementText withdrawTitleText;
    private ElementText depositTitleText;


    public AutomatedTellerMachineGuiContainer(InventoryPlayer player, TileDeviceBase tileentity) {
        super(new AutomatedTellerMachineContainer(player,tileentity), player, tileentity);
        currentState = GuiState.STATE_MAIN;
        PacketHandler.INSTANCE.sendToServer(new MessageTransaction(0, MessageTransaction.TransactionType.BALANCE));
    }

    @Override
    public void init() {

        guiElements.add(depositButton = new ElementButton(this,(width / 2) - guiLeft - 50,10,"Deposit"));
        guiElements.add(withdrawButton = new ElementButton(this,(width / 2) - guiLeft - 50,30,"Withdraw"));
        guiElements.add(balanceButton = new ElementButton(this,(width / 2) - guiLeft - 50,50,"Balance"));
        guiElements.add(backButton = new ElementButton(this,4,14,"Back"));
        guiElements.add(textFieldWithdraw = new ElementNumberInput(this,(width / 2) - guiLeft - 40,14));
        guiElements.add(withdrawTitleText = new ElementText(this,50,4,"Withdraw"));
        guiElements.add(textFieldDeposit = new ElementNumberInput(this,(width / 2) - guiLeft - 40,14));
        guiElements.add(depositTitleText = new ElementText(this,50,4,"Deposit"));
        guiElements.add(submitButton = new ElementButton(this,(width / 2) - guiLeft + 65,14,">"));
        guiElements.add(balanceText = new ElementText(this,10,40,"Balance: $" + currentBalance));
        guiElements.add(bankBalanceText = new ElementText(this,10,50,"BankBalance: $" + bankBalance));
        backButton.guiButton.width = 40;
        submitButton.guiButton.width = 20;

    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        for(ElementBase element:guiElements){
            element.setVisible(false);
        }
        switch (currentState){
            case STATE_MAIN:
                withdrawButton.setVisible(true);
                depositButton.setVisible(true);
                balanceButton.setVisible(true);
                break;
            case STATE_WITHDRAW:
                withdrawTitleText.setVisible(true);
                textFieldWithdraw.setVisible(true);
                backButton.setVisible(true);
                submitButton.setVisible(true);
                break;
            case STATE_DEPOSIT:
                depositTitleText.setVisible(true);
                textFieldDeposit.setVisible(true);
                backButton.setVisible(true);
                submitButton.setVisible(true);
                break;
            case STATE_BALANCE:
                bankBalanceText.setVisible(true);
                balanceText.setVisible(true);
                backButton.setVisible(true);
                balanceText.setText("Balance: $"+ FormatHelper.formatNumber(currentBalance));
                bankBalanceText.setText("Bank Balance: $" + FormatHelper.formatNumber(bankBalance));
                break;
        }
    }


    @Override
    public void buttonPressed(ElementButton elementButton) {
        super.buttonPressed(elementButton);
        if(elementButton == withdrawButton){
            currentState = GuiState.STATE_WITHDRAW;

        }
        else if(elementButton == depositButton){
            currentState = GuiState.STATE_DEPOSIT;
        }
        else if(elementButton == backButton){
            currentState = GuiState.STATE_MAIN;
        }
        else if(elementButton == submitButton){
            if(currentState == GuiState.STATE_DEPOSIT){
                PacketHandler.INSTANCE.sendToServer(new MessageTransaction(Integer.parseInt(textFieldDeposit.textField.getText()), MessageTransaction.TransactionType.DEPOSIT));
            }else if(currentState == GuiState.STATE_WITHDRAW){
                PacketHandler.INSTANCE.sendToServer(new MessageTransaction(Integer.parseInt(textFieldWithdraw.textField.getText()), MessageTransaction.TransactionType.WITHDRAW));
            }
        }else if(elementButton == balanceButton){
            currentState = GuiState.STATE_BALANCE;
            PacketHandler.INSTANCE.sendToServer(new MessageTransaction(0, MessageTransaction.TransactionType.BALANCE));
        }
    }

    enum GuiState{
        STATE_MAIN,
        STATE_DEPOSIT,
        STATE_WITHDRAW,
        STATE_BALANCE
    }
}
