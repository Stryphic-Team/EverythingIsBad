package com.dna.everythingisbad.block.ore;

import com.dna.everythingisbad.block.BlockBase;
import com.dna.everythingisbad.block.IOreDictBlock;
import net.minecraftforge.oredict.OreDictionary;

public class BlockCopperOre extends BlockBase implements IOreDictBlock {
    public BlockCopperOre(String name){
        super(name);
        setHardness(10);
        setHarvestLevel("Stone",1);
    }

    @Override
    public void initOreDict() {
        OreDictionary.registerOre("oreCopper",this);
    }
}
