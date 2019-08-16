package com.dna.everythingisbad.item;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModPotions;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class ItemHotCheaters extends ItemFoodBase {
    public ItemHotCheaters(String name){
        super(6,7.5f,false);
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));

        //Potion potion = ModPotions.POTION_HIGHNESS.getPotion();
        //PotionEffect potioneffect = new PotionEffect(potion,0,0);
        //this.setPotionEffect(potioneffect,1f);

        // TODO Remove mining fatigue from highness

        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        ModItems.ITEMS.add(this);
    }
}
