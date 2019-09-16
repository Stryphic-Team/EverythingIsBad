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
    public static final DryerRecipePrototype BLOOD_RECIPE = new DryerRecipePrototype(
            120,
            ModFluids.BLOOD.getFluid(),
            10000,
            new ItemStack(ModBlocks.BLOOD_BLOCK)
    );
    public static final DryerRecipePrototype URINE_RECIPE = new DryerRecipePrototype(
            120,
            ModFluids.URINE.getFluid(),
            10000,
            new ItemStack(ModBlocks.URINE_BLOCK)
    );
    public static final DryerRecipePrototype POOP_RECIPE = new DryerRecipePrototype(
            120,
            ModFluids.DIARIA.getFluid(),
            10000,
            new ItemStack(ModBlocks.POOP_BLOCK)
    );
    public static final DryerRecipePrototype DEVILS_PEE_RECIPE = new DryerRecipePrototype(
            120,
            ModFluids.DEVILS_PEE.getFluid(),
            10000,
            new ItemStack(ModBlocks.URINE_BLOCK)
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
