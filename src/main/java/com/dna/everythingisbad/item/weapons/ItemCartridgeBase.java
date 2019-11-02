package com.dna.everythingisbad.item.weapons;

import com.dna.everythingisbad.item.ItemBase;
import net.minecraft.item.ItemStack;

public abstract class ItemCartridgeBase extends ItemBase {
    private int rounds;

    public ItemCartridgeBase(String name,int rounds){
        super(name);
        this.rounds = rounds;
        setMaxStackSize(1);
        setMaxDamage(rounds);
    }
    @Override
    public boolean isDamageable()
    {
        return true;
    }

    public static int getRounds(ItemStack itemStack) {
        if(itemStack.getItem() instanceof ItemCartridgeBase) {
            return itemStack.getItemDamage();
        }
        return 0;
    }
    public int getMaxRounds(){
        return rounds;
    }
}
