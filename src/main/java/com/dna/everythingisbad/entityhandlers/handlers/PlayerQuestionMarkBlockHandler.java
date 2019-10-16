package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModLootTables;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;

import java.util.Random;

public class PlayerQuestionMarkBlockHandler extends PlayerHandlerBase {
    @Override
    public void playerTick(EntityPlayer player) {
        super.playerTick(player);
        if (player instanceof EntityPlayerMP){
            Random rand = new Random();
            World worldIn = player.getEntityWorld();

            BlockPos player_pos = player.getPosition();
            int player_x = player_pos.getX();
            int player_y = player_pos.getY();
            int player_z = player_pos.getZ();

            BlockPos pos_plus_2 = new BlockPos(player_x,player_y+2,player_z);
            Block blockabove = worldIn.getBlockState(pos_plus_2).getBlock();

            if (blockabove == ModBlocks.QUESTION_MARK_BLOCK && player.motionY > 0){
                // Sets it to the empty block
                worldIn.setBlockToAir(pos_plus_2);
                worldIn.setBlockState(pos_plus_2,ModBlocks.EMPTY_BLOCK.getBlockState().getBaseState());

                // Gives you the item(s) and shit
                LootTable loottable = worldIn.getLootTableManager().getLootTableFromLocation(ModLootTables.QUESTION_MARK_BLOCK_LOOT);
                LootContext.Builder lootcontext$builder = (new LootContext.Builder((WorldServer)worldIn));
                for (ItemStack lutestack : loottable.generateLootForPools(rand,lootcontext$builder.build())){
                    player.dropItem(lutestack,false);
                }
            }
        }
    }
}
