package com.dna.everythingisbad.entity;

import com.dna.everythingisbad.utils.IHasModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.world.World;

public class EntityStupidTNT extends EntityTNTPrimed implements IHasModel {
    public EntityStupidTNT(World worldIn) {
        super(worldIn);
    }

    public EntityStupidTNT(World worldIn, double x, double y, double z, EntityLivingBase igniter) {
        super(worldIn, x, y, z, igniter);
    }

    @Override
    public void registerModels() {

    }
}
