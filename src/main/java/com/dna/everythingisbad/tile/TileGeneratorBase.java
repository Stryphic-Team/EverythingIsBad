package com.dna.everythingisbad.tile;

import com.dna.everythingisbad.tile.utils.handlers.ModEnergyHandler;
import com.dna.everythingisbad.tile.utils.handlers.ModFluidHandler;
import com.dna.everythingisbad.tile.utils.handlers.ModItemHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public abstract class TileGeneratorBase extends TileDeviceBase implements ITickable {


    private int progress = 0;
    private int finishedProgress = 0;
    private int totalEnergyProduced = 0;


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
        if(hasFuel() && !inProgress()){
            consumeFuel();
            stepProgress();
        }
        if(inProgress()){
            if(canProduceEnergy()) {
                stepProgress();
                produceEnergy();
            }
        }
        if(getProgress() >= getFinishedProgress()){
            setProgress(0);
        }
    }
    public abstract boolean hasFuel();
    public abstract void consumeFuel();
    public void produceEnergy(){
        energyHandler.addEnergy(getEnergyProducedThisTick(),false);
    }
    public boolean canProduceEnergy(){
        //Checks to see if we have enough energy in the buffer for this tick
        return energyHandler.addEnergy(getEnergyProducedThisTick(),true);
    }
    public int getEnergyProducedThisTick(){
        return totalEnergyProduced / finishedProgress;
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
    public boolean inProgress(){
        if(progress > 0){
            return progress < finishedProgress;
        }
        return false;
    }
    public void stepProgress(){
        this.progress++;
    }
    public void setTotalEnergyProduced(int totalEnergyProduced){
        this.totalEnergyProduced = totalEnergyProduced;
    }


}
