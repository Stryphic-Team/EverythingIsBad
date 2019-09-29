package com.dna.everythingisbad.tile.storage;

import com.dna.everythingisbad.tile.TileGeneratorBase;
import com.dna.everythingisbad.tile.utils.handlers.ModEnergyHandler;

public class TileUrineBattery extends TileGeneratorBase {
    public TileUrineBattery() {
        super("urine_battery");
        this.energyHandler = new ModEnergyHandler(100000,69,0,false,true);
        energyHandler.setEnergyStorage(energyHandler.getMaxEnergyStored());
        //displayName = ModBlocks.URINE_BATTERY.getLocalizedName();
    }

    @Override
    public boolean hasFuel() {
        return false;
    }

    @Override
    public void consumeFuel() {

    }
}
