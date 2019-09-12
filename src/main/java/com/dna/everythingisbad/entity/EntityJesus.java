package com.dna.everythingisbad.entity;


import com.dna.everythingisbad.init.ModLootTables;
import com.dna.everythingisbad.init.ModSoundEvents;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
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
}