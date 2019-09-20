package com.dna.everythingisbad.entity;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.*;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBullet extends EntityThrowable {

    public EntityBullet(World worldin){
        super (worldin);
        this.setNoGravity(true);
    }

    public EntityBullet(World worldin, EntityLivingBase elb){
        super (worldin, elb);
        this.setNoGravity(true);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.entityHit != null && result.entityHit != this.getThrower())
        {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 12f);
        }
        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }
}
