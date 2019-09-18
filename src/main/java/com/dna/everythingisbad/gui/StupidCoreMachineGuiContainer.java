package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.gui.container.StupidCoreMachineContainer;
import com.dna.everythingisbad.gui.elements.ElementEnergyGauge;
import com.dna.everythingisbad.gui.elements.ElementOutputSlot;
import com.dna.everythingisbad.gui.elements.ElementProgressBar;
import com.dna.everythingisbad.tile.TileStupidCoreMachine;
import net.minecraft.entity.player.InventoryPlayer;

public class StupidCoreMachineGuiContainer extends DeviceContainerGuiBase {

    public StupidCoreMachineGuiContainer(InventoryPlayer player, TileStupidCoreMachine tileentity) {
        super(
                new StupidCoreMachineContainer(player, tileentity),
                player,
                tileentity
        );
    }


    @Override
    public void init() {
        guiElements.add(new ElementEnergyGauge(this,157,5));
        guiElements.add(new ElementProgressBar(this,60,35));
        guiElements.add(new ElementOutputSlot(this,94,35));
    }
}
