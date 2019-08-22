package com.dna.everythingisbad.init;

import com.dna.everythingisbad.reference.Reference;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class ModLootTables {
    public static ArrayList<ResourceLocation> LOOT_TABLES = new ArrayList<ResourceLocation>();
    public static ResourceLocation ENTITY_JESUS_LOOT = register(Reference.MOD_ID,"entities/jesus");


    public static ResourceLocation register(String id,String name)
    {
        if (LOOT_TABLES.add(new ResourceLocation(id,name)))
        {
            return new ResourceLocation(id,name);
        }
        else
        {
            throw new IllegalArgumentException(id + " is already a registered built-in loot table");
        }
    }

}
