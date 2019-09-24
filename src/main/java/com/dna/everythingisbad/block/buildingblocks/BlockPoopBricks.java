package com.dna.everythingisbad.block.buildingblocks;

import com.dna.everythingisbad.block.BlockBase;
import net.minecraft.block.SoundType;

public class BlockPoopBricks extends BlockBase {
    public BlockPoopBricks(String name) {

        super(name);
        setSoundType(SoundType.STONE);
        setHardness(2f);
        setResistance(15);
    }
}
