package com.dna.everythingisbad.init;

import com.dna.everythingisbad.entity.EntityStupidTNT;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;

import java.util.ArrayList;
import java.util.List;

public class ModEntities {


    public static final EntityEntry STUPID_TNT_ENTITY = new EntityEntry(EntityStupidTNT.class, Reference.MOD_ID + ":stupid_tnt_entity");
    public static final EntityEntry[] ENTITIES = new EntityEntry[]{
            STUPID_TNT_ENTITY
    };
    public static void init(){
        for(EntityEntry e:ENTITIES){
            e.setRegistryName(e.getName());
        }


    }

}
