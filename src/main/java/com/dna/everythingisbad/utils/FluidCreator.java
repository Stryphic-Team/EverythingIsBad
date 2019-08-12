package com.dna.everythingisbad.utils;


import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModBlocks;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
@Deprecated
public class FluidCreator {
    //Fuck diddy

    private BlockFluidClassic blockfluidclassic; // Very original inspired name
    private Fluid fluid; // Very original inspired name
    private String name;

    public FluidCreator(Fluid fluid){
        //Yeah
        this.fluid = fluid;
        this.name = fluid.getName();
        register(fluid);

        this.blockfluidclassic.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        ModBlocks.BLOCKS.add(blockfluidclassic);

    }
    public static void register(Fluid fluid) {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
    }

    // Extreme botom





    public BlockFluidClassic getBlockFluidClassic() {
        return blockfluidclassic;
    }

    public void setBlockFluidClassic(BlockFluidClassic blockfluidclassic) {
        this.blockfluidclassic = blockfluidclassic;
    }

    public Fluid getFluid() {
        return fluid;
    }

    public void setFluid(Fluid fluid) {
        this.fluid = fluid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
