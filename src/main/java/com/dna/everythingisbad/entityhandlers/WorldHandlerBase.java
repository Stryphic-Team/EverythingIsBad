package com.dna.everythingisbad.entityhandlers;

import com.dna.everythingisbad.utils.RandomUtils;
import com.dna.everythingisbad.utils.handlers.CommonEventHandler;
import net.minecraft.world.World;
import scala.util.Random;

public abstract class WorldHandlerBase {
    protected Random random = RandomUtils.random;
    public WorldHandlerBase(){
        CommonEventHandler.WORLD_HANDLERS.add(this);
    }
    public void worldTick(World world){}
}
