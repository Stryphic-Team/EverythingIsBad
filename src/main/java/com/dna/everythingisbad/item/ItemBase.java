package com.dna.everythingisbad.item;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.creativetab.CreativeTabEverythingBad;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import com.dna.everythingisbad.reference.Reference;

public class ItemBase extends Item implements IHasModel {
//    public ItemBase(String name)
//    {
//        setRegistryName(name);
//        setUnlocalizedName(name);
//        this.setHasSubtypes(true);
//        this.setMaxDamage(0);
//        this.setCreativeTab(CreativeTabEverythingBad.EVERYTHING_BAD_TAB);
//        ModItems.ITEMS.add(this);
//    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this,0, "inventory");

    }
}
