package com.dna.everythingisbad.capabilities;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class ModFluidHandler implements IFluidHandler {
    private FluidTank fluidTank;
    private boolean isFillable;
    private boolean isDrainable;
    private ArrayList<Fluid> fluidWhitelist = new ArrayList<Fluid>();
    public ModFluidHandler(boolean isDrainable, boolean isFillable){
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
        if(fluidWhitelist.size() == 0){
            return fluidTank.fill(resource,doFill);
        }
        for(Fluid fluid:fluidWhitelist){
            if(resource.getFluid().equals(fluid)){
                return fluidTank.fill(resource,doFill);
            }
        }
        return 0;

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
    public void addToWhitelist(Fluid fluid){
        fluidWhitelist.add(fluid);
    }
    public ArrayList<Fluid> getFluidWhitelist(){
        return fluidWhitelist;
    }
    public boolean reduceFluid(int amount,boolean simulate){
        FluidStack fluidTransferred = fluidTank.drain(amount,true);

        if(fluidTransferred != null) {
            return fluidTransferred.amount > 0;
        }else{
            return false;
        }

    }
    public FluidTank getFluidTank(){
        return fluidTank;
    }

}
