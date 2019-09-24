package com.dna.everythingisbad.item;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import com.dna.everythingisbad.utils.IHasModel;
import net.minecraft.item.Item;

public abstract class ItemBase extends Item implements IHasModel {

    public ItemBase(String name){
        this(name,true);
    }
    public ItemBase(String name,boolean addToCreativeTab){
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        if(addToCreativeTab) {
            this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        }
        ModItems.ITEMS.add(this);
    }
    @Override
    public void registerModels()
    {
        Main.proxy.registerModel(this,0);

    }
}
