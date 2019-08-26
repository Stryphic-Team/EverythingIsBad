package com.dna.everythingisbad.item;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;

public class ItemStringBass extends ItemBase {
    public ItemStringBass(String name)
    {

        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        this.setMaxStackSize(1);
        ModItems.ITEMS.add(this);
    }
}
