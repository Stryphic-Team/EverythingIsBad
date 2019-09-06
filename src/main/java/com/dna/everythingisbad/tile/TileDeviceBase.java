package com.dna.everythingisbad.tile;

import com.dna.everythingisbad.capabilities.ModEnergyHandler;
import com.dna.everythingisbad.capabilities.ModFluidHandler;
import com.dna.everythingisbad.capabilities.ModItemHandler;
import com.dna.everythingisbad.init.ModTileEntities;
import com.dna.everythingisbad.network.messagestypes.MessageSyncMachineGui;
import com.dna.everythingisbad.utils.FluidCache;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.FluidStack;


public class TileDeviceBase extends TileEntity implements ITickable {
    private String name;
    private int progress;
    private int finishedProgress;
    protected ModItemHandler itemStackHadler;
    protected ModFluidHandler fluidHandler;
    protected ModEnergyHandler energyHandler;
    private MessageSyncMachineGui messageSyncMachineGui;

    public TileDeviceBase(String name){
        this.name = name;
        ModTileEntities.TILE_ENTITIES.add(this);
        messageSyncMachineGui = new MessageSyncMachineGui("",0,0,0);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        return super.writeToNBT(compound);
    }

    public String getName(){
        return name;
    }

    public ModItemHandler getItemStackHadler() {
        return itemStackHadler;
    }

    public ModFluidHandler getFluidHandler() {
        return fluidHandler;
    }

    public ModEnergyHandler getEnergyHandler() {
        return energyHandler;
    }

    @Override
    public void update() {

    }
    public void setField(EnumTileDataType type,int data){
        FluidStack fluidStack;
        switch (type){
            case ENERGY_STORAGE:
                energyHandler.setEnergyStorage(data);
                break;
            case PROGRESS:
                this.progress = data;
                break;
            case FLUID_STORED:
                fluidStack = fluidHandler.getFluidTank().getFluid();
                if(fluidStack != null){
                    fluidStack.amount = data;
                }
                break;
            case FLUID_TYPE:
                fluidHandler.getFluidTank().setFluid(new FluidStack(FluidCache.fromInt(data),0));
                break;
        }
    }
    public int getField(EnumTileDataType type){
        switch (type){
            case ENERGY_STORAGE:
                return energyHandler.getEnergyStored();
            case PROGRESS:
                return progress;
            case FLUID_STORED:
                return fluidHandler.getFluidTank().getFluidAmount();
            case FLUID_TYPE:
                FluidStack fluid = fluidHandler.getFluidTank().getFluid();
                if(fluid != null){
                    return FluidCache.toInt(fluidHandler.getFluidTank().getFluid().getUnlocalizedName());
                }else {
                    return -1;
                }
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
