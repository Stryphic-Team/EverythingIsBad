package com.dna.everythingisbad.world.dimension;

import com.dna.everythingisbad.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.structure.MapGenStructure;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ChunkGeneratorHeaven implements IChunkGenerator {

    private final Map<String, MapGenStructure> structureGenerators = new HashMap<String, MapGenStructure>();

    // World gen stuff idk
    private NoiseGeneratorOctaves noiseAssShit; // perlin noise 1
    private NoiseGeneratorOctaves lperlinNoise1;
    private NoiseGeneratorOctaves lperlinNoise2;
    public NoiseGeneratorOctaves scaleNoise;
    public NoiseGeneratorOctaves depthNoise;

    private double[] someShit; // is the buffer used by the primer
    double[] pnr;
    double[] ar;
    double[] br;
    double[] noiseData4;
    double[] dr;

    private final World world;
    private final Random random;
    public ChunkGeneratorHeaven(World world) {
        this.world = world;
        this.random = new Random();
        world.setSeaLevel(74);
        this.noiseAssShit = new NoiseGeneratorOctaves(this.random,8);
        this.lperlinNoise1 = new NoiseGeneratorOctaves(this.random, 16);
        this.lperlinNoise2 = new NoiseGeneratorOctaves(this.random, 16);
        this.scaleNoise = new NoiseGeneratorOctaves(this.random, 10);
        this.depthNoise = new NoiseGeneratorOctaves(this.random, 16);

    }

    @Override
    public Chunk generateChunk(int x, int z) {
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.someShit = this.getHeights(this.someShit, x * 4, 0, z * 4, 5, 17, 5);
//
//        for (int i = 68; i < 71; ++i)
//        {
//
//            IBlockState iblockstate = ModBlocks.CLOUD_BLOCK.getDefaultState();
//
//            if (iblockstate != null)
//            {
//                for (int j = 0; j < 16; ++j)
//                {
//                    for (int k = 0; k < 16; ++k)
//                    {
//                        int arbitraryHeight = 14;
//                        if (someShit[(arbitraryHeight*16*16)+(k*16)+j] > 0.0d){
//                            chunkprimer.setBlockState(j, i, k, iblockstate);
//                        }
//                    }
//                }
//            }
//        }
        int i = 4;
        int j = this.world.getSeaLevel() / 2 + 1;
        int k = 5;
        int l = 17;
        int i1 = 5;
        //this.someShit = this.getHeights(this.buffer, p_185936_1_ * 4, 0, p_185936_2_ * 4, 5, 17, 5);

        for (int j1 = 0; j1 < 4; ++j1)
        {
            for (int k1 = 0; k1 < 4; ++k1)
            {
                for (int l1 = 0; l1 < 16; ++l1)
                {
                    double d0 = 0.125D;
                    double d1 = this.someShit[((j1 + 0) * 5 + k1 + 0) * 17 + l1 + 0];
                    double d2 = this.someShit[((j1 + 0) * 5 + k1 + 1) * 17 + l1 + 0];
                    double d3 = this.someShit[((j1 + 1) * 5 + k1 + 0) * 17 + l1 + 0];
                    double d4 = this.someShit[((j1 + 1) * 5 + k1 + 1) * 17 + l1 + 0];
                    double d5 = (this.someShit[((j1 + 0) * 5 + k1 + 0) * 17 + l1 + 1] - d1) * 0.125D;
                    double d6 = (this.someShit[((j1 + 0) * 5 + k1 + 1) * 17 + l1 + 1] - d2) * 0.125D;
                    double d7 = (this.someShit[((j1 + 1) * 5 + k1 + 0) * 17 + l1 + 1] - d3) * 0.125D;
                    double d8 = (this.someShit[((j1 + 1) * 5 + k1 + 1) * 17 + l1 + 1] - d4) * 0.125D;

                    for (int i2 = 0; i2 < 8; ++i2)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * 0.25D;
                        double d13 = (d4 - d2) * 0.25D;

                        for (int j2 = 0; j2 < 4; ++j2)
                        {
                            double d14 = 0.25D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * 0.25D;

                            for (int k2 = 0; k2 < 4; ++k2)
                            {
                                IBlockState iblockstate = null;

                                if (l1 * 8 + i2 < j)
                                {
                                    iblockstate = Blocks.AIR.getDefaultState();
                                }

                                if (d15 > 0.0D)
                                {
                                    iblockstate = ModBlocks.CLOUD_BLOCK.getDefaultState();
                                }

                                int l2 = j2 + j1 * 4;
                                int i3 = i2 + l1 * 8;
                                int j3 = k2 + k1 * 4;
                                chunkprimer.setBlockState(l2, i3, j3, iblockstate);
                                d15 += d16;
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }

        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);

        Biome[] abiome = this.world.getBiomeProvider().getBiomesForGeneration((Biome[])null, x * 16, z * 16, 16, 16);
        byte[] abyte = chunk.getBiomeArray();

        for (int hoho = 0; hoho < abyte.length; ++hoho)
        {
            abyte[hoho] = (byte)Biome.getIdForBiome(abiome[hoho]);
        }
        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public void populate(int x, int z) {
        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.world, this.random, x, z, false);
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
        for (MapGenStructure mapgenstructure : this.structureGenerators.values())
        {
            mapgenstructure.generate(this.world, x, z, (ChunkPrimer)null);
        }
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }

    private double[] getHeights(double[] p_185938_1_, int p_185938_2_, int p_185938_3_, int p_185938_4_, int p_185938_5_, int p_185938_6_, int p_185938_7_)
    {
        if (p_185938_1_ == null)
        {
            p_185938_1_ = new double[p_185938_5_ * p_185938_6_ * p_185938_7_];
        }

        net.minecraftforge.event.terraingen.ChunkGeneratorEvent.InitNoiseField event = new net.minecraftforge.event.terraingen.ChunkGeneratorEvent.InitNoiseField(this, p_185938_1_, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == net.minecraftforge.fml.common.eventhandler.Event.Result.DENY) return event.getNoisefield();

        double d0 = 684.412D;
        double d1 = 2053.236D;
        this.noiseData4 = this.scaleNoise.generateNoiseOctaves(this.noiseData4, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, 1, p_185938_7_, 1.0D, 0.0D, 1.0D);
        this.dr = this.depthNoise.generateNoiseOctaves(this.dr, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, 1, p_185938_7_, 100.0D, 0.0D, 100.0D);
        this.pnr = this.noiseAssShit.generateNoiseOctaves(this.pnr, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_, 8.555150000000001D, 34.2206D, 8.555150000000001D);
        this.ar = this.lperlinNoise1.generateNoiseOctaves(this.ar, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_, 684.412D, 2053.236D, 684.412D);
        this.br = this.lperlinNoise2.generateNoiseOctaves(this.br, p_185938_2_, p_185938_3_, p_185938_4_, p_185938_5_, p_185938_6_, p_185938_7_, 684.412D, 2053.236D, 684.412D);
        int i = 0;
        double[] adouble = new double[p_185938_6_];

        for (int j = 0; j < p_185938_6_; ++j)
        {
            adouble[j] = Math.cos((double)j * Math.PI * 6.0D / (double)p_185938_6_) * 2.0D;
            double d2 = (double)j;

            if (j > p_185938_6_ / 2)
            {
                d2 = (double)(p_185938_6_ - 1 - j);
            }

            if (d2 < 4.0D)
            {
                d2 = 4.0D - d2;
                adouble[j] -= d2 * d2 * d2 * 10.0D;
            }
        }

        for (int l = 0; l < p_185938_5_; ++l)
        {
            for (int i1 = 0; i1 < p_185938_7_; ++i1)
            {
                double d3 = 0.0D;

                for (int k = 0; k < p_185938_6_; ++k)
                {
                    double d4 = adouble[k];
                    double d5 = this.ar[i] / 512.0D;
                    double d6 = this.br[i] / 512.0D;
                    double d7 = (this.pnr[i] / 10.0D + 1.0D) / 2.0D;
                    double d8;

                    if (d7 < 0.0D)
                    {
                        d8 = d5;
                    }
                    else if (d7 > 1.0D)
                    {
                        d8 = d6;
                    }
                    else
                    {
                        d8 = d5 + (d6 - d5) * d7;
                    }

                    d8 = d8 - d4;

                    if (k > p_185938_6_ - 4)
                    {
                        double d9 = (double)((float)(k - (p_185938_6_ - 4)) / 3.0F);
                        d8 = d8 * (1.0D - d9) + -10.0D * d9;
                    }

                    if ((double)k < 0.0D)
                    {
                        double d10 = (0.0D - (double)k) / 4.0D;
                        d10 = MathHelper.clamp(d10, 0.0D, 1.0D);
                        d8 = d8 * (1.0D - d10) + -10.0D * d10;
                    }

                    p_185938_1_[i] = d8;
                    ++i;
                }
            }
        }

        return p_185938_1_;
    }
}
