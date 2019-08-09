package com.dna.everythingisbad.init;

import com.dna.everythingisbad.utils.SmeltingRecipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModSmeltingRecipes {
    //System for loading recipes
    public static final SmeltingRecipe POOPSTONE_BRICK_RECIPE =
            new SmeltingRecipe(ModItems.POOP_ITEM,new ItemStack(ModItems.POOP_BRICK_ITEM,1),1f);

    public static final SmeltingRecipe[] SMELTING_RECIPES = new SmeltingRecipe[]{
            POOPSTONE_BRICK_RECIPE
    };
    public static void init(){
        for(SmeltingRecipe recipe:SMELTING_RECIPES){
            GameRegistry.addSmelting(recipe.getInputItem(),recipe.getOutputItem(),recipe.getSpeed());
        }

    }
}
