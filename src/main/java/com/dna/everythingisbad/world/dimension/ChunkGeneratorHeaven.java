package com.dna.everythingisbad.world.dimension;

import com.dna.everythingisbad.init.ModBiomes;
import com.dna.everythingisbad.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorDebug;
import net.minecraft.world.gen.ChunkGeneratorFlat;
import net.minecraft.world.gen.ChunkGeneratorHell;
import net.minecraft.world.gen.IChunkGenerator;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ChunkGeneratorHeaven implements IChunkGenerator {
    private final World world;
    private final Random random;
    public ChunkGeneratorHeaven(World world) {
        this.world = world;
        this.random = new Random();
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        ChunkPrimer chunkprimer = new ChunkPrimer();

        for (int i = 68; i < 73; ++i)
        {
            IBlockState iblockstate = ModBlocks.CLOUD_BLOCK.getDefaultState();

            if (iblockstate != null)
            {
                for (int j = 0; j < 16; ++j)
                {
                    for (int k = 0; k < 16; ++k)
                    {
                        chunkprimer.setBlockState(j, i, k, iblockstate);
                    }
                }
            }
        }

        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);

        Biome[] abiome = this.world.getBiomeProvider().getBiomesForGeneration(null, x * 16, z * 16, 16, 16);
        byte[] abyte = chunk.getBiomeArray();

        for (int l = 0; l < abyte.length; ++l)
        {
            abyte[l] = (byte)Biome.getIdForBiome(abiome[l]);
        }
        chunk.resetRelightChecks();
        return chunk;
    }

    @Override
    public void populate(int x, int z) {

    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        Biome biome = this.world.getBiome(pos);
        return biome.getSpawnableList(creatureType);
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {

    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }
}
