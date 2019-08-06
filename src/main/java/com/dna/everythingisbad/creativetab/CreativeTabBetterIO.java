package com.dna.everythingisbad.creativetab;

import com.dna.everythingisbad.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabBetterIO {
    public static final CreativeTabs BETTERIO_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase()) {
        @Override
        public ItemStack getTabIconItem() {
            return null;
        }
//        @Override
//        public Item getTabIconItem() {
//            //return new ItemStack(BlockGlass).getItem();
//            ItemStack itemStack = new ItemStack(new BlockGlowstone()).getItem();
//            return itemStack;
//        }


    };
};
