package com.dna.everythingisbad.init;

import com.dna.everythingisbad.reference.Reference;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ModArmorMaterials {
    public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_CAMO = EnumHelper.addArmorMaterial("camo",
            Reference.MOD_ID + ":camo",14,
            new int[]{1,4,3,2},9, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN,
            0.0F);
}
