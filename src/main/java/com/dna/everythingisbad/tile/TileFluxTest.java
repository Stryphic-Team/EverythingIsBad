package com.dna.everythingisbad.tile;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;


public class TileFluxTest extends TileGeneratorBase {
    private int energyStored = 10000;
    private int ticks = 0;
    public TileFluxTest(){
        super("flux_test");
        energyHandler.setMaxOutput(1);
    }

    @Override
    public void update(){
        super.update();
        energyHandler.setEnergyStorage(10000);
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

    static {
        register("flux_test", TileFluxTest.class);
    }
}
