package com.dna.everythingisbad.tile.generators;

import com.dna.everythingisbad.init.ModFluids;
import com.dna.everythingisbad.tile.TileGeneratorBase;
import net.minecraft.util.ITickable;

public class TileDiaricGenerator extends TileGeneratorBase implements ITickable {
    public TileDiaricGenerator(){
        super("diaric_generator");
        fluidHandler.addToWhitelist(ModFluids.DIARIA.getFluid());
        setFinishedProgress(240);
        setTotalEnergyProduced(24000);
        this.displayName = "Diaric Generator";
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
