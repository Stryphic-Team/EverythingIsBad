package com.dna.everythingisbad.item;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;

/**
 * Only a crafting item
 */
public class ItemStupidCore extends ItemBase {
    public ItemStupidCore(String name){
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));

        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        ModItems.ITEMS.add(this);
    }
}
