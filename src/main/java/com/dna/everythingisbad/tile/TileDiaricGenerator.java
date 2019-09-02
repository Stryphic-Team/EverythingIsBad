package com.dna.everythingisbad.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileDiaricGenerator extends TileGeneratorBase implements ITickable, IEnergyStorage, IFluidTank {
    private int energyStored = 0;
    private int ticks = 0;

    boolean fluidAcceptor = false;
    boolean energyAcceptor = false;
    boolean itemAcceptor = false;

    public TileDiaricGenerator(){
        fluidAcceptor = true;
        energyAcceptor = true;
        itemAcceptor = true;
    }

    @Override
    public void update() {
        ticks++;
        if(ticks % 10 == 0) {
            FaceData east = new FaceData(getPos().east(), EnumFacing.EAST);
            FaceData west = new FaceData(getPos().west(), EnumFacing.WEST);
            FaceData north = new FaceData(getPos().north(), EnumFacing.NORTH);
            FaceData south = new FaceData(getPos().south(), EnumFacing.SOUTH);
            FaceData up = new FaceData(getPos().up(), EnumFacing.UP);
            FaceData down = new FaceData(getPos().down(), EnumFacing.DOWN);


            FaceData[] faceData = new FaceData[]{
                    east, west, north, south, up, down
            };
            for (FaceData face : faceData) {
                if (face.tileEntity != null) {
                    net.minecraftforge.energy.IEnergyStorage cap = (net.minecraftforge.energy.IEnergyStorage) face.tileEntity.getCapability(
                            CapabilityEnergy.ENERGY,
                            face.facing.getOpposite()
                    );
                    if (cap != null) {
                        cap.receiveEnergy(1000, false);

                        extractEnergy(1000, false);
                    }

                }
            }
        }
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
        return energyStored;
    }

    @Override
    public int getMaxEnergyStored() {
        return 50000;
    }

    @Override
    public boolean canExtract() {
        return true;
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
    static {
        register("diaric_generator", TileDiaricGenerator.class);
    }

    @Nullable
    @Override
    public FluidStack getFluid() {
        return null;
    }

    @Override
    public int getFluidAmount() {
        return 0;
    }

    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public FluidTankInfo getInfo() {
        return null;
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        return 0;
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return null;
    }
}
