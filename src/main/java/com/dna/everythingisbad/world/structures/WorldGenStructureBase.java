package com.dna.everythingisbad.world.structures;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public abstract class WorldGenStructureBase extends WorldGenerator {
    @Override
    public abstract boolean generate(World worldIn, Random rand, BlockPos position);
    public static Rotation getRotation() {
        Random random = new Random();
        return Rotation.NONE;
    }
}
