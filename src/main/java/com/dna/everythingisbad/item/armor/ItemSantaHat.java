package com.dna.everythingisbad.item.armor;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.item.ItemBase;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class ItemSantaHat extends ItemBase {
    public ItemSantaHat(String name){
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        setMaxStackSize(1);
        ModItems.ITEMS.add(this);
    }
    @Override
    public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity) {
        return armorType == EntityEquipmentSlot.HEAD && entity instanceof EntityPlayer;
    }
}
