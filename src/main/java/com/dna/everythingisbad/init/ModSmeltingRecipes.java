package com.dna.everythingisbad.init;

import com.dna.everythingisbad.utils.prototypes.SmeltingPrototype;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

public class ModSmeltingRecipes {
    public static ArrayList<SmeltingPrototype> RECIPES = new ArrayList<SmeltingPrototype>();
    //System for loading recipes
    public static final SmeltingPrototype POOPSTONE_BRICK_RECIPE =
            new SmeltingPrototype(ModItems.POOP_ITEM,new ItemStack(ModItems.POOP_BRICK_ITEM,1),1f);
    public static final SmeltingPrototype JESUS_MEAT_COOKED_RECIPE =
            new SmeltingPrototype(ModItems.JESUS_MEAT_RAW,new ItemStack(ModItems.JESUS_MEAT_COOKED,1),1f);
    public static final SmeltingPrototype FRIED_DRAGON_EGG_RECIPE =
            new SmeltingPrototype(new ItemStack(Blocks.DRAGON_EGG,1).getItem(),new ItemStack(ModItems.FRIED_DRAGON_EGG_ITEM,1),0.5f);
    public static final SmeltingPrototype URINE_BRICK_RECIPE =
            new SmeltingPrototype(ModItems.URINE_CRYSTAL_ITEM, new ItemStack(ModItems.URINE_BRICK_ITEM,1),1f);
    public static final SmeltingPrototype COPPER_ORE_RECIPE =
            new SmeltingPrototype(new ItemStack(ModBlocks.COPPER_ORE_BLOCK).getItem(), new ItemStack(ModItems.COPPER_INGOT_ITEM,1),1f);
    public static final SmeltingPrototype BABY_COOKIN_SHIT =
            new SmeltingPrototype(new ItemStack(ModItems.BABY_ITEM).getItem(),new ItemStack(ModItems.COOKED_BABY_ITEM,1),1);

    public static void init(){
        for(SmeltingPrototype recipe:RECIPES){
            GameRegistry.addSmelting(recipe.getInputItem(),recipe.getOutputItem(),recipe.getSpeed());
        }

    }
}
