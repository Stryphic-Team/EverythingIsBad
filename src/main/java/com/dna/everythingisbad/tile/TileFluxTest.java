package com.dna.everythingisbad.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;


public class TileFluxTest extends TileGeneratorBase implements IEnergyStorage, ITickable {
    private int energyStored = 10000;
    private int ticks = 0;

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        energyStored -= maxExtract;
        if(energyStored > maxExtract){
            return maxExtract;
        }else{
            return 0;
        }

    }

    @Override
    public int getEnergyStored() {
        return energyStored;
    }

    @Override
    public int getMaxEnergyStored() {
        return 10000;
    }

    @Override
    public boolean canExtract() {
        return false;
    }

    @Override
    public boolean canReceive() {
        return false;
    }


    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing from) {

        return capability == CapabilityEnergy.ENERGY;
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {

        energyStored = nbt.getInteger("energyStored");
        super.readFromNBT(nbt);

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

        super.writeToNBT(nbt);
        nbt.setInteger("energyStored",energyStored);

        return nbt;
    }
    @Override
    public <T> T getCapability(Capability<T> capability, final EnumFacing from) {

        if (capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(new net.minecraftforge.energy.IEnergyStorage() {

                @Override
                public int receiveEnergy(int maxReceive, boolean simulate) {

                    return TileFluxTest.this.receiveEnergy(maxReceive, simulate);
                }

                @Override
                public int extractEnergy(int maxExtract, boolean simulate) {

                    return TileFluxTest.this.extractEnergy(maxExtract, simulate);
                }

                @Override
                public int getEnergyStored() {

                    return TileFluxTest.this.getEnergyStored();
                }

                @Override
                public int getMaxEnergyStored() {

                    return TileFluxTest.this.getMaxEnergyStored();
                }

                @Override
                public boolean canExtract() {

                    return true;
                }

                @Override
                public boolean canReceive() {

                    return false;
                }
            });
        }
        return super.getCapability(capability, from);
    }

    @Override
    public void update() {


    }
    static {
        register("flux_test", TileFluxTest.class);
    }
}
