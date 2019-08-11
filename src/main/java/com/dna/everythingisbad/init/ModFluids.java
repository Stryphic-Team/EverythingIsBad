package com.dna.everythingisbad.init;

import com.dna.everythingisbad.fluid.FluidDevilsPee;
import com.dna.everythingisbad.fluid.FluidDevilsPeeBlock;
import com.dna.everythingisbad.utils.FluidCreator;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import java.util.ArrayList;
import java.util.List;

public class ModFluids {
    public static BlockFluidClassic DEVILS_PEE =
        new FluidDevilsPeeBlock(new FluidDevilsPee("devils_pee")); // Yeah

    public static final BlockFluidClassic[] FLUIDS=new BlockFluidClassic[]{DEVILS_PEE};

    public static void init(RegistryEvent.Register<Block> event){
        for (BlockFluidClassic fluidblock:FLUIDS){
            register(fluidblock.getFluid());
            event.getRegistry().register(fluidblock);
        }
    }
    public static void register(Fluid fluid) {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
    }
}
