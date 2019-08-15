package com.dna.everythingisbad.creativetab;

import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.item.ItemStack;

public class CreativeTab {
    public static final net.minecraft.creativetab.CreativeTabs EVERYTHING_BAD_TAB = new net.minecraft.creativetab.CreativeTabs(Reference.MOD_ID.toLowerCase()) {
        @Override
        public ItemStack getTabIconItem() {
            ItemStack itemStack = new ItemStack(ModBlocks.STUPID_TNT_BLOCK);
            return itemStack;
        }


    };
}
