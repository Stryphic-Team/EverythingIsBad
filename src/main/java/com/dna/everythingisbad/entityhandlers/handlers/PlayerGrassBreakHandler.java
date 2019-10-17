package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.RandomUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;

public class PlayerGrassBreakHandler extends PlayerHandlerBase {
    @Override
    public void playerBreakBlock(EntityPlayer player, IBlockState state) {
        super.playerBreakBlock(player, state);
        if (!player.isCreative())
            if (state.getBlock() == Blocks.TALLGRASS){
                // 9% drop chance from tall grass
                if (RandomUtils.percentChance(9)){
                    player.dropItem(ModItems.TOBACCO_SEEDS_ITEM,1);
                }
            }
    }
}
