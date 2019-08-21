package com.dna.everythingisbad.entity;


import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;
//TODO make the entity quote bible quotes with tts
public class EntityJesus extends EntityZombie{

    public EntityJesus(World worldIn) {
        super(worldIn);
        setAIMoveSpeed(2f);
    }
}