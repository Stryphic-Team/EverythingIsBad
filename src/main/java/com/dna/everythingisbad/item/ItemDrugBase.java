package com.dna.everythingisbad.item;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.utils.IHasModel;
import net.minecraft.item.ItemPotion;

public class ItemDrugBase extends ItemPotion implements IHasModel {

    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this,0, "inventory");

    }
}
