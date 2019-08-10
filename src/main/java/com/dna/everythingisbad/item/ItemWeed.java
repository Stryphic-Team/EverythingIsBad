package com.dna.everythingisbad.item;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.creativetab.CreativeTabEverythingBad;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModPotions;
import com.dna.everythingisbad.utils.CommonUtils;
import com.dna.everythingisbad.utils.ModPotionUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemWeed extends ItemDrugBase {
    public ItemWeed(String name)
    {
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        this.setMaxStackSize(16);
        this.setCreativeTab(CreativeTabEverythingBad.EVERYTHING_BAD_TAB);
        ModItems.ITEMS.add(this);
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
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
