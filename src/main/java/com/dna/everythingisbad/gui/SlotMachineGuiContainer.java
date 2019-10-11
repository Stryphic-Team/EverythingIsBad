package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.gui.container.SlotMachineContainer;
import com.dna.everythingisbad.gui.elements.ElementSlot;
import com.dna.everythingisbad.network.PacketHandler;
import com.dna.everythingisbad.network.messagestypes.MessageRollSlotMachine;
import com.dna.everythingisbad.tile.TileDeviceBase;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;

import java.io.IOException;

public class SlotMachineGuiContainer extends DeviceContainerGuiBase {
    public SlotMachineGuiContainer(InventoryPlayer player, TileDeviceBase tileentity) {
        super(new SlotMachineContainer(player,tileentity),player, tileentity);
    }

    @Override
    public void init() {
        guiElements.add(new ElementSlot(this,57,35));
        guiElements.add(new ElementSlot(this,57+30,35));
        guiElements.add(new ElementSlot(this,57+60,35));
        guiElements.add(new ElementSlot(this,10,50));

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button.id == 0){
            MessageRollSlotMachine message = new MessageRollSlotMachine();
            message.setBlockPos(getTileEntity().getPos());
            PacketHandler.INSTANCE.sendToServer(message);
        }
        super.actionPerformed(button);
    }

    @Override
    public void initGui() {
        super.initGui();
        addButton(new GuiButton(0,guiLeft+90,guiTop+60,50,20,"Start"));
    }
}
