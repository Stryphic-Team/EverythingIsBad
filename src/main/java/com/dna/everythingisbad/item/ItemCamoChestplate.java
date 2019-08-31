package com.dna.everythingisbad.item;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModArmorMaterials;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import com.dna.everythingisbad.utils.IHasModel;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemCamoChestplate extends ItemArmorBase {
    public ItemCamoChestplate(String name) {
        super(ModArmorMaterials.ARMOR_MATERIAL_CAMO,1,EntityEquipmentSlot.CHEST);
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        setMaxStackSize(1);
        ModItems.ITEMS.add(this);
    }
    //@Override
    //public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity) {
    //    return armorType == EntityEquipmentSlot.CHEST && entity instanceof EntityPlayer;
    //}
}
