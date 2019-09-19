package com.dna.everythingisbad.init;


import com.dna.everythingisbad.utils.prototypes.LiquifierRecipePrototype;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.ArrayList;
import java.util.HashMap;

public class LiquifierRecipes {
    public static ArrayList<LiquifierRecipePrototype> RECIPES = new ArrayList<LiquifierRecipePrototype>();
    private static HashMap<Item,LiquifierRecipePrototype> RECIPES_MAP = new HashMap<Item,LiquifierRecipePrototype>();
    //adjust the diminishing return can be adjusted V with this number
    public static float diminishingPercent = 1 - 0.02f;
    public static final LiquifierRecipePrototype POOP_TO_DIARIA = new LiquifierRecipePrototype(
            100,
            new ItemStack(ModItems.POOP_ITEM),
            1000,ModFluids.DIARIA.getFluid(),
            250);
    public static final LiquifierRecipePrototype POOP_BLOCK_TO_DIARIA = new LiquifierRecipePrototype(
            400,
            new ItemStack(ModBlocks.POOP_BLOCK),
            4000,ModFluids.DIARIA.getFluid(),
            1000);
    public static final LiquifierRecipePrototype URINE_CRYSTAL_TO_URINE = new LiquifierRecipePrototype(
            100,
            new ItemStack(ModItems.URINE_CRYSTAL_ITEM),
            1000,ModFluids.URINE.getFluid(),
            250);
    public static final LiquifierRecipePrototype URINE_BLOCK_TO_URINE = new LiquifierRecipePrototype(
            400,
            new ItemStack(ModBlocks.URINE_BLOCK),
            4000,ModFluids.URINE.getFluid(),
            1000);
    public static final LiquifierRecipePrototype COBBLESTONE_TO_LAVA = new LiquifierRecipePrototype(
            100,
            new ItemStack(Blocks.COBBLESTONE),
            1000, FluidRegistry.LAVA,
            100);
    public static final LiquifierRecipePrototype OBSIDIAN_TO_LAVA = new LiquifierRecipePrototype(
            400,
            new ItemStack(Blocks.OBSIDIAN),
            10000,FluidRegistry.LAVA,
            400);
    public static void init(){
        for(LiquifierRecipePrototype recipe:RECIPES){
            RECIPES_MAP.put(recipe.getInput().getItem(),recipe);
        }
    }
    public static LiquifierRecipePrototype getRecipe(Item input){
        if(RECIPES_MAP.containsKey(input)){
            return RECIPES_MAP.get(input);
        }

        return null;
    }
}
