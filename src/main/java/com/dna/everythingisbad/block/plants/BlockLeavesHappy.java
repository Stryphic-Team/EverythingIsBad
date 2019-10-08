package com.dna.everythingisbad.block.plants;

import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.RandomUtils;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

public class BlockLeavesHappy extends BlockLeavesBase {
    public BlockLeavesHappy(String name)
    {
        super(name);
        setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));

        this.setTickRandomly(true);

        this.setHardness(0.2F);
        this.setLightOpacity(1);
        this.setSoundType(SoundType.PLANT);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        if(!((Boolean)state.getValue(DECAYABLE)).booleanValue()) i |= 2;
        if(!((Boolean)state.getValue(CHECK_DECAY)).booleanValue()) i|= 4;
        return i;
    }
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {CHECK_DECAY, DECAYABLE});
    }

    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return BlockPlanks.EnumType.OAK;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor() {
        return ColorizerFoliage.getFoliageColorBasic();
    }

    // TODO Figure out how to make it show up green, based on biome
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public int getSaplingDropChance(IBlockState state)
    {
        return 2;
    }


    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        if (RandomUtils.percentChance(50)){

            drops.add(new ItemStack(ModBlocks.SAPLING_HAPPY_BLOCK,1));
        }else{
            drops.add(new ItemStack(ModItems.DEVILS_CABBAGE_ITEM,1));
        }

    }

    @Nonnull
    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return null;
    }
}
