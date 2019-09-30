package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.gui.container.CoalGeneratorContainer;
import com.dna.everythingisbad.gui.elements.ElementEnergyGauge;
import com.dna.everythingisbad.gui.elements.ElementInputSlot;
import com.dna.everythingisbad.gui.elements.ElementProgressBar;
import com.dna.everythingisbad.gui.elements.ElementStatistics;
import com.dna.everythingisbad.tile.TileDeviceBase;
import net.minecraft.entity.player.InventoryPlayer;

public class CoalGeneratorGuiContainer extends DeviceContainerGuiBase {
    public CoalGeneratorGuiContainer(InventoryPlayer player, TileDeviceBase tileentity) {
        super(new CoalGeneratorContainer(player,tileentity), player, tileentity);
    }

    @Override
    public void init() {
        guiElements.add(new ElementEnergyGauge(this,157,5));
        guiElements.add(new ElementInputSlot(this,57,35));
        guiElements.add(new ElementProgressBar(this,80,35));
        guiElements.add(new ElementStatistics(this,-80,15));
    }
}
