package com.dna.everythingisbad.block;

import com.dna.everythingisbad.creativetab.CreativeTabEverythingBad;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;

public class BlockFallingBase extends BlockFalling {
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
}
