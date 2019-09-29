package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.gui.container.DryerMachineContainer;
import com.dna.everythingisbad.gui.elements.*;
import com.dna.everythingisbad.tile.processing.TileDryerMachine;
import net.minecraft.entity.player.InventoryPlayer;

public class DryerMachineGuiContainer extends DeviceContainerGuiBase {

    public DryerMachineGuiContainer(InventoryPlayer player, TileDryerMachine tileentity) {
        super(
                new DryerMachineContainer(player,tileentity),
                player,
                tileentity
        );
    }
    @Override
    public void init() {
        guiElements.add(new ElementEnergyGauge(this,157,5));
        guiElements.add(new ElementFluidTank(this,145,5));
        guiElements.add(new ElementOutputSlot(this,116,35));
        guiElements.add(new ElementInputSlot(this,57,35));
        guiElements.add(new ElementProgressBar(this,80,35));
        guiElements.add(new ElementStatistics(this,-80,15));
    }

}
