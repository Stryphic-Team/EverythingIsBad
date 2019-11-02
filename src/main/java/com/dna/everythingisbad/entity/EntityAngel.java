package com.dna.everythingisbad.entity;

import com.dna.everythingisbad.init.ModLootTables;
import com.dna.everythingisbad.init.ModSoundEvents;
import com.dna.everythingisbad.utils.RandomUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityAngel extends EntityBat {
    public EntityAngel(World worldIn) {
        super(worldIn);
        this.setAIMoveSpeed(0.1f);
    }

    @Override
    public boolean getCanSpawnHere() {
        IBlockState iblockstate = this.world.getBlockState((new BlockPos(this)).down());
        if (RandomUtils.fromRangeI(0,1000)>995 && iblockstate.canEntitySpawn(this)){
            return true;
        }
        return false;
    }

    @Override
    public ResourceLocation getLootTable()
    {

        return ModLootTables.ENTITY_ANGEL_LOOT;
    }

    @Override
    public SoundEvent getAmbientSound()
    {
        int hoo = this.rand.nextInt(3);
        return ModSoundEvents.SOUND_EVENT_ANGEL_AMBIENT[hoo];
    }
    @Override
    public SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        int har = this.rand.nextInt(2);
        return ModSoundEvents.SOUND_EVENT_ANGEL_HURT[har];
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSoundEvents.SOUND_EVENT_ANGEL_DIE;
    }

    @Override
    protected float getSoundVolume() {
        return 0.5f;
    }

    @Override
    protected float getSoundPitch() {
        return 1f;
    }
}
