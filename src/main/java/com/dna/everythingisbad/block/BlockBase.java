package com.dna.everythingisbad.block;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;



public class BlockBase extends Block implements IHasModel {
    protected String name;

    public BlockBase(Material material) {
        //material determines sound, map color, tool?, flammability, etc
        super(material);
        setHardness(2);
        setResistance(30);
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
    }

    public BlockBase(){
        this(Material.ROCK);//we'll use rock as default
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerModel(Item.getItemFromBlock(this),0);

    }
}
