package com.dna.everythingisbad.item.armor;

import com.dna.everythingisbad.item.ItemBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class ItemSantaHat extends ItemBase {
    public ItemSantaHat(String name){
        super(name);
        setMaxStackSize(1);

    }
    @Override
    public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity) {
        return armorType == EntityEquipmentSlot.HEAD && entity instanceof EntityPlayer;
    }
}
