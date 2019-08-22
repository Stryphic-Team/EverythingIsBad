package com.dna.everythingisbad.init;

import com.dna.everythingisbad.client.*;
import com.dna.everythingisbad.entity.*;
import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.utils.prototypes.EntityPrototype;
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
    public static final EntityPrototype ENTITY_JESUS = new EntityPrototype("jesus", EntityJesus.class);
    public static final EntityPrototype ENTITY_SATAN = new EntityPrototype("satan", EntitySatan.class);
    public static final EntityPrototype ENTITY_GOOD_MOB = new EntityPrototype("good_mob", EntityGoodMob.class);

    /**
     * Registers the entities that are registered above
     */
    public static void init(){
        /*
         * Registers the entities
         */
        for(EntityPrototype e:ENTITIES){
            e.register();
        }
        /*
         * adds spawns for all biomes
         */
        for(Biome biome:Biome.REGISTRY){
            EntityRegistry.addSpawn(EntityStupidSkeleton.class,10,5,200, EnumCreatureType.MONSTER,biome);
            EntityRegistry.addSpawn(EntityJesus.class,1,5,300, EnumCreatureType.MONSTER,biome);
            EntityRegistry.addSpawn(EntitySatan.class,1,5,300, EnumCreatureType.MONSTER,biome);
        }

        EntityRegistry.registerEgg(new ResourceLocation(Reference.MOD_ID,"stupid_skeleton"),0xaefc5f,0xb70101);
        EntityRegistry.registerEgg(new ResourceLocation(Reference.MOD_ID,"jesus"),0xf4ec50,0xf45050);
        EntityRegistry.registerEgg(new ResourceLocation(Reference.MOD_ID,"satan"),0x212020,0xf43a29);
        EntityRegistry.registerEgg(new ResourceLocation(Reference.MOD_ID,"good_mob"),0xffffff,0x000000);

    }

    /**
     * For registering Renderers
     */
    @SideOnly(Side.CLIENT)
    public static void initRenderer(){
        RenderingRegistry.registerEntityRenderingHandler(EntityStupidTNT.class, RenderStupidTNT::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityStupidSkeleton.class, RenderStupidSkeleton::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityJesus.class, RenderJesus::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySatan.class, RenderSatan::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGoodMob.class, RenderGoodMob::new);
    }

}
