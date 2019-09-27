package com.dna.everythingisbad.entity;


import com.dna.everythingisbad.ai.EntityAINearestNonChristian;
import com.dna.everythingisbad.init.ModLootTables;
import com.dna.everythingisbad.init.ModSoundEvents;
import com.dna.everythingisbad.init.Religion;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import java.util.Random;


public class EntityJesus extends EntityZombie{

    static Random random = new Random();

    public EntityJesus(World worldIn) {
        super(worldIn);
        setAIMoveSpeed(2f);

    }
    @Override
    public ResourceLocation getLootTable()
    {

        return ModLootTables.ENTITY_JESUS_LOOT;
    }
    @Override
    public boolean shouldBurnInDay()
    {
        return false;
    }
    @Override
    public SoundEvent getAmbientSound()
    {
        int hoo = random.nextInt(5);
        return ModSoundEvents.SOUND_EVENT_JESUS_AMBIENT[hoo];
    }
    @Override
    public SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        int har = random.nextInt(5);
        return ModSoundEvents.SOUND_EVENT_JESUS_AMBIENT[har];
    }

    @Override
    public boolean getCanSpawnHere() {
        if (random.nextFloat() < 0.01){

            // All of this is so that we skip the EntityMob's light level requirement
            IBlockState iblockstate = this.world.getBlockState((new BlockPos(this)).down());
            return this.world.getDifficulty() != EnumDifficulty.PEACEFUL; //&&
                    //this.getBlockPathWeight(new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ)) >= 0.0F &&
                    //iblockstate.canEntitySpawn(this);

        }else{
            return false;
        }
    }
    @Override
    protected void applyEntityAI() {
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        //this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntitySatan.class}));
        this.targetTasks.addTask(2, new EntityAINearestNonChristian(this, EntityPlayer.class, true, Religion.CHRISTIAN));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntitySatan.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        //this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    }
}