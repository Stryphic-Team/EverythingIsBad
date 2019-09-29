package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.gui.container.LiquifierMachineContainer;
import com.dna.everythingisbad.gui.elements.*;
import com.dna.everythingisbad.tile.processing.TileLiquifierMachine;
import net.minecraft.entity.player.InventoryPlayer;

public class LiquifierMachineGuiContainer extends DeviceContainerGuiBase {
    public LiquifierMachineGuiContainer(InventoryPlayer player, TileLiquifierMachine tileentity) {
        super(
                new LiquifierMachineContainer(player, tileentity),
                player,
                tileentity
        );
    }
    @Override
    public void init() {
        guiElements.add(new ElementEnergyGauge(this,157,5));
        guiElements.add(new ElementFluidTank(this,145,5));
        guiElements.add(new ElementInputSlot(this,57,35));
        guiElements.add(new ElementProgressBar(this,80,35));
        guiElements.add(new ElementStatistics(this,-80,15));
    }
}
