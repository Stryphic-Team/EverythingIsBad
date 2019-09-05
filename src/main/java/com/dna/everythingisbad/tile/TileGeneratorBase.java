package com.dna.everythingisbad.tile;

import com.dna.everythingisbad.capabilities.ModEnergyHandler;
import com.dna.everythingisbad.capabilities.ModFluidHandler;
import com.dna.everythingisbad.capabilities.ModItemHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileGeneratorBase extends TileDeviceBase implements ITickable {


    int ticks = 0;
    private int progress = 0;
    private int finishedProgress = 0;


    public TileGeneratorBase(String name){
        super(name);
        itemStackHadler = new ModItemHandler(1);
        fluidHandler = new ModFluidHandler(false,true);
        energyHandler = new ModEnergyHandler(100000,1000,0,false,true);

    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        energyHandler.setEnergyStorage(compound.getInteger("energy_stored"));
        progress = compound.getInteger("progress");

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("energy_stored",energyHandler.getEnergyStored());
        compound.setInteger("progress",progress);
        return super.writeToNBT(compound);
    }

    @Override
    public void update() {
        energyHandler.updateEnergyOutput(this,this.world);
    }
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing from) {

        return capability == CapabilityEnergy.ENERGY || capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
    }
    @Override
    public <T> T getCapability(Capability<T> capability, final EnumFacing from) {
        if (capability == CapabilityEnergy.ENERGY){

            return CapabilityEnergy.ENERGY.cast(energyHandler);
        }
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHadler);
        }
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(fluidHandler);
        }
        return null;
    }
    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getFinishedProgress() {
        return finishedProgress;
    }

    public void setFinishedProgress(int finishedProgress) {
        this.finishedProgress = finishedProgress;
    }
    public void stepProgress(){
        this.progress++;
    }


}
