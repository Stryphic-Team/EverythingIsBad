package com.dna.everythingisbad.item;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;

public class ItemSoul extends ItemBase {
    public ItemSoul(String name) {

        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        this.setMaxStackSize(1);
        ModItems.ITEMS.add(this);
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
