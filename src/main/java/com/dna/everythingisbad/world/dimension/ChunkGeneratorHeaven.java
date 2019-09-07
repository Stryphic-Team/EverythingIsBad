package com.dna.everythingisbad.world.dimension;

import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGeneratorFlat;

import java.util.Random;

public class ChunkGeneratorHeaven extends ChunkGeneratorFlat {
    public ChunkGeneratorHeaven(World worldIn,boolean generateStructures) {
        super(worldIn, new Random().nextLong(), generateStructures,"");
    }
}
