package com.dna.everythingisbad.block.buildingblocks;

import com.dna.everythingisbad.block.BlockFallingBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockPoop extends BlockFallingBase {

    public BlockPoop(String name) {
        super(name, Material.GROUND);
        setSoundType(SoundType.SLIME);
        setHardness(0.5f);
        setResistance(1);
    }
}
