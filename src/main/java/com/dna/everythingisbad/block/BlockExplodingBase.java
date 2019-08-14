package com.dna.everythingisbad.block;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.utils.IHasModel;
import net.minecraft.block.BlockTNT;
import net.minecraft.item.Item;

public class BlockExplodingBase extends BlockTNT implements IHasModel {
    public BlockExplodingBase() {
        //material determines sound, map color, tool?, flammability, etc
        super();
        setHardness(2);
        setResistance(30);
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
    }
    

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
