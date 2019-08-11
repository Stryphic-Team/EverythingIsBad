package com.dna.everythingisbad.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;

public class ModPotionUtils extends PotionUtils {

    public static ItemStack addPotionToItemStack(ItemStack itemIn, PotionType potionIn)
    {
        ResourceLocation resourcelocation = PotionType.REGISTRY.getNameForObject(potionIn);

        if (potionIn == PotionTypes.EMPTY)
        {
            if (itemIn.hasTagCompound())
            {
                NBTTagCompound nbttagcompound = itemIn.getTagCompound();
                nbttagcompound.removeTag("Potion");

                if (nbttagcompound.hasNoTags())
                {
                    itemIn.setTagCompound((NBTTagCompound)null);
                }
            }
        }
        else
        {
            NBTTagCompound nbttagcompound1 = itemIn.hasTagCompound() ? itemIn.getTagCompound() : new NBTTagCompound();
            nbttagcompound1.setString("Potion", resourcelocation.toString());
            itemIn.setTagCompound(nbttagcompound1);
        }

        return itemIn;
    }
    public static boolean potionsActive(EntityPlayer player, Potion... potions){
        for(Potion potion: potions){
            try {
                player.getActivePotionEffect(potion);

            }catch (NullPointerException e) {
                return false;
            }
        }
        return true;
    }
}
