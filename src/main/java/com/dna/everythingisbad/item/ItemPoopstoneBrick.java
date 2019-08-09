package com.dna.everythingisbad.item;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.creativetab.CreativeTabEverythingBad;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.reference.Reference;

public class ItemPoopstoneBrick extends ItemBase {
    public ItemPoopstoneBrick(String name)
    {
        setRegistryName(name);
        setUnlocalizedName(name);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(CreativeTabEverythingBad.EVERYTHING_BAD_TAB);
        ModItems.ITEMS.add(this);
    }
    @Override
    public String getUnlocalizedName(){
        //easy storage format: blockName
        //convert to proper format: tile.[modID]:[blockName].name
        return String.format("item.%s:%s", Reference.MOD_ID.toLowerCase(), getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }
}
