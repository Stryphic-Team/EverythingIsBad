package com.dna.everythingisbad.utils;

import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidCreator {
    //Fuck diddy

    private BlockFluidClassic blockfluidclassic; // Very original inspired name
    private Fluid fluid; // Very original inspired name

    public FluidCreator(BlockFluidClassic blockfluidclassic,Fluid fluid){
        //Yeah
        this.blockfluidclassic = blockfluidclassic;
        this.fluid = fluid;
    }

    // Extreme botom





    public BlockFluidClassic getBlockFluidClassic() {
        return blockfluidclassic;
    }

    public void setBlockFluidClassic(BlockFluidClassic blockfluidclassic) {
        this.blockfluidclassic = blockfluidclassic;
    }

    public Fluid getFluid() {
        return fluid;
    }

    public void setFluid(Fluid fluid) {
        this.fluid = fluid;
    }
}
