package com.dna.everythingisbad.item.weapons;

import com.dna.everythingisbad.item.ItemBase;

public abstract class ItemGunBase extends ItemBase {
    public ItemGunBase(String name){
        super(name);
        this.maxStackSize = 1;
    }
}
