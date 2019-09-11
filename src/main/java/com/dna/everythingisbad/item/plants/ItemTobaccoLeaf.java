package com.dna.everythingisbad.item.plants;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.item.ItemBase;
import com.dna.everythingisbad.utils.CommonUtils;

public class ItemTobaccoLeaf extends ItemBase {
    public ItemTobaccoLeaf(String name){
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));

        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        ModItems.ITEMS.add(this);
    }
}
