package com.dna.everythingisbad.item;

import net.minecraft.item.ItemStack;

public class ItemSoul extends ItemBase {
    public ItemSoul(String name) {

        super(name);
        this.setMaxStackSize(1);

    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        if (stack!=null && stack.getTagCompound()!=null && stack.getTagCompound().getString("player_name")!=null){
            String player_name = stack.getTagCompound().getString("player_name");
            return player_name + "'s Soul";
        }else{
            return "Soul";
        }
    }
}
