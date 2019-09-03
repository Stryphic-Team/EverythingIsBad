package com.dna.everythingisbad.capabilities;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

import javax.annotation.Nullable;

public class ModFluidCapability implements IFluidHandler {
    private FluidTank fluidTank;
    private boolean isFillable;
    private boolean isDrainable;
    public ModFluidCapability(boolean isDrainable, boolean isFillable){
        this.isDrainable = isDrainable;
        this.isFillable = isFillable;
        this.fluidTank = new FluidTank(16000);
    }
    @Override
    public IFluidTankProperties[] getTankProperties() {
        return fluidTank.getTankProperties();
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        return fluidTank.fill(resource,doFill);
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        return resource;
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return fluidTank.drain(maxDrain,doDrain);
    }
}
