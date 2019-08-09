package com.dna.everythingisbad.block;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.creativetab.CreativeTabEverythingBad;
import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockFallingBase extends BlockFalling implements IHasModel {
    protected String name;

    public BlockFallingBase(Material material) {
        //material determines sound, map color, tool?, flammability, etc
        super(material);
        setHardness(2);
        setResistance(30);
        this.setCreativeTab(CreativeTabEverythingBad.EVERYTHING_BAD_TAB);
    }

    public BlockFallingBase(){
        this(Material.ROCK);//we'll use rock as default
    }





    public String getName(){
        return name;
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
