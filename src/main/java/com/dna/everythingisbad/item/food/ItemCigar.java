package com.dna.everythingisbad.item.food;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModPotions;
import com.dna.everythingisbad.utils.CommonUtils;

public class ItemCigar extends ItemFoodBase {
    public ItemCigar(String name){
        super(0, 0, false);
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        this.setPotionEffect(ModPotions.POTION_STIMULATION.getPotionEffect(),1f);
        ModItems.ITEMS.add(this);
        this.setAlwaysEdible();
    }
}
