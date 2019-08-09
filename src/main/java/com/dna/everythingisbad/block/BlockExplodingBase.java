package com.dna.everythingisbad.block;

import com.dna.everythingisbad.creativetab.CreativeTabEverythingBad;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.Material;

public class BlockExplodingBase extends BlockTNT {
    public BlockExplodingBase() {
        //material determines sound, map color, tool?, flammability, etc
        super();
        setHardness(2);
        setResistance(30);
        this.setCreativeTab(CreativeTabEverythingBad.EVERYTHING_BAD_TAB);
    }


}
