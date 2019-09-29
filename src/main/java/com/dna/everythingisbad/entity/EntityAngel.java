package com.dna.everythingisbad.entity;

import com.dna.everythingisbad.init.ModLootTables;
import com.dna.everythingisbad.utils.RandomUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAngel extends EntityBat {
    public EntityAngel(World worldIn) {
        super(worldIn);
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
}
