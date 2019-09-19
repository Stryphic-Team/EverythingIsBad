package com.dna.everythingisbad.entity;

import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModSoundEvents;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class EntityPoliceOfficer extends EntityZombie {
    static Random random = new Random();
    public EntityPoliceOfficer(World worldIn) {
        super(worldIn);
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
        return ModSoundEvents.SOUND_EVENT_POLICE_AMBIENT[hoo];
    }
    @Override
    public SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        int har = random.nextInt(5);
        return ModSoundEvents.SOUND_EVENT_POLICE_AMBIENT[har];
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata =  super.onInitialSpawn(difficulty, livingdata);
        // Gives the police their gear
        this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModItems.COWBOY_HAT_ITEM));
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.POLICE_GUN_ITEM));

        // Prevents the police from dropping their gun upon death
        this.inventoryHandsDropChances[EntityEquipmentSlot.MAINHAND.getIndex()] = 0;
        return livingdata;
    }

    @Override
    public boolean getCanSpawnHere() {
        return false;
    }
}
