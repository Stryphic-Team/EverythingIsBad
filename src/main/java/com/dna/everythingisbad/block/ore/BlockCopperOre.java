package com.dna.everythingisbad.block.ore;

import com.dna.everythingisbad.block.BlockBase;

public class BlockCopperOre extends BlockBase {
    public BlockCopperOre(String name){
        super(name);
        setHardness(10);
        setHarvestLevel("Stone",1);
    }
}
