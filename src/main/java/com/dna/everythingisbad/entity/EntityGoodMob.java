package com.dna.everythingisbad.entity;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;

public class EntityGoodMob extends EntityZombie {
    public EntityGoodMob(World worldIn) {
        super(worldIn);
        setAIMoveSpeed(0.7394875349875349f);
    }
}
