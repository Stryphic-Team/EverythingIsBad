package com.dna.everythingisbad.tile.generators;

import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.tile.TileGeneratorBase;
import com.dna.everythingisbad.tile.utils.handlers.ModEnergyHandler;

public class TileStupidCoreReactor extends TileGeneratorBase {
    public TileStupidCoreReactor() {
        super("stupid_core_reactor");

        this.energyHandler = new ModEnergyHandler((int)Math.pow(10,9),10000,0,false,true);
        //that's 50,000,000
        setTotalEnergyProduced(10000000);
        setFinishedProgress(1000);
        itemStackHadler.setSlotConfig(0,true,true);
        fluidHandler = null;
        displayName = ModBlocks.STUPID_CORE_REACTOR.getLocalizedName();
    }

    @Override
    public boolean hasFuel() {
        return itemStackHadler.getStackInSlot(0).getItem() == ModItems.STUPID_CORE_ITEM;
    }

    @Override
    public void consumeFuel() {
        itemStackHadler.extractItem(0,1,false);
    }

    @Override
    public void update() {
        super.update();

        if(inProgress()) {
            targetTemperature = 1000;
            updateTemperature();
        }
        if(temperature >= 666){
            world.createExplosion(null,pos.getX(),pos.getY(),pos.getZ(),50f,true);
        }


    }
}
