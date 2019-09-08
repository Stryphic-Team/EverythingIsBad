package com.dna.everythingisbad.init;

import com.dna.everythingisbad.utils.prototypes.DryerRecipePrototype;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.ArrayList;
import java.util.HashMap;

public class DryerRecipes {
    public static ArrayList<DryerRecipePrototype> RECIPES = new ArrayList<DryerRecipePrototype>();
    private static HashMap<Fluid,DryerRecipePrototype> RECIPES_MAP = new HashMap<Fluid,DryerRecipePrototype>();
    public static final DryerRecipePrototype WATER_RECIPE = new DryerRecipePrototype(
            240,
            FluidRegistry.WATER,
            100000,
            new ItemStack(Items.AIR)
    );
    public static final DryerRecipePrototype LAVA_RECIPE = new DryerRecipePrototype(
            240,
            FluidRegistry.LAVA,
            100000,
            new ItemStack(Blocks.OBSIDIAN)
    );

    public static void init(){
        for(DryerRecipePrototype recipe:RECIPES){
            RECIPES_MAP.put(recipe.getInput(),recipe);
        }
    }
    public static DryerRecipePrototype getRecipe(Fluid input){
        if(RECIPES_MAP.containsKey(input)){
            return RECIPES_MAP.get(input);
        }

        return null;
    }
}
