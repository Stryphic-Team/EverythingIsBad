package com.dna.everythingisbad.item.weapons;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.utils.IHasModel;
import net.minecraft.item.Item;

public class ItemGunBase extends Item implements IHasModel {
    public ItemGunBase(){
        this.maxStackSize = 1;
    }
    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this,0, "inventory");

    }

}
