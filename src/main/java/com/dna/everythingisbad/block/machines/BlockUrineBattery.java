package com.dna.everythingisbad.block.machines;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.gui.GuiHandler;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.tile.TileDeviceBase;
import com.dna.everythingisbad.tile.storage.TileUrineBattery;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
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
        //super.getDrops(drops, world, pos, state, fortune);
        if(tileDeviceBase != null){
            ItemStack itemStack = new ItemStack(ModBlocks.URINE_BATTERY,1);
            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.setInteger("energy_stored",tileDeviceBase.getEnergyHandler().getEnergyStored());
            itemStack.setTagCompound(tagCompound);
            drops.add(itemStack);
        }

    }

    @Override
    public void addItemToRegistry() {
        ItemBlock itemBlock = new ItemBlockDevice(this,new TileUrineBattery().getEnergyHandler().getEnergyStored());
        itemBlock.setRegistryName(this.name);
        itemBlock.setUnlocalizedName(CommonUtils.createUnlocalizedName(this.name));
        itemBlock.setMaxStackSize(1);

        ModItems.ITEMS.add(itemBlock);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if(tileEntity instanceof TileDeviceBase){
            this.tileDeviceBase = (TileDeviceBase)tileEntity;
        }
        super.breakBlock(worldIn, pos, state);
    }
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileUrineBattery();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if(worldIn.getTileEntity(pos) instanceof TileDeviceBase){
            TileDeviceBase tileEntity = (TileDeviceBase) worldIn.getTileEntity(pos);
            NBTTagCompound tag = stack.getTagCompound();
            if(tag != null){
                tileEntity.getEnergyHandler().setEnergyStorage(tag.getInteger("energy_stored"));
            }


        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        }
        TileEntity te = worldIn.getTileEntity(pos);
        if (!(te instanceof TileUrineBattery)) {
            return false;
        }

        playerIn.openGui(Main.instance, GuiHandler.GUI_URINE_BATTERY, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
