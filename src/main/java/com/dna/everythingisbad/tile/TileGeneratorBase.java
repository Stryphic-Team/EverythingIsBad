package com.dna.everythingisbad.tile;

import com.dna.everythingisbad.capabilities.ModEnergyCapability;
import com.dna.everythingisbad.capabilities.ModFluidCapability;
import com.dna.everythingisbad.misc.ModItemStackHadler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileGeneratorBase extends TileEntity implements ITickable {

    ModItemStackHadler itemStackHadler;
    ModFluidCapability fluidCapability;
    ModEnergyCapability energyCapability;
    int ticks = 0;
    private int progress = 0;
    private int finishedProgress = 0;


    public TileGeneratorBase(){
        itemStackHadler = new ModItemStackHadler(1);
        fluidCapability = new ModFluidCapability(false,true);
        energyCapability = new ModEnergyCapability(100000,1000,0,false,true);

    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        energyCapability.setEnergyStorage(compound.getInteger("energy_stored"));
        progress = compound.getInteger("progress");

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("energy_stored",energyCapability.getEnergyStored());
        compound.setInteger("progress",progress);
        return super.writeToNBT(compound);
    }

    @Override
    public void update() {
        energyCapability.updateEnergyOutput(this,this.world);
    }
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing from) {

        return capability == CapabilityEnergy.ENERGY || capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
    }
    @Override
    public <T> T getCapability(Capability<T> capability, final EnumFacing from) {
        if (capability == CapabilityEnergy.ENERGY){

            return CapabilityEnergy.ENERGY.cast(energyCapability);
        }
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHadler);
        }
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(fluidCapability);
        }
        return null;
    }


}
