package com.dna.everythingisbad.init;

import com.dna.everythingisbad.client.*;
import com.dna.everythingisbad.entity.*;
import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.utils.prototypes.EntityPrototype;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.*;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
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
    public static final EntityPrototype ENTITY_THREE_HEADED_SHEEP = new EntityPrototype("three_headed_sheep", EntityThreeHeadedSheep.class);
    public static final EntityPrototype ENTITY_POLICE_OFFICER = new EntityPrototype("police_officer", EntityPoliceOfficer.class);

    public static final EntityPrototype ENTITY_BULLET = new EntityPrototype("bullet", EntityBullet.class);
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
            if(!biome.equals(Biomes.MUSHROOM_ISLAND) && !biome.equals(Biomes.MUSHROOM_ISLAND_SHORE)) {
                if (biome.equals(Biomes.HELL)) {
                    EntityRegistry.addSpawn(EntitySatan.class, 1, 1, 10, EnumCreatureType.MONSTER, biome);
//            }else if (biome == ModBiomes.HEAVEN){
//                EntityRegistry.addSpawn(EntityJesus.class,100,5,10, EnumCreatureType.MONSTER,biome);
                } else {
                    EntityRegistry.addSpawn(EntityStupidSkeleton.class, 10, 1, 10, EnumCreatureType.MONSTER, biome);
                    EntityRegistry.addSpawn(EntityJesus.class, 5, 1, 5, EnumCreatureType.MONSTER, biome);
                }
            }
        }
        for (Biome biome:ForgeRegistries.BIOMES){
            if(biome.equals(ModBiomes.HEAVEN)){
                EntityRegistry.addSpawn(EntityJesus.class,1,1,1, EnumCreatureType.MONSTER,biome);
                EntityRegistry.removeSpawn(EntityZombie.class,EnumCreatureType.MONSTER,biome);
                EntityRegistry.removeSpawn(EntityWitch.class,EnumCreatureType.MONSTER,biome);
                EntityRegistry.removeSpawn(EntitySkeleton.class,EnumCreatureType.MONSTER,biome);
                EntityRegistry.removeSpawn(EntitySpider.class,EnumCreatureType.MONSTER,biome);
                EntityRegistry.removeSpawn(EntityCreeper.class,EnumCreatureType.MONSTER,biome);
                EntityRegistry.removeSpawn(EntityEnderman.class,EnumCreatureType.MONSTER,biome);
                EntityRegistry.removeSpawn(EntityZombieVillager.class,EnumCreatureType.MONSTER,biome);
                EntityRegistry.removeSpawn(EntityStupidSkeleton.class,EnumCreatureType.MONSTER,biome);
            }
        }

        EntityRegistry.registerEgg(new ResourceLocation(Reference.MOD_ID,"stupid_skeleton"),0xabf95e,0x020085);
        EntityRegistry.registerEgg(new ResourceLocation(Reference.MOD_ID,"jesus"),0xead797,0xb73025);
        EntityRegistry.registerEgg(new ResourceLocation(Reference.MOD_ID,"satan"),0xdc2500,0x3b0a03);
        EntityRegistry.registerEgg(new ResourceLocation(Reference.MOD_ID,"good_mob"),0xffffff,0x000000);
        EntityRegistry.registerEgg(new ResourceLocation(Reference.MOD_ID,"three_headed_sheep"),0xffffff,0x000000);
        EntityRegistry.registerEgg(new ResourceLocation(Reference.MOD_ID,"police_officer"),0xc6b87f,0x6a5439);
        //EntityRegistry.registerEgg(new ResourceLocation(Reference.MOD_ID,"bullet"),0,0);
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
        RenderingRegistry.registerEntityRenderingHandler(EntityThreeHeadedSheep.class, RenderThreeHeadedSheep::new);
        //RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, RenderModPlayer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPoliceOfficer.class, RenderPoliceOfficer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class,RenderBullet::new);
    }

}
