package com.dna.everythingisbad.block;

import net.minecraft.block.SoundType;

public class BlockCloud extends BlockBase {

    public BlockCloud(String name){
        super(name);
        setHardness(0.15f);
        setSoundType(SoundType.SNOW);

    }
}
