package com.dna.everythingisbad.block.buildingblocks;

import com.dna.everythingisbad.block.BlockBase;

public class BlockFlashy extends BlockBase {
    public BlockFlashy(String name) {
        super(name);
        setHardness(10);
        setHarvestLevel("pickaxe",0);
        setLightLevel(1f);
    }

}
