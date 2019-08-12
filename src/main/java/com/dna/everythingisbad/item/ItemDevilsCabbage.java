package com.dna.everythingisbad.item;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModPotions;
import com.dna.everythingisbad.potion.PotionDrugBase;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemDevilsCabbage extends ItemFoodBase {
    public ItemDevilsCabbage(String name)
    {
        super(0,0,true);
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        this.setPotionEffect(ModPotions.POTION_HIGHNESS.getPotionEffect(),1f);
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        ModItems.ITEMS.add(this);
    }
}
