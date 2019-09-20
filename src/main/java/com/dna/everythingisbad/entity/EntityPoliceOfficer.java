package com.dna.everythingisbad.entity;

import com.dna.everythingisbad.ai.EntityAIPoliceShootGun;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Random;

public class EntityPoliceOfficer extends EntitySkeleton implements IRangedAttackMob {

    static Random random = new Random();

    public EntityPoliceOfficer(World worldIn) {
        super(worldIn);
    }

    @Override
    public SoundEvent getAmbientSound()
    {
        int hoo = random.nextInt(5);
        return ModSoundEvents.SOUND_EVENT_POLICE_AMBIENT[hoo];
    }
    @Override
    public SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        int har = random.nextInt(5);
        return ModSoundEvents.SOUND_EVENT_POLICE_AMBIENT[har];
    }

    protected SoundEvent getStepSound()
    {
        return SoundEvents.ENTITY_ZOMBIE_STEP;
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata =  super.onInitialSpawn(difficulty, livingdata);
        // Gives the police their gear
        this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModItems.COWBOY_HAT_ITEM));
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.POLICE_GUN_ITEM));
        //this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.BOW));

        // Prevents the police from dropping their gun upon death
        this.inventoryHandsDropChances[EntityEquipmentSlot.MAINHAND.getIndex()] = 0;

        this.setCombatTask();
        return livingdata;
    }

    @Override
    public boolean getCanSpawnHere() {
        return false;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
        EntityBullet bulet = new EntityBullet(this.world,this);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - bulet.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
        bulet.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.world.getDifficulty().getDifficultyId() * 4));
        this.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(bulet);
    }

    @Override
    public void setCombatTask() {
        super.setCombatTask();
        EntityAIPoliceShootGun doodly = new EntityAIPoliceShootGun(this, 1.0D, 20, 15.0F);
        this.tasks.addTask(4,doodly);
    }
}
