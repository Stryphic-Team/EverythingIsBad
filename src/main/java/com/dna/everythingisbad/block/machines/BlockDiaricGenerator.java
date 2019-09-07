package com.dna.everythingisbad.block.machines;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModFluids;
import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.tile.TileDeviceBase;
import com.dna.everythingisbad.tile.TileDiaricGenerator;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;

import javax.annotation.Nullable;

public class BlockDiaricGenerator extends BlockGeneratorBase {
    TileDeviceBase tileDeviceBase;
    public BlockDiaricGenerator(String name) {
        super(name);
    }
    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        tileDeviceBase = new TileDiaricGenerator();
        return tileDeviceBase;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        }
        TileEntity te = worldIn.getTileEntity(pos);
        if (!(te instanceof TileDiaricGenerator)) {
            return false;
        }
        Fluid diariaFluid = ModFluids.DIARIA.getFluid();
        ItemStack diariaBucket = FluidUtil.getFilledBucket(new FluidStack(diariaFluid,1));
        if(playerIn.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem().equals(diariaBucket.getItem())){
            FluidTank fluidTank = this.tileDeviceBase.getFluidHandler().getFluidTank();
            if(fluidTank.getFluidAmount() + 1000 <= fluidTank.getCapacity()){
                this.tileDeviceBase.getFluidHandler().fill(new FluidStack(diariaFluid,1000),true);
                ItemStack emptyBucket = new ItemStack(Items.BUCKET);
                if(!playerIn.isCreative()) {
                    playerIn.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).shrink(1);
                    playerIn.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, emptyBucket);
                }
            }

        }else{
            playerIn.openGui(Main.instance, Reference.GUI_DIARIC_GENERATOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }
}
