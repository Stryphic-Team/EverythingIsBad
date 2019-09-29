package com.dna.everythingisbad.tile.generators;

import com.dna.everythingisbad.tile.TileGeneratorBase;


public class TileFluxTest extends TileGeneratorBase {
    public TileFluxTest(){
        super("flux_test");
        energyHandler.setMaxOutput(1);
        setTotalEnergyProduced(10000);
        setFinishedProgress(10);
    }

    @Override
    public boolean hasFuel() {
        return true;
    }

    @Override
    public void consumeFuel() {

    }
}
