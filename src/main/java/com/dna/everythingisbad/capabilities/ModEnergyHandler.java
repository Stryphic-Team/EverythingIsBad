package com.dna.everythingisbad.capabilities;

import com.dna.everythingisbad.tile.FaceData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class ModEnergyHandler implements IEnergyStorage {
    private int energyStorage = 0;
    private int maxEnergyStorage;
    private int maxInput;
    private int maxOutput;
    private boolean input;
    private boolean output;
    public ModEnergyHandler(int maxEnergyStorage, int maxOutput, int maxInput, boolean input, boolean output){
        this.maxEnergyStorage = maxEnergyStorage;
        this.maxOutput = maxOutput;
        this.maxInput = maxInput;
        this.input = input;
        this.output = output;
    }
    //TODO Optimized energy output
    public void updateEnergyOutput(TileEntity tileEntity, World world){
        FaceData east = new FaceData(tileEntity.getPos().east(), EnumFacing.EAST,world);
        FaceData west = new FaceData(tileEntity.getPos().west(), EnumFacing.WEST,world);
        FaceData north = new FaceData(tileEntity.getPos().north(), EnumFacing.NORTH,world);
        FaceData south = new FaceData(tileEntity.getPos().south(), EnumFacing.SOUTH,world);
        FaceData up = new FaceData(tileEntity.getPos().up(), EnumFacing.UP,world);
        FaceData down = new FaceData(tileEntity.getPos().down(), EnumFacing.DOWN,world);
        FaceData[] faceData = new FaceData[]{
                east, west, north, south, up, down
        };
        for (FaceData face : faceData) {
            if (face.tileEntity != null) {
                net.minecraftforge.energy.IEnergyStorage cap = (net.minecraftforge.energy.IEnergyStorage) face.tileEntity.getCapability(
                        CapabilityEnergy.ENERGY,
                        face.facing.getOpposite()
                );
                if (cap != null) {
                    int energySent = cap.receiveEnergy(maxOutput, true);
                    int energyExtracted;
                    if(energySent > 0){
                        energyExtracted = this.extractEnergy(energySent,true);
                        if(energyExtracted > 0){
                            int minEnergy = Math.min(energyExtracted,energySent);
                            this.extractEnergy(minEnergy,false);
                            cap.receiveEnergy(minEnergy,false);
                        }
                    }
                }
            }
        }
    }
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if(!input){
            return 0;
        }else{
            int possibleTransfer = maxEnergyStorage - energyStorage;
            int energyTransfer = Math.min(maxReceive,maxInput);
            energyTransfer = Math.min(energyTransfer,possibleTransfer);
            if(simulate) {
                return energyTransfer;
            }else {
                if (energyStorage + energyTransfer <= maxEnergyStorage) {
                    this.energyStorage += energyTransfer;
                    return energyTransfer;
                } else {
                    return 0;
                }
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
            if(simulate) {
                return energyTransfer;
            }else {
                if (energyStorage - energyTransfer >= 0) {
                    energyStorage -= energyTransfer;
                    return energyTransfer;
                } else {
                    return 0;
                }
            }

        }
    }
    public boolean addEnergy(int amount,boolean simulate){
        int energyTransfer;
        int possibleTransfer = maxEnergyStorage - energyStorage;
        energyTransfer = Math.min(amount,Integer.MAX_VALUE);
        energyTransfer = Math.min(energyTransfer,possibleTransfer);

        if (energyStorage + energyTransfer <= maxEnergyStorage) {
            if(!simulate) {
                energyStorage += energyTransfer;
            }
            return true;
        } else {
            return false;
        }
    }
    public boolean reduceEnergy(int amount,boolean simulate){
        int energyTransfer;
        int possibleTransfer = energyStorage;
        energyTransfer = Math.min(amount,Integer.MAX_VALUE);
        energyTransfer = Math.min(energyTransfer,possibleTransfer);

        if (energyStorage - energyTransfer >= 0) {
            if(!simulate) {
                energyStorage -= energyTransfer;
            }
            return true;
        } else {
            return false;
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
