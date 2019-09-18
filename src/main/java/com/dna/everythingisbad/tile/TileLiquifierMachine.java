package com.dna.everythingisbad.tile;

import com.dna.everythingisbad.capabilities.ModEnergyHandler;
import com.dna.everythingisbad.capabilities.ModFluidHandler;
import com.dna.everythingisbad.capabilities.ModItemHandler;
import com.dna.everythingisbad.init.LiquifierRecipes;
import com.dna.everythingisbad.utils.prototypes.LiquifierRecipePrototype;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileLiquifierMachine extends TileMachineBase {
    public TileLiquifierMachine() {
        super("liquifier_machine");
        this.itemStackHadler = new ModItemHandler(1);
        this.energyHandler = new ModEnergyHandler(1000000,0,1000,true,false);
        this.fluidHandler = new ModFluidHandler(true,false);
        setFinishedProgress(2400);
        itemStackHadler.setSlotConfig(0,true,true);
        this.displayName = "Liquifier Machine";
        outputSlot = 0;
    }

    @Override
    public void update() {
        super.update();

    }

    @Override
    public boolean hasNecessaryItems() {
        ItemStack itemInput = itemStackHadler.getStackInSlot(0);
        LiquifierRecipePrototype recipe = LiquifierRecipes.getRecipe(itemInput.getItem());
        if(recipe != null){
            setFinishedProgress(recipe.getDuration());
            if(fluidHandler.addFluid(new FluidStack(recipe.getOutput(),recipe.getOutputAmount()),true)){
                if(energyHandler.reduceEnergy(recipe.getEnergyConsumed() / recipe.getDuration(),true)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void insertOutput() {
        ItemStack itemInput = itemStackHadler.getStackInSlot(0);
        LiquifierRecipePrototype recipe = LiquifierRecipes.getRecipe(itemInput.getItem());
        fluidHandler.addFluid(new FluidStack(recipe.getOutput(),recipe.getOutputAmount()),false);
    }

    @Override
    public void reduceInput() {
        itemStackHadler.getStackInSlot(0).shrink(1);
    }

    @Override
    public void reduceEnergy() {
        ItemStack itemInput = itemStackHadler.getStackInSlot(0);
        LiquifierRecipePrototype recipe = LiquifierRecipes.getRecipe(itemInput.getItem());
        energyHandler.reduceEnergy(recipe.getEnergyConsumed() / recipe.getDuration(),false);
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
