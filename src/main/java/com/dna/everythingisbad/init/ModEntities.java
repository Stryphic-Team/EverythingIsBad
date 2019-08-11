package com.dna.everythingisbad.init;

import com.dna.everythingisbad.client.RenderStupidTNT;
import com.dna.everythingisbad.entity.EntityStupidTNT;
import com.dna.everythingisbad.reference.Reference;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
    @SideOnly(Side.CLIENT)
    public static void initRenderer(){
        for(EntityEntry e:ENTITIES){
            RenderingRegistry.registerEntityRenderingHandler(EntityStupidTNT.class, RenderStupidTNT.FACTORY);
        }
    }

}
