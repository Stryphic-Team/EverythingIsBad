package com.dna.everythingisbad.item.weapons;

import com.dna.everythingisbad.item.ItemBase;
import net.minecraft.item.ItemStack;

public class ItemStupidTNTCartridge extends ItemBase {
    public ItemStupidTNTCartridge(String name){
        super(name);
        this.setMaxDamage(8);
        this.setMaxStackSize(1);
    }
    @Override
    public boolean isDamageable()
    {
        return true;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return false;
    }
}