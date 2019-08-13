package com.dna.everythingisbad.item;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;

public class ItemChickenSoup extends ItemFoodBase {
    public ItemChickenSoup(String name) {
        super(100, 100, true);
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        ModItems.ITEMS.add(this);
    }
}
