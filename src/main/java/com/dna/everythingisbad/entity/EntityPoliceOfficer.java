package com.dna.everythingisbad.entity;

import com.dna.everythingisbad.ai.EntityAIPoliceShootGun;
import com.dna.everythingisbad.ai.EntityPoliceBreakDoor;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Random;

public class EntityPoliceOfficer extends EntitySkeleton implements IRangedAttackMob {

    private boolean isBreakDoorsTaskSet;
    private final EntityPoliceBreakDoor breakDoor = new EntityPoliceBreakDoor(this);
    static Random random = new Random();

    public EntityPoliceOfficer(World worldIn) {
        super(worldIn);
        this.setCombatTask();
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
        this.setBreakDoorsAItask(true);

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

    /**
     * Sets or removes EntityAIBreakDoor task
     */
    public void setBreakDoorsAItask(boolean enabled)
    {
        if (this.isBreakDoorsTaskSet != enabled)
        {
            this.isBreakDoorsTaskSet = enabled;
            ((PathNavigateGround)this.getNavigator()).setBreakDoors(enabled);

            if (enabled)
            {
                this.tasks.addTask(3, this.breakDoor);
            }
            else
            {
                this.tasks.removeTask(this.breakDoor);
            }
        }
    }

    public boolean isBreakDoorsTaskSet()
    {
        return this.isBreakDoorsTaskSet;
    }

    @Override
    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIRestrictSun(this));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, false));
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        compound.setBoolean("CanBreakDoors", true);
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return null;
    }
}
