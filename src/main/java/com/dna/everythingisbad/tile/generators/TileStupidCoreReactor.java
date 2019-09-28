package com.dna.everythingisbad.tile.generators;

import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.tile.TileGeneratorBase;

public class TileStupidCoreReactor extends TileGeneratorBase {
    public TileStupidCoreReactor() {
        super("stupid_core_reactor");
        //that's 100,000,000
        setTotalEnergyProduced(10 ^ 8);
        setFinishedProgress(1000);
        itemStackHadler.setSlotConfig(0,true,true);
    }

    @Override
    public boolean hasFuel() {
        return itemStackHadler.getStackInSlot(0).getItem() == ModItems.STUPID_CORE_ITEM;
    }

    @Override
    public void consumeFuel() {
        itemStackHadler.extractItem(0,1,false);
    }
}
