package com.dna.everythingisbad.init;

import com.dna.everythingisbad.client.RenderStupidSkeleton;
import com.dna.everythingisbad.client.RenderStupidTNT;
import com.dna.everythingisbad.entity.EntityStupidSkeleton;
import com.dna.everythingisbad.entity.EntityStupidTNT;
import com.dna.everythingisbad.utils.EntityPrototype;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

public class ModEntities {


    public static ArrayList<EntityPrototype> ENTITIES = new ArrayList<EntityPrototype>();

    public static final EntityPrototype ENTITY_STUPID_TNT = new EntityPrototype("stupid_tnt_primed",EntityStupidTNT.class);
    public static final EntityPrototype ENTITY_STUPID_SKELETON = new EntityPrototype("stupid_skeleton", EntityStupidSkeleton.class);


    /**
     * Registers the entities that are registered above
     */
    public static void init(){
        for(EntityPrototype e:ENTITIES){
            e.register();
        }
        for(Biome biome:Biome.REGISTRY){
            EntityRegistry.addSpawn(EntityStupidSkeleton.class,10,5,200, EnumCreatureType.MONSTER,biome);
        }

        EntityRegistry.registerEgg(new ResourceLocation("stupid_skeleton"),4,4);

//        EntityRegistry.removeSpawn("");
    }

    /**
     * For registering Renderers
     */
    @SideOnly(Side.CLIENT)
    public static void initRenderer(){
        RenderingRegistry.registerEntityRenderingHandler(EntityStupidTNT.class, RenderStupidTNT::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityStupidSkeleton.class, RenderStupidSkeleton::new);

    }

}
