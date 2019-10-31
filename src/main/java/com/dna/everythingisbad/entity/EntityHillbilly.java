package com.dna.everythingisbad.entity;

import com.dna.everythingisbad.init.ModItems;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityHillbilly extends EntityZombie {
    public EntityHillbilly(World worldIn) {
        super(worldIn);
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata =  super.onInitialSpawn(difficulty, livingdata);
        // Gives the hillbillies their gear
        this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ModItems.HILLBILLY_HAT_ITEM));
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.BANJO_ITEM));

        return livingdata;
    }
}
