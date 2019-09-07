package com.dna.everythingisbad.tile;

import com.dna.everythingisbad.init.ModFluids;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraftforge.fluids.FluidStack;

public class TileDiaricGenerator extends TileGeneratorBase implements ITickable {
    public TileDiaricGenerator(){
        super("diaric_generator");
        fluidHandler.addToWhitelist(ModFluids.DIARIA.getFluid());
        setFinishedProgress(240);
        this.displayName = "Diaric Generator";
    }

    @Override
    public void update() {
        super.update();
        if(energyHandler.getEnergyStored() < energyHandler.getMaxEnergyStored()){


            if(fluidHandler.getFluidTank().getFluidAmount() >= 10){
                fluidHandler.reduceFluid(10,false);
                energyHandler.addEnergy(100,false);

            }
        }
    }


    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        FluidStack fluid = FluidStack.loadFluidStackFromNBT(compound);
        fluidHandler.getFluidTank().setFluid(fluid);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {

        fluidHandler.getFluidTank().writeToNBT(compound);
        return super.writeToNBT(compound);
    }

}
