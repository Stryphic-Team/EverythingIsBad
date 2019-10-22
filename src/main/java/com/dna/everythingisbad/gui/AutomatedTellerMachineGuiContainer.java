package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.gui.container.AutomatedTellerMachineContainer;
import com.dna.everythingisbad.gui.elements.ElementBase;
import com.dna.everythingisbad.gui.elements.ElementButton;
import com.dna.everythingisbad.gui.elements.ElementNumberInput;
import com.dna.everythingisbad.gui.elements.ElementTextField;
import com.dna.everythingisbad.network.PacketHandler;
import com.dna.everythingisbad.network.messagestypes.MessageTransaction;
import com.dna.everythingisbad.tile.TileDeviceBase;
import net.minecraft.entity.player.InventoryPlayer;

public class AutomatedTellerMachineGuiContainer extends DeviceContainerGuiBase{
    public ElementButton withdrawButton;
    public ElementButton depositButton;
    public ElementButton backButton;
    public ElementTextField textFieldWithdraw;
    public ElementTextField textFieldDeposit;
    public GuiState currentState = GuiState.STATE_MAIN;
    public ElementButton submitButton;


    public AutomatedTellerMachineGuiContainer(InventoryPlayer player, TileDeviceBase tileentity) {
        super(new AutomatedTellerMachineContainer(player,tileentity), player, tileentity);
    }

    @Override
    public void init() {

        guiElements.add(depositButton = new ElementButton(this,(width / 2) - guiLeft - 50,10,"Deposit"));
        guiElements.add(withdrawButton = new ElementButton(this,(width / 2) - guiLeft - 50,40,"Withdraw"));
        guiElements.add(backButton = new ElementButton(this,4,14,"Back"));
        guiElements.add(textFieldWithdraw = new ElementNumberInput(this,(width / 2) - guiLeft - 40,14));
        guiElements.add(textFieldDeposit = new ElementNumberInput(this,(width / 2) - guiLeft - 40,14));
        guiElements.add(submitButton = new ElementButton(this,(width / 2) - guiLeft + 65,14,">"));
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
                break;
            case STATE_WITHDRAW:
                textFieldWithdraw.setVisible(true);
                backButton.setVisible(true);
                submitButton.setVisible(true);
                break;
            case STATE_DEPOSIT:
                textFieldDeposit.setVisible(true);
                backButton.setVisible(true);
                submitButton.setVisible(true);
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
        }
    }

    enum GuiState{
        STATE_MAIN,
        STATE_DEPOSIT,
        STATE_WITHDRAW
    }
}
