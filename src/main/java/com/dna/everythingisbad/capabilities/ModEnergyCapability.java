package com.dna.everythingisbad.capabilities;

import net.minecraftforge.energy.IEnergyStorage;

public class ModEnergyCapability implements IEnergyStorage {
    private int energyStorage = 0;
    private int maxEnergyStorage;
    private int maxInput;
    private int maxOutput;
    private boolean input;
    private boolean output;
    public ModEnergyCapability(int maxEnergyStorage,int maxOutput,int maxInput,boolean input,boolean output){
        this.maxEnergyStorage = maxEnergyStorage;
        this.maxOutput = maxOutput;
        this.maxInput = maxInput;
        this.input = input;
        this.output = output;
    }


    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if(!input){
            return 0;
        }else{
            int possibleTransfer = maxEnergyStorage - energyStorage;
            int energyTransfer = Math.min(maxReceive,maxInput);
            energyTransfer = Math.min(energyTransfer,possibleTransfer);
            if(energyStorage + energyTransfer <= maxEnergyStorage){
                energyStorage += energyTransfer;
                return energyTransfer;
            }else{
                return 0;
            }

        }

    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if(!output){
            return 0;
        }else{
            int possibleTransfer = energyStorage;
            int energyTransfer = Math.min(maxExtract,maxOutput);
            energyTransfer = Math.min(energyTransfer,possibleTransfer);
            if(energyStorage - energyTransfer > 0){
                energyStorage -= energyTransfer;
                return energyTransfer;
            }else{
                return 0;
            }

        }
    }

    @Override
    public int getEnergyStored() {
        return energyStorage;
    }

    @Override
    public int getMaxEnergyStored() {
        return maxEnergyStorage;
    }

    @Override
    public boolean canExtract() {
        return output;
    }

    @Override
    public boolean canReceive() {
        return input;
    }
    public int getEnergyStorage() {
        return energyStorage;
    }

    public void setEnergyStorage(int energyStorage) {
        this.energyStorage = energyStorage;
    }

    public int getMaxEnergyStorage() {
        return maxEnergyStorage;
    }

    public void setMaxEnergyStorage(int maxEnergyStorage) {
        this.maxEnergyStorage = maxEnergyStorage;
    }

    public int getMaxInput() {
        return maxInput;
    }

    public void setMaxInput(int maxInput) {
        this.maxInput = maxInput;
    }

    public int getMaxOutput() {
        return maxOutput;
    }

    public void setMaxOutput(int maxOutput) {
        this.maxOutput = maxOutput;
    }

    public boolean isInput() {
        return input;
    }

    public void setInput(boolean input) {
        this.input = input;
    }

    public boolean isOutput() {
        return output;
    }

    public void setOutput(boolean output) {
        this.output = output;
    }
}
