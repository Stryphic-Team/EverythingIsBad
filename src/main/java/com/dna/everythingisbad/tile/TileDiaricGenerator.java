package com.dna.everythingisbad.tile;

import com.dna.everythingisbad.init.ModFluids;
import net.minecraft.util.ITickable;

public class TileDiaricGenerator extends TileGeneratorBase implements ITickable {
    private int ticks = 0;

    public TileDiaricGenerator(){
        super();
        fluidCapability.addToWhitelist(ModFluids.DIARIA.getFluid());
    }

    @Override
    public void update() {
        super.update();
        if(energyCapability.addEnergy(1000,true)){
            if(fluidCapability.getFluidTank().getFluidAmount() >= 10){
                fluidCapability.reduceFluid(10,false);
                energyCapability.addEnergy(1000,false);
            }
        }
        //
        //energyCapability.receiveEnergy(10000,false);
    }
    static {
        register("diaric_generator",TileDiaricGenerator.class);
    }
}
