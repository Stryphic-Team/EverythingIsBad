package com.dna.everythingisbad.block.machines;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.tile.TileDeviceBase;
import com.dna.everythingisbad.tile.TileDryerMachine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;

import javax.annotation.Nullable;

public class BlockDryerMachine extends BlockMachineBase {
    TileDeviceBase tileDeviceBase;
    public BlockDryerMachine(String name) {
        super(name);
    }
    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        tileDeviceBase = new TileDryerMachine();
        return tileDeviceBase;
    }


    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        }
        TileDeviceBase tileDeviceBase = (TileDeviceBase) worldIn.getTileEntity(pos);
        if (!(tileDeviceBase instanceof TileDryerMachine)) {
            return false;
        }

        if(playerIn.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem().equals(Items.BUCKET)){
            FluidTank fluidTank = tileDeviceBase.getFluidHandler().getFluidTank();
            if(fluidTank.getFluidAmount() >= 1000){


                playerIn.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).shrink(1);
                playerIn.inventory.addItemStackToInventory(FluidUtil.getFilledBucket(fluidTank.getFluid()));
                if(playerIn instanceof EntityPlayerMP){
                    EntityPlayerMP playerMP = (EntityPlayerMP)playerIn;
                    playerMP.getEntityWorld().playSound((EntityPlayer)null, playerIn.posX,playerIn.posY,playerIn.posZ,SoundEvents.ITEM_BOTTLE_EMPTY,SoundCategory.PLAYERS,1f,1f);

                }
                tileDeviceBase.getFluidHandler().drain(1000,true);
            }

        }else{
            playerIn.openGui(Main.instance, Reference.GUI_DRYER_MACHINE, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }


        return true;
    }
}
