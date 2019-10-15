package com.dna.everythingisbad.entityhandlers;

import com.dna.everythingisbad.utils.handlers.CommonEventHandler;
import net.minecraft.entity.EntityLivingBase;

public abstract class LivingHandlerBase {
    public LivingHandlerBase() {
        CommonEventHandler.LIVING_HANDLERS.add(this);
    }

    //Called once per tick
    public void livingTick(EntityLivingBase livingBase) {}

}
