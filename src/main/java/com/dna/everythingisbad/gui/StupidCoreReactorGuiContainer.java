package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.gui.container.StupidCoreReactorContainer;
import com.dna.everythingisbad.gui.elements.ElementEnergyGauge;
import com.dna.everythingisbad.gui.elements.ElementInputSlot;
import com.dna.everythingisbad.gui.elements.ElementProgressBar;
import com.dna.everythingisbad.gui.elements.ElementStatistics;
import com.dna.everythingisbad.tile.generators.TileStupidCoreReactor;
import net.minecraft.entity.player.InventoryPlayer;

public class StupidCoreReactorGuiContainer extends DeviceContainerGuiBase {
    public StupidCoreReactorGuiContainer(InventoryPlayer player, TileStupidCoreReactor tileentity) {
        super(
                new StupidCoreReactorContainer(player, tileentity),
                player,
                tileentity
        );
    }

    @Override
    public void init() {

        guiElements.add(new ElementEnergyGauge(this,157,5));
        guiElements.add(new ElementInputSlot(this,57,35));
        guiElements.add(new ElementProgressBar(this,80,35));
        guiElements.add(new ElementStatistics(this,-80,15));
    }
}
