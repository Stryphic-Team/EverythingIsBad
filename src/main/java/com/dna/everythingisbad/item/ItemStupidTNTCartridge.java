package com.dna.everythingisbad.item;
import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.item.ItemStack;

public class ItemStupidTNTCartridge extends ItemBase {
    public ItemStupidTNTCartridge(String name){
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        this.setMaxDamage(8);
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        ModItems.ITEMS.add(this);
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