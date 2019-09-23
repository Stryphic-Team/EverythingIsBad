package com.dna.everythingisbad.utils;

import net.minecraft.entity.Entity;
import net.minecraft.world.Explosion;

public class ExplosionUtils {
    public static Explosion createExplosion(Entity entityIn, double x, double y, double z, float strength)
    {
        return newExplosion(entityIn, x, y, z, strength, false, false);
    }

    /**
     * returns a new explosion. Does initiation (at time of writing Explosion is not finished)
     */
    public static Explosion newExplosion(Entity entityIn, double x, double y, double z, float strength, boolean isFlaming, boolean isSmoking)
    {
        Explosion explosion = new Explosion(entityIn.world, entityIn, x, y, z, strength, isFlaming, isSmoking);

        if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(entityIn.world, explosion)) return explosion;
        explosion.doExplosionA();
        explosion.doExplosionB(false);
        return explosion;
    }
}
