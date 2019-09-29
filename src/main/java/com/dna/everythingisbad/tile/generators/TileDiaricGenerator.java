package com.dna.everythingisbad.tile.generators;

import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModFluids;
import com.dna.everythingisbad.tile.TileGeneratorBase;
import com.dna.everythingisbad.tile.utils.handlers.ModEnergyHandler;
import net.minecraft.util.ITickable;

public class TileDiaricGenerator extends TileGeneratorBase implements ITickable {
    public TileDiaricGenerator(){
        super("diaric_generator");
        fluidHandler.addToWhitelist(ModFluids.DIARIA.getFluid());
        energyHandler = new ModEnergyHandler(100000,50,0,false,true);
        setFinishedProgress(240);
        setTotalEnergyProduced(24000);
        this.displayName = ModBlocks.DIARIC_GENERATOR.getLocalizedName();
    }
    @Override
    public boolean hasFuel() {
        return fluidHandler.getFluidTank().getFluidAmount() >= 1000;
    }

    @Override
    public void consumeFuel() {
        fluidHandler.reduceFluid(1000,false);
    }
}
