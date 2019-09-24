package com.dna.everythingisbad.block.machines;

import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.tile.TileDeviceBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockUrineBattery extends BlockGeneratorBase {
    TileDeviceBase tileDeviceBase;
    public BlockUrineBattery(String name) {
        super(name);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        super.getDrops(drops, world, pos, state, fortune);
        if(tileDeviceBase != null){
            ItemStack itemStack = new ItemStack(ModBlocks.URINE_BATTERY,1);
            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.setInteger("energy_stored",1000);
            itemStack.writeToNBT(tagCompound);
            drops.add(itemStack);
        }

    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if(tileEntity instanceof TileDeviceBase){
            this.tileDeviceBase = (TileDeviceBase)tileEntity;
        }
        super.breakBlock(worldIn, pos, state);
    }
}
