package com.dna.everythingisbad.block;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.utils.IHasModel;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;

public class BlockLogBase extends BlockLog implements IHasModel {
    public BlockLogBase()
    {
        setSoundType(SoundType.WOOD);
        setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
