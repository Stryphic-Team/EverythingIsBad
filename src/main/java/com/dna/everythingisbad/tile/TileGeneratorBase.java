package com.dna.everythingisbad.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileGeneratorBase extends TileEntity implements ITickable, IEnergyStorage, IItemHandlerModifiable, IFluidHandler {

    boolean fluidAcceptor = false;
    boolean energyAcceptor = false;
    boolean itemAcceptor = false;

    @Override
    public void update() {

    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored() {
        return 0;
    }

    @Override
    public int getMaxEnergyStored() {
        return 0;
    }

    @Override
    public boolean canExtract() {
        return false;
    }

    @Override
    public boolean canReceive() {
        return false;
    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {

    }

    @Override
    public int getSlots() {
        return 0;
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return null;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return null;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return null;
    }

    @Override
    public int getSlotLimit(int slot) {
        return 0;
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return new IFluidTankProperties[0];
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        return 0;
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        return null;
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return null;
    }

    class FaceData{
        public TileEntity tileEntity;
        public EnumFacing facing;
        public FaceData(BlockPos pos, EnumFacing facing){
            World world = getWorld();
            this.tileEntity = world.getTileEntity(pos);
            this.facing = facing;
        }
    }

    @Override
    public <T> T getCapability(Capability<T> capability, final EnumFacing from) {
        if (capability == CapabilityEnergy.ENERGY && energyAcceptor){
            return CapabilityEnergy.ENERGY.cast(this);
        }
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && itemAcceptor){
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this);
        }
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && fluidAcceptor){
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
        }
        return null;
    }
}
