package com.dna.everythingisbad.init;

import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionHelper;

public class ModBrewingRecipes {
    public ModBrewingRecipes(){

    }
    public static void init(){
        // Highness potion
        PotionHelper.addMix(PotionTypes.AWKWARD,ModItems.DEVILS_CABBAGE_ITEM,ModPotions.POTION_HIGHNESS.getPotionType());

        // Hepatitis potion (Poop item has metadata 3)
        ItemStack poop = new ItemStack(ModItems.POOP_ITEM,1,3);
        Ingredient ing = Ingredient.fromStacks(poop);
        PotionHelper.addMix(PotionTypes.AWKWARD,ing,ModPotions.POTION_HEPATITIS.getPotionType());

        // Adrenaline potion
        PotionHelper.addMix(PotionTypes.AWKWARD,ModItems.ANGEL_DUST,ModPotions.POTION_ADRENALINE.getPotionType());
    }
}
