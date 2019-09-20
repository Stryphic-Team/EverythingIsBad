package com.dna.everythingisbad.item;

import net.minecraftforge.oredict.OreDictionary;

public class ItemCopperIngot extends ItemBase implements IOreDictItem{
    public ItemCopperIngot(String name){
        super(name);
    }

    @Override
    public void initOreDict() {
        OreDictionary.registerOre("ingotCopper",this);
    }
}
