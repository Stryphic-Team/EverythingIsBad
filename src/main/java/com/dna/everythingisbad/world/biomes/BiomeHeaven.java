package com.dna.everythingisbad.world.biomes;

import net.minecraft.world.biome.Biome;

public class BiomeHeaven extends Biome {
    public BiomeHeaven() {
        super(new BiomeProperties("Heaven").setRainDisabled());
    }
}
