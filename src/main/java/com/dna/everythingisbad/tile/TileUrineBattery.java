package com.dna.everythingisbad.tile;

import com.dna.everythingisbad.capabilities.ModEnergyHandler;

public class TileUrineBattery extends TileGeneratorBase {
    public TileUrineBattery() {
        super("urine_battery");
        this.energyHandler = new ModEnergyHandler(100000,10,0,false,true);
        energyHandler.setEnergyStorage(energyHandler.getMaxEnergyStored());
    }
}
