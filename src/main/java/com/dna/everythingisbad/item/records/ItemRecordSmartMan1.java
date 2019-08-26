package com.dna.everythingisbad.item.records;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModSoundEvents;
import com.dna.everythingisbad.utils.CommonUtils;

public class ItemRecordSmartMan1 extends ItemRecordBase {
    private final String displayName;

    public ItemRecordSmartMan1(String name) {
        super(name, ModSoundEvents.SOUND_EVENT_SMART_MAN1);
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        this.displayName = "item." + name + ".desc";
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        ModItems.ITEMS.add(this);
    }
}
