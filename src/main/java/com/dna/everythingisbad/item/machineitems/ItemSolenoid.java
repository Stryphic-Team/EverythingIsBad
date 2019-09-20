package com.dna.everythingisbad.item.machineitems;

import com.dna.everythingisbad.item.IOreDictItem;
import com.dna.everythingisbad.item.ItemBase;
import net.minecraftforge.oredict.OreDictionary;

public class ItemSolenoid extends ItemBase implements IOreDictItem {
    public ItemSolenoid(String name){
        super(name);
    }

    @Override
    public void initOreDict() {
        OreDictionary.registerOre("wireCopper",this);
    }
}
