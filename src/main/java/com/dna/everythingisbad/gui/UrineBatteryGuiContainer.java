package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.gui.container.UrineBatteryContainer;
import com.dna.everythingisbad.gui.elements.ElementEnergyGauge;
import com.dna.everythingisbad.gui.elements.ElementStatistics;
import com.dna.everythingisbad.tile.TileDeviceBase;
import net.minecraft.entity.player.InventoryPlayer;

public class UrineBatteryGuiContainer extends DeviceContainerGuiBase{
    public UrineBatteryGuiContainer(InventoryPlayer player, TileDeviceBase tileentity) {
        super(new UrineBatteryContainer(player,tileentity), player, tileentity);
    }

    @Override
    public void init() {
        guiElements.add(new ElementEnergyGauge(this,(xSize/2)-6,5));
        guiElements.add(new ElementStatistics(this,-80,15));
    }
}
