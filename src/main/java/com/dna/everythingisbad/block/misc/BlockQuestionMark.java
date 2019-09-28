package com.dna.everythingisbad.block.misc;

import com.dna.everythingisbad.block.BlockBase;
import net.minecraft.block.material.Material;

public class BlockQuestionMark extends BlockBase {
    public BlockQuestionMark(String name) {
        super(name, Material.ROCK);
        setHardness(2f);
        setResistance(15);
    }
}
