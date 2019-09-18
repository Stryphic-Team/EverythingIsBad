package com.dna.everythingisbad.entity;

import com.dna.everythingisbad.init.ModItems;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityPoliceOfficer extends EntityZombie {
    public EntityPoliceOfficer(World worldIn) {
        super(worldIn);
    }

    @Override
    public boolean shouldBurnInDay()
    {
        return false;
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata =  super.onInitialSpawn(difficulty, livingdata);
        this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModItems.COWBOY_HAT_ITEM));
        return livingdata;
    }

    @Override
    public boolean getCanSpawnHere() {
        return false;
    }
}
