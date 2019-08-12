package com.dna.everythingisbad.utils;


import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.fluid.FluidBlockCore;
import com.dna.everythingisbad.fluid.FluidCore;
import com.dna.everythingisbad.init.ModBlocks;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidCreator {
    //Fuck diddy

    private FluidBlockCore blockfluidclassic; // Very original inspired name
    private FluidCore fluid; // Very original inspired name
    private String name;

    public FluidCreator(FluidCore fluid){
        //Yeah
        this.fluid = fluid;
        this.name = fluid.getName();
        register(fluid);
        this.blockfluidclassic = new FluidBlockCore(name,fluid);
        this.blockfluidclassic.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        ModBlocks.BLOCKS.add(blockfluidclassic);

    }
    public static void register(Fluid fluid) {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
    }

    // Extreme botom





    public FluidBlockCore getBlockFluidClassic() {
        return blockfluidclassic;
    }

    public void setBlockFluidClassic(FluidBlockCore blockfluidclassic) {
        this.blockfluidclassic = blockfluidclassic;
    }

    public FluidCore getFluid() {
        return fluid;
    }

    public void setFluid(FluidCore fluid) {
        this.fluid = fluid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
