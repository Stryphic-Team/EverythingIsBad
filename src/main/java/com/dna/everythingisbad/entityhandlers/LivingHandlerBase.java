package com.dna.everythingisbad.entityhandlers;

import com.dna.everythingisbad.utils.RandomUtils;
import com.dna.everythingisbad.utils.handlers.CommonEventHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import scala.util.Random;

public abstract class LivingHandlerBase {
    protected Random random = RandomUtils.random;
    public LivingHandlerBase() {
        CommonEventHandler.LIVING_HANDLERS.add(this);
    }

    //Called once per tick
    public void livingTick(EntityLivingBase livingBase) {}
    //Called when a living entity died
    public void livingDied(EntityLivingBase entity){}

    public void livingDamage(EntityLivingBase entityLiving, DamageSource source, float amount) {}
}
