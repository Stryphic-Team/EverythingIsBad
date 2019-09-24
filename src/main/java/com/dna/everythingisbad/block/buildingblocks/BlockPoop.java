package com.dna.everythingisbad.block.buildingblocks;

import com.dna.everythingisbad.block.BlockFallingBase;
import net.minecraft.block.SoundType;

public class BlockPoop extends BlockFallingBase {
    public BlockPoop(String name) {
        super(name);
        setSoundType(SoundType.SLIME);
        setHardness(0.5f);
        setResistance(1);
    }
}
