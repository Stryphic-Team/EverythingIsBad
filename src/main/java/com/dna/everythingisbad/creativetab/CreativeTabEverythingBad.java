package com.dna.everythingisbad.creativetab;

import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabEverythingBad {
    public static final CreativeTabs EVERYTHING_BAD_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase()) {

        @Override
        public ItemStack getTabIconItem() {
            ItemStack itemStack = new ItemStack(ModBlocks.STUPID_TNT_BLOCK);
            return itemStack;
        }


    };
};
