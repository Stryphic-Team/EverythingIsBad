package com.dna.everythingisbad.item;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.utils.IHasModel;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;

public class ItemRecordBase extends ItemRecord implements IHasModel {

    protected ItemRecordBase(String name, SoundEvent soundIn) {
        super(name, soundIn);
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this,0, "inventory");

    }
}
