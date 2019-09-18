package com.dna.everythingisbad.tile;

import com.dna.everythingisbad.capabilities.ModEnergyHandler;
import com.dna.everythingisbad.capabilities.ModFluidHandler;
import com.dna.everythingisbad.capabilities.ModItemHandler;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;

public abstract class TileMachineBase  extends TileDeviceBase {
    protected int outputSlot = 0;
    public TileMachineBase(String name) {
        super(name);
        energyHandler = new ModEnergyHandler(1000000,0,10000,true,false);
        fluidHandler = new ModFluidHandler(false,false);
        itemStackHadler = new ModItemHandler(0);
    }
    public void stepProgress(){
        if(progress < finishedProgress){
            progress++;
        }
    }
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing from) {

        return capability == CapabilityEnergy.ENERGY || capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(energyHandler);
        }
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){

            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHadler);
        }
        return super.getCapability(capability, facing);
    }
    public void setField(EnumTileDataType type,int data){
        switch (type){
            case ENERGY_STORAGE:
                energyHandler.setEnergyStorage(data);
                break;
            case PROGRESS:
                this.progress = data;
                break;
        }
    }
    public int getField(EnumTileDataType type){
        switch (type){
            case ENERGY_STORAGE:
                return energyHandler.getEnergyStored();
            case PROGRESS:
                return progress;
            default:
                return 0;
        }
    }

    @Override
    public void update() {
        super.update();
        if(hasNecessaryItems()){
            stepProgress();
            reduceEnergy();
            if(getProgress() >= getFinishedProgress()){
                insertOutput();
                reduceInput();
                setProgress(0);
            }
        }else{
            setProgress(0);
        }
    }

    public abstract boolean hasNecessaryItems();
    public abstract void insertOutput();
    public abstract void reduceInput();
    public abstract void reduceEnergy();
    //Gets the progress at which the machine is done
    public int getFinishedProgress(){
        return finishedProgress;
    }
    public int getProgress(){
        return progress;
    }
    public void setProgress(int progress){
        this.progress = progress;
    }
    public void setFinishedProgress(int finishedProgress){
        this.finishedProgress = finishedProgress;
    }

}
