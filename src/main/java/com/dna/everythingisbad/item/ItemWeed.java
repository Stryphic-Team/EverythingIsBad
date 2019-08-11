package com.dna.everythingisbad.item;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;

public class ItemWeed extends ItemDrugBase {
    public ItemWeed(String name)
    {
        setRegistryName(name);
        setUnlocalizedName(name);
        this.setMaxStackSize(16);
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        ModItems.ITEMS.add(this);
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @Override
    public void getSubItems(net.minecraft.creativetab.CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            for (PotionType potiontype : PotionType.REGISTRY){
                //Main.logger.info(potiontype.getRegistryName());

                // only one "potion" for weed. highness effect lol

                if (potiontype.getRegistryName().toString().equals("everythingbad:highness")) {
                    items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), potiontype));
                }
            }
            //PotionType potiontype = ModPotions.POTION_DRUG_BASE;
            //PotionType potiontype = PotionType.REGISTRY;
            //if (potiontype != null) {
                //items.add(PotionUtils.addPotionToItemStack(new ItemStack(this), potiontype));
                //items.add(new ItemStack(this));
                //ModItems.ITEMS.add(this);
            //}
        }
    }
}
