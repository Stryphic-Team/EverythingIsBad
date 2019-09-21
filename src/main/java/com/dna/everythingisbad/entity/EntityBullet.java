package com.dna.everythingisbad.entity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

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
        if (result.typeOfHit == RayTraceResult.Type.BLOCK){
            BlockPos pozishin = result.getBlockPos();
            Block hitblock = this.world.getBlockState(pozishin).getBlock();
            if (hitblock == Blocks.GLASS || hitblock == Blocks.GLASS_PANE || hitblock == Blocks.STAINED_GLASS || hitblock == Blocks.STAINED_GLASS_PANE){
                if (this.getServer() != null){
                    WorldServer woerald = this.getServer().getWorld(this.dimension);
                    woerald.setBlockToAir(pozishin);
                }
                this.world.playSound(this.posX,this.posY,this.posZ, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS,1,1,true);
            }
        }
        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
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
