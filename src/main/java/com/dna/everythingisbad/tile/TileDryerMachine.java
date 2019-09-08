package com.dna.everythingisbad.tile;

import com.dna.everythingisbad.capabilities.ModFluidHandler;
import com.dna.everythingisbad.capabilities.ModItemHandler;
import com.dna.everythingisbad.init.DryerRecipes;
import com.dna.everythingisbad.utils.prototypes.DryerRecipePrototype;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileDryerMachine extends TileMachineBase {
    public TileDryerMachine() {
        super("dryer_machine");
        this.itemStackHadler = new ModItemHandler(2);
        this.fluidHandler = new ModFluidHandler(false,true);
        setFinishedProgress(2400);
        itemStackHadler.setExtractor(true);
        this.displayName = "Dryer Machine";
    }

    @Override
    public void update() {
        super.update();
        FluidStack fluidStack = fluidHandler.getFluidTank().getFluid();
        if(fluidStack != null){
            Fluid fluidInTank = fluidStack.getFluid();
            DryerRecipePrototype dryerRecipe = DryerRecipes.getRecipe(fluidInTank);
            if(dryerRecipe != null && fluidStack.amount >= 1000 && itemStackHadler.getStackInSlot(1).getCount() < 64){
                setFinishedProgress(dryerRecipe.getDuration());
                if(itemStackHadler.insertItem(1,dryerRecipe.getOutput(),true).getCount() > 0) {
                    if (energyHandler.getEnergyStored() >= dryerRecipe.getEnergyConsumed() / dryerRecipe.getDuration()) {
                        stepProgress();
                        energyHandler.reduceEnergy(dryerRecipe.getEnergyConsumed() / dryerRecipe.getDuration(), false);
                    }
                    if (getProgress() == getFinishedProgress()) {
                        setProgress(0);
                        itemStackHadler.setStackInSlot(1,dryerRecipe.getOutput());
                        itemStackHadler.insertItem(1,dryerRecipe.getOutput(),false);
                        fluidHandler.getFluidTank().drain(1000, true);
                    }
                }
            }else{
                setProgress(0);
            }
        }
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing from) {

        return capability == CapabilityEnergy.ENERGY || capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
    }
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(energyHandler);
        }
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){

            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHadler);
        }
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){

            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(fluidHandler);
        }
        return super.getCapability(capability, facing);
    }
}
