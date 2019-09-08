package com.dna.everythingisbad.utils.prototypes;

import com.dna.everythingisbad.init.DryerRecipes;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

public class DryerRecipePrototype {
    private int duration;
    private Fluid input;
    private int energyConsumed;
    private ItemStack output;

    public DryerRecipePrototype(int duration, Fluid input,int energyConsumed,ItemStack output){
        this.duration = duration;
        this.input = input;
        this.energyConsumed = energyConsumed;
        this.output = output;
        DryerRecipes.RECIPES.add(this);
    }

    public int getDuration() {
        return duration;
    }

    public Fluid getInput() {
        return input;
    }

    public int getEnergyConsumed() {
        return energyConsumed;
    }

    public ItemStack getOutput() {
        return output;
    }
}
