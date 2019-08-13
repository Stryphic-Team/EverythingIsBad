package com.dna.everythingisbad.item;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModSoundEvents;
import com.dna.everythingisbad.utils.CommonUtils;

public class ItemRecordGasolineBabies extends ItemRecordBase { //fixed

    private final String displayName;

    public ItemRecordGasolineBabies(String name) {
        super(name, ModSoundEvents.SOUND_EVENT_GASOLINE_BABIES);
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        this.displayName = "item." + name + ".desc";
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        ModItems.ITEMS.add(this);
    }

}
