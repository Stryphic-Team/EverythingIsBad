package com.dna.everythingisbad.tile;

import com.dna.everythingisbad.tile.utils.handlers.ModEnergyHandler;
import com.dna.everythingisbad.tile.utils.handlers.ModFluidHandler;
import com.dna.everythingisbad.tile.utils.handlers.ModItemHandler;
import net.minecraft.util.ITickable;

public abstract class TileGeneratorBase extends TileDeviceBase implements ITickable {



    private int totalEnergyProduced = 0;


    public TileGeneratorBase(String name){
        super(name);
        itemStackHadler = new ModItemHandler(1);
        fluidHandler = new ModFluidHandler(false,true);
        energyHandler = new ModEnergyHandler(100000,1000,0,false,true);

    }


    @Override
    public void update() {
        super.update();
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
        return getEnergyProducedThisTick() + energyHandler.getEnergyStored() < energyHandler.getMaxEnergyStored();
    }
    public int getEnergyProducedThisTick(){
        return totalEnergyProduced / finishedProgress;
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
