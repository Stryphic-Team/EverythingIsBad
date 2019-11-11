package com.dna.everythingisbad.item.armor;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModArmorMaterials;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ItemCamoLeggings extends ItemArmorBase {
    public ItemCamoLeggings(String name) {
        super(ModArmorMaterials.ARMOR_MATERIAL_CAMO,1, EntityEquipmentSlot.LEGS);
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        setMaxStackSize(1);
        ModItems.ITEMS.add(this);
    }
}
