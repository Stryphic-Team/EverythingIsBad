package com.dna.everythingisbad.tile.processing;

import com.dna.everythingisbad.init.DryerRecipes;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.tile.TileMachineBase;
import com.dna.everythingisbad.tile.utils.handlers.ModEnergyHandler;
import com.dna.everythingisbad.tile.utils.handlers.ModFluidHandler;
import com.dna.everythingisbad.tile.utils.handlers.ModItemHandler;
import com.dna.everythingisbad.utils.prototypes.DryerRecipePrototype;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

public class TileDryerMachine extends TileMachineBase {
    public TileDryerMachine() {
        super("dryer_machine");
        this.itemStackHadler = new ModItemHandler(2);
        this.fluidHandler = new ModFluidHandler(false,true);
        this.energyHandler = new ModEnergyHandler(100000,0,1000,true,false);
        setFinishedProgress(2400);
        itemStackHadler.setSlotConfig(1,true,false);
        itemStackHadler.setSlotConfig(0,true,true);
        this.displayName = ModBlocks.DRYER_MACHINE.getLocalizedName();
        outputSlot = 1;
    }

    @Override
    public void update() {
        super.update();
        FluidStack fluidInBucket = FluidUtil.getFluidContained(itemStackHadler.getStackInSlot(0));
        if(fluidInBucket != null){
            int fluidFilled = fluidHandler.fill(fluidInBucket,true);
            if(fluidFilled > 0){
                itemStackHadler.setStackInSlot(0,new ItemStack(Items.BUCKET));
            }
        }

    }
    //Checks to make sure that all conditions for the recipe are met
    public boolean hasNecessaryItems(){
        FluidStack fluidStack = fluidHandler.getFluidTank().getFluid();
        if(itemStackHadler.getStackInSlot(outputSlot).getCount() < itemStackHadler.getSlotLimit(outputSlot)) {
            if (fluidStack != null) {
                DryerRecipePrototype recipe = DryerRecipes.getRecipe(fluidStack.getFluid());
                if (recipe != null) {
                    setFinishedProgress(recipe.getDuration());
                    //Has a bucket of the fluid required for the recipe
                    if (fluidStack.amount >= 1000) {
                        //Has enough energy for one tick of the recipe
                        if (energyHandler.getEnergyStored() >= recipe.getEnergyConsumed() / recipe.getDuration()) {
                            Item itemInOutput = itemStackHadler.getStackInSlot(1).getItem();
                            if (itemInOutput == Items.AIR || itemInOutput == recipe.getOutput().getItem()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void insertOutput(){
        if(hasNecessaryItems()){
            FluidStack fluidStack = fluidHandler.getFluidTank().getFluid();
            DryerRecipePrototype recipe = DryerRecipes.getRecipe(fluidStack.getFluid());
            itemStackHadler.addItem(1,recipe.getOutput().copy(),false);
        }
    }
    public void reduceInput(){
        if(hasNecessaryItems()){
            fluidHandler.getFluidTank().drain(1000,true);
        }
    }
    public void reduceEnergy(){
        if(hasNecessaryItems()){
            FluidStack fluidStack = fluidHandler.getFluidTank().getFluid();
            DryerRecipePrototype recipe = DryerRecipes.getRecipe(fluidStack.getFluid());
            energyHandler.reduceEnergy(recipe.getEnergyConsumed() / recipe.getDuration(),false);
        }
    }


}
