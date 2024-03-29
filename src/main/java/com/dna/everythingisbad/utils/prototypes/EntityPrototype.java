package com.dna.everythingisbad.utils.prototypes;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModEntities;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;


/**
 * Class for registering entities
 */
public class EntityPrototype {

    private ResourceLocation texture;
    private Class<? extends Entity> entityClass;
    private int id;
    private static int ID_COUNT = 1;
    private String name;
    private String modid;


    /**
     * Assigns the parameters to private variables and creates the ResourceLocation
     * @param name
     * @param entityClass
     */
    public EntityPrototype(String name, Class<? extends Entity> entityClass){
        this.modid = Reference.MOD_ID;
        this.name = name;
        this.entityClass = entityClass;
        this.id = ID_COUNT;
        ID_COUNT++;
        ModEntities.ENTITIES.add(this);
    }
    public EntityPrototype(String name,String modid, Class<? extends Entity> entityClass){
        this.modid = modid;
        this.name = name;
        this.entityClass = entityClass;
        this.id = ID_COUNT;
        ID_COUNT++;
        ModEntities.ENTITIES.add(this);
    }
    /**
     * Registers a new entity with a texture applied
     */
    public void register(){
        EntityRegistry.registerModEntity(
                new ResourceLocation(this.modid,name),
                entityClass,
                name,
                id,
                Main.instance,
                64,
                3,
                true
        );
    }

    /**
     * returns the resource location of the entity texture
     * @return ResourceLocation
     */
    public ResourceLocation getTexture() {
        return texture;
    }

    /**
     * returns the class of type Entity
     * @return Class of Type Entity
     */
    public Class<? extends Entity> getEntityClass() {
        return entityClass;
    }

    /**
     * returns the registry id of the entity
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * returns the name of the entity
     * @return String
     */
    public String getName() {
        return name;
    }

}
