package com.dna.everythingisbad.init;

import com.dna.everythingisbad.client.RenderStupidTNT;
import com.dna.everythingisbad.entity.EntityStupidTNT;
import com.dna.everythingisbad.utils.EntityPrototype;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

public class ModEntities {


    public static ArrayList<EntityPrototype> ENTITIES = new ArrayList<EntityPrototype>();

    public static final EntityPrototype ENTITY_STUPID_TNT = new EntityPrototype("stupid_tnt","blocks/stupid_tnt",EntityStupidTNT.class);

    /**
     * Registers the entities that are registered above
     */
    public static void init(){
        for(EntityPrototype e:ENTITIES){
            e.register();
        }
    }

    /**
     * For registering Renderers
     */
    @SideOnly(Side.CLIENT)
    public static void initRenderer(){
        RenderingRegistry.registerEntityRenderingHandler(EntityStupidTNT.class, RenderStupidTNT::new);
    }

}
