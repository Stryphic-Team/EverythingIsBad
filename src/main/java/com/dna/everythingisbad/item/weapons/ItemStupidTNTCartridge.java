package com.dna.everythingisbad.item.weapons;

import net.minecraft.item.ItemStack;

public class ItemStupidTNTCartridge extends ItemCartridgeBase {
    public ItemStupidTNTCartridge(String name){
        super(name,8);;
    }


    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return false;
    }
}