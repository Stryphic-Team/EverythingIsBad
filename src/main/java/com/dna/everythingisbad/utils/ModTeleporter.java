package com.dna.everythingisbad.utils;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class ModTeleporter extends Teleporter {
    private final WorldServer world;
    private double x, y, z;

    public ModTeleporter(WorldServer world, double x, double y, double z) {
        super(world);
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void placeInPortal(Entity entityIn, float rotationYaw) {
        entityIn.setPosition(x,y,z);
        entityIn.motionX=0.0f;
        entityIn.motionY=0.0f;
        entityIn.motionZ=0.0f;
    }
}
