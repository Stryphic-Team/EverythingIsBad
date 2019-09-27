package com.dna.everythingisbad.block.buildingblocks;

import com.dna.everythingisbad.block.BlockFallingBase;
import net.minecraft.block.SoundType;

public class BloodBlock extends BlockFallingBase {
    public BloodBlock(String name){
        super(name);
        setSoundType(SoundType.STONE);
        setHardness(5f);
        setResistance(30f);
    }
}
