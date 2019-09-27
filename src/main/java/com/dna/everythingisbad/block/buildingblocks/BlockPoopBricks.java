package com.dna.everythingisbad.block.buildingblocks;

import com.dna.everythingisbad.block.BlockBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockPoopBricks extends BlockBase {
    public BlockPoopBricks(String name) {

        super(name, Material.ROCK);
        setSoundType(SoundType.STONE);
        setHardness(2f);
        setResistance(15);
    }
}
