package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.gui.container.DiaricGeneratorContainer;
import com.dna.everythingisbad.gui.elements.ElementEnergyGauge;
import com.dna.everythingisbad.gui.elements.ElementFluidTank;
import com.dna.everythingisbad.gui.elements.ElementProgressBar;
import com.dna.everythingisbad.gui.elements.ElementStatistics;
import com.dna.everythingisbad.tile.generators.TileDiaricGenerator;
import net.minecraft.entity.player.InventoryPlayer;

public class DiaricGeneratorGuiContainer extends DeviceContainerGuiBase {

    public DiaricGeneratorGuiContainer(InventoryPlayer player, TileDiaricGenerator tileentity) {
        super(
                new DiaricGeneratorContainer(player,tileentity),
                player,
                tileentity
        );

    }

    @Override
    public void init() {
        guiElements.add(new ElementEnergyGauge(this,157,5));
        guiElements.add(new ElementFluidTank(this,145,5));
        guiElements.add(new ElementStatistics(this,-80,15));
        guiElements.add(new ElementProgressBar(this,60,35));
    }

}
