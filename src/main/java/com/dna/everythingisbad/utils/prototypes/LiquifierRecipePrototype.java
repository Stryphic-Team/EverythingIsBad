package com.dna.everythingisbad.utils.prototypes;

import com.dna.everythingisbad.init.LiquifierRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

public class LiquifierRecipePrototype {
    private int duration;
    private ItemStack input;
    private int energyConsumed;
    private Fluid output;
    private int outputAmount;

    public LiquifierRecipePrototype(int duration, ItemStack input,int energyConsumed,Fluid output,int outputAmount){
        this.duration = duration;
        this.input = input;
        this.energyConsumed = energyConsumed;
        this.output = output;
        this.outputAmount = (int)((float)outputAmount * LiquifierRecipes.diminishingPercent);
        LiquifierRecipes.RECIPES.add(this);
    }

    public int getDuration() {
        return duration;
    }

    public ItemStack getInput() {
        return input;
    }

    public int getEnergyConsumed() {
        return energyConsumed;
    }

    public Fluid getOutput() {
        return output;
    }

    public int getOutputAmount() {
        return outputAmount;
    }
}
