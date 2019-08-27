package com.dna.everythingisbad.tile;

import com.dna.everythingisbad.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class TileStupidCoreMachine extends TileEntity implements ITickable, IEnergyStorage {
    private int energyStored = 0;
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
        return true;
    }
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing from) {

        return capability == CapabilityEnergy.ENERGY || capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {

        energyStored = nbt.getInteger("energyStored");
        super.readFromNBT(nbt);

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

        super.writeToNBT(nbt);
        nbt.setInteger("energyStored",energyStored);

        return nbt;
    }
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(new net.minecraftforge.energy.IEnergyStorage() {

                @Override
                public int receiveEnergy(int maxReceive, boolean simulate) {

                    return TileStupidCoreMachine.this.receiveEnergy(maxReceive, simulate);
                }

                @Override
                public int extractEnergy(int maxExtract, boolean simulate) {

                    return TileStupidCoreMachine.this.extractEnergy(maxExtract, simulate);
                }

                @Override
                public int getEnergyStored() {

                    return TileStupidCoreMachine.this.getEnergyStored();
                }

                @Override
                public int getMaxEnergyStored() {

                    return TileStupidCoreMachine.this.getMaxEnergyStored();
                }

                @Override
                public boolean canExtract() {

                    return true;
                }

                @Override
                public boolean canReceive() {

                    return false;
                }
            });
        }
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new IItemHandler() {
                @Override
                public int getSlots() {
                    return 1;
                }

                @Nonnull
                @Override
                public ItemStack getStackInSlot(int slot) {
                    return new ItemStack(ModItems.STUPID_CORE_ITEM);
                }

                @Nonnull
                @Override
                public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                    return null;
                }

                @Nonnull
                @Override
                public ItemStack extractItem(int slot, int amount, boolean simulate) {
                    return new ItemStack(ModItems.STUPID_CORE_ITEM);
                }

                @Override
                public int getSlotLimit(int slot) {
                    return 1;
                }
            });
        }
        return super.getCapability(capability, facing);
    }
    static {
        register("stupid_core_machine", TileStupidCoreMachine.class);
    }
}
