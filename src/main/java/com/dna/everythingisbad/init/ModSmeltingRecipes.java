package com.dna.everythingisbad.init;

import com.dna.everythingisbad.utils.prototypes.SmeltingPrototype;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModSmeltingRecipes {
    //System for loading recipes
    public static final SmeltingPrototype POOPSTONE_BRICK_RECIPE =
            new SmeltingPrototype(ModItems.POOP_ITEM,new ItemStack(ModItems.POOP_BRICK_ITEM,1),1f);
    public static final SmeltingPrototype JESUS_MEAT_COOKED_RECIPE =
            new SmeltingPrototype(ModItems.JESUS_MEAT_RAW,new ItemStack(ModItems.JESUS_MEAT_COOKED,1),1f);

    public static final SmeltingPrototype[] SMELTING_RECIPES = new SmeltingPrototype[]{
            POOPSTONE_BRICK_RECIPE,
            JESUS_MEAT_COOKED_RECIPE
    };
    public static void init(){
        for(SmeltingPrototype recipe:SMELTING_RECIPES){
            GameRegistry.addSmelting(recipe.getInputItem(),recipe.getOutputItem(),recipe.getSpeed());
        }

    }
}
