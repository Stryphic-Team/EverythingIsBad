package com.dna.everythingisbad.world.dimension;

import com.dna.everythingisbad.init.ModBiomes;
import com.dna.everythingisbad.init.ModDimensions;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;

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

    @Override
    protected void init() {
        this.hasSkyLight = true;
    }

    @Override
    public boolean isDaytime() {
        return true;
    }

    @Override
    public float getSunBrightness(float par1) {
        return 1.0f;
    }

    @Override
    public Vec3d getSkyColor(Entity cameraEntity, float partialTicks) {
        return new Vec3d(1,2.5,2.5);
    }
}
