package com.dna.everythingisbad.tile.generators;

import com.dna.everythingisbad.tile.TileGeneratorBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;


public class TileFluxTest extends TileGeneratorBase {
    public TileFluxTest(){
        super("flux_test");
        energyHandler.setMaxOutput(1);
        setTotalEnergyProduced(10000);
        setFinishedProgress(10);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing from) {

        return capability == CapabilityEnergy.ENERGY;
    }
    @Override
    public <T> T getCapability(Capability<T> capability, final EnumFacing from) {

        if (capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(energyHandler);
        }
        return super.getCapability(capability, from);
    }
    @Override
    public boolean hasFuel() {
        return true;
    }

    @Override
    public void consumeFuel() {

    }
}
