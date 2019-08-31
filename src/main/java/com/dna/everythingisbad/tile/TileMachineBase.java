package com.dna.everythingisbad.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;

public class TileMachineBase  extends TileEntity implements ITickable, IEnergyStorage, IItemHandlerModifiable {
    private int energyStored = 0;
    private int energyInputRate = 100000;
    private int energyMaxStorage = 1000000;
    @Override
    public void update() {

    }
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if(energyStored <= energyMaxStorage && !simulate){
            if(energyStored + maxReceive <= energyMaxStorage) {
                if(maxReceive >= energyInputRate){
                    energyStored += energyInputRate;
                    return energyInputRate;
                }else{
                    energyStored += maxReceive;
                    return maxReceive;
                }
            }else{
                //only transfers the difference to make sure that you can't go over the max;
                int transfer = energyMaxStorage - energyStored;
                energyStored += transfer;
                return transfer;
            }
        }else{
            return 0;
        }
    }
    public boolean canInteractWith(EntityPlayer playerIn) {
        // If we are too far away from this tile entity you cannot use it
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
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
        return energyMaxStorage;
    }

    @Override
    public boolean canExtract() {
        return false;
    }

    @Override
    public boolean canReceive() {
        return true;
    }
    public void reduceEnergyStored(int amount){
        if(energyStored - amount < 0){
            energyStored -= amount;
        }else{
            energyStored = 0;
        }
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
            return CapabilityEnergy.ENERGY.cast(this);
        }
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this);
            //return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(this);
        }
        return super.getCapability(capability, facing);
    }
    @Override
    public int getSlots() {
        return 0;
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return new ItemStack(Items.AIR);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return stack;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return new ItemStack(Items.AIR);
    }

    @Override
    public int getSlotLimit(int slot) {
        return 0;
    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {

    }
}
