package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.LivingHandlerBase;
import com.dna.everythingisbad.init.ModFluids;
import com.dna.everythingisbad.utils.handlers.FluidEventHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;

public class LivingFluidHandler extends LivingHandlerBase {
    @Override
    public void livingTick(EntityLivingBase livingBase) {
        super.livingTick(livingBase);
        if(livingBase instanceof EntityPlayerMP || livingBase instanceof EntityCreature){
            BlockPos playerPos = livingBase.getPosition();
            IBlockState blockAtPlayerPos = livingBase.getEntityWorld().getBlockState(playerPos);
            if(blockAtPlayerPos.getBlock() == ModFluids.DEVILS_PEE.getBlockFluidBase()){
                FluidEventHandler.inDevilsPee(livingBase);
            }
        }
    }
}
