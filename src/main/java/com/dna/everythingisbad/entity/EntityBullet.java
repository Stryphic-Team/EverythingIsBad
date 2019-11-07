package com.dna.everythingisbad.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

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
            result.entityHit.attackEntityFrom(new EntityDamageSourceIndirect("arrow", this, this.getThrower()), 12f);
        }
        if (!this.world.isRemote){
            if (result.typeOfHit == RayTraceResult.Type.BLOCK){
                BlockPos pozishin = result.getBlockPos();
                Block hitblock = this.world.getBlockState(pozishin).getBlock();

                // If you're hitting a glass block, let it pass thru

                if (hitblock == Blocks.GLASS || hitblock == Blocks.GLASS_PANE || hitblock == Blocks.STAINED_GLASS || hitblock == Blocks.STAINED_GLASS_PANE){
                    if (this.getServer() != null){
                        WorldServer woerald = this.getServer().getWorld(this.dimension);
                        if(woerald.getGameRules().getBoolean("mobGriefing")) {
                            woerald.setBlockToAir(pozishin);
                        }
                    }
                    this.world.playSound(this.posX,this.posY,this.posZ, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS,1,1,true);

                // Else just destroy the bullet entity
                }else{
                    this.world.setEntityState(this, (byte)3);
                    this.setDead();
                }

            // If it's not a block you're hitting, destroy bullet anyways
            }else{
                this.world.setEntityState(this, (byte)3);
                this.setDead();
            }
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.ticksExisted > 200){
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }
}
