package com.dna.everythingisbad.world.dimension;

import com.dna.everythingisbad.init.ModBiomes;
import com.dna.everythingisbad.init.ModDimensions;
import net.minecraft.init.Biomes;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.ChunkGeneratorFlat;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.Mod;

public class HeavenProvider extends WorldProvider {
    public HeavenProvider(){
        this.biomeProvider = new BiomeProviderSingle(ModBiomes.HEAVEN);
    }
    @Override
    public DimensionType getDimensionType() {
        return ModDimensions.HEAVEN;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorHeaven(world);
    }
    @Override
    public boolean canRespawnHere(){
        return false;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    @Override
    public BiomeProvider getBiomeProvider() {
        return new BiomeProviderSingle(ModBiomes.HEAVEN);
    }
}
