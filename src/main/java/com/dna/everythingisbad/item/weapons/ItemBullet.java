package com.dna.everythingisbad.item.weapons;

import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.item.ItemBase;
import com.dna.everythingisbad.utils.CommonUtils;

public class ItemBullet extends ItemBase {
    public ItemBullet(String name){
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        ModItems.ITEMS.add(this);
    }
}
