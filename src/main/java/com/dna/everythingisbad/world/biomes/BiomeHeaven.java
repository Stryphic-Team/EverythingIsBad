package com.dna.everythingisbad.world.biomes;

import com.dna.everythingisbad.entity.EntityJesus;
import com.google.common.collect.Lists;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;

import java.util.List;

public class BiomeHeaven extends Biome {
    protected List<Biome.SpawnListEntry> jesusList = Lists.<Biome.SpawnListEntry>newArrayList();

    public BiomeHeaven() {
        super(new BiomeProperties("Heaven").setRainDisabled());
        this.jesusList.add(new Biome.SpawnListEntry(EntityJesus.class,420,1,5));
    }

    //@Override
    //public List<SpawnListEntry> getSpawnableList(EnumCreatureType creatureType) {
    //    return this.jesusList;
    //}
}
