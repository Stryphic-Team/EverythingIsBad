package com.dna.everythingisbad.tile.processing;

import com.dna.everythingisbad.init.LiquifierRecipes;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.tile.TileMachineBase;
import com.dna.everythingisbad.tile.utils.handlers.ModEnergyHandler;
import com.dna.everythingisbad.tile.utils.handlers.ModFluidHandler;
import com.dna.everythingisbad.tile.utils.handlers.ModItemHandler;
import com.dna.everythingisbad.utils.prototypes.LiquifierRecipePrototype;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class TileLiquifierMachine extends TileMachineBase {
    public TileLiquifierMachine() {
        super("liquifier_machine");
        this.itemStackHadler = new ModItemHandler(1);
        this.energyHandler = new ModEnergyHandler(100000,0,1000,true,false);
        this.fluidHandler = new ModFluidHandler(true,false);
        setFinishedProgress(2400);
        itemStackHadler.setSlotConfig(0,true,true);
        this.displayName = ModBlocks.LIQUIFIER_MACHINE.getLocalizedName();
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
            energyUsedPerTick = recipe.getEnergyConsumed() / recipe.getDuration();
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

}
