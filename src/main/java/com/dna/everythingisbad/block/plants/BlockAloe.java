package com.dna.everythingisbad.block.plants;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.block.IModBlockBase;
import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockAloe extends BlockDeadBush implements IModBlockBase {
    public BlockAloe(String name){
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        setSoundType(SoundType.PLANT);
        addBlockToRegistry();
        addItemToRegistry();

        // This isn't redundant!!! It doesn't extend BlockBase >:C
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
    }

    @Override
    public void addItemToRegistry() {
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void addBlockToRegistry() {
        ModBlocks.BLOCKS.add(this);
    }

    @Override
    public void registerModels()
    {
        Main.proxy.registerModel(Item.getItemFromBlock(this),0);
    }
    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        Random rand = new Random();
        if (!worldIn.isRemote)
        {
            if (stack.getItem() == Items.SHEARS){
                player.addStat(StatList.getBlockStats(this));
                spawnAsEntity(worldIn, pos, new ItemStack(ModBlocks.ALOE_BLOCK, 1, 0));
            }else{
                player.addStat(StatList.getBlockStats(this));
                spawnAsEntity(worldIn, pos, new ItemStack(ModItems.ALOE_LEAF_ITEM, 1+rand.nextInt(2), 0));
            }

        }
        else
        {
            //super.harvestBlock(worldIn, player, pos, state, te, stack);
        }
    }
    @Override
    public java.util.List<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune)
    {
        return java.util.Arrays.asList(new ItemStack(ModBlocks.ALOE_BLOCK));
    }
}
