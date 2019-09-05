package com.dna.everythingisbad.tile;
import com.dna.everythingisbad.capabilities.ModEnergyHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileMachineBase  extends TileDeviceBase {
    private int progress = 0;
    private int finishedProgress = 0;

    public TileMachineBase(String name) {
        super(name);
        energyHandler = new ModEnergyHandler(1000000,0,10000,true,false);

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
    public void readFromNBT(NBTTagCompound nbt) {

        energyHandler.setEnergyStorage(nbt.getInteger("energyStored"));
        super.readFromNBT(nbt);

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

        super.writeToNBT(nbt);
        nbt.setInteger("energyStored",energyHandler.getEnergyStored());

        return nbt;
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
