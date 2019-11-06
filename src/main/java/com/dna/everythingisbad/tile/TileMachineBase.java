package com.dna.everythingisbad.tile;

import com.dna.everythingisbad.tile.utils.handlers.ModEnergyHandler;
import com.dna.everythingisbad.tile.utils.handlers.ModFluidHandler;
import com.dna.everythingisbad.tile.utils.handlers.ModItemHandler;

public abstract class TileMachineBase  extends TileDeviceBase {
    protected int outputSlot = 0;
    private int speedMultiplier = 1;
    protected int energyUsedPerTick = 100;
    public TileMachineBase(String name) {
        super(name);
        energyHandler = new ModEnergyHandler(1000000,0,10000,true,false);
        fluidHandler = new ModFluidHandler(false,false);
        itemStackHadler = new ModItemHandler(0);
    }
    public void stepProgress(){
        if(progress < finishedProgress){
            progress += speedMultiplier;
        }
    }

    @Override
    public void update() {
        super.update();
        if(hasNecessaryItems()){
            speedMultiplier = (int) Math.round(Math.abs(Math.log(1f / (1f + (float)energyHandler.getEnergyStored()))));
            stepProgress();
            reduceEnergy();
            if(getProgress() >= getFinishedProgress()){
                insertOutput();
                reduceInput();
                setProgress(0);
            }
        }else{
            //setProgress(0);
        }

    }

    public abstract boolean hasNecessaryItems();
    public abstract void insertOutput();
    public abstract void reduceInput();
    public void reduceEnergy(){
        energyHandler.reduceEnergy(energyUsedPerTick*speedMultiplier,false);
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
