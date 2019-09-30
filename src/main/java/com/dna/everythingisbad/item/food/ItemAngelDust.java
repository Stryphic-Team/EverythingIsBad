package com.dna.everythingisbad.item.food;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModPotions;
import com.dna.everythingisbad.utils.CommonUtils;

public class ItemAngelDust extends ItemFoodBase {
    public ItemAngelDust(String name)
    {
        super(0,0,false);
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        this.setPotionEffect(ModPotions.POTION_ADRENALINE.getPotionEffect(),1f);
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        ModItems.ITEMS.add(this);
        this.setAlwaysEdible();
    }
}
