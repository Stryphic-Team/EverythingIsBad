package com.dna.everythingisbad.fluid;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraft.entity.Entity;

import java.util.function.Consumer;

public class FluidDevilsPeeBlock extends BlockFluidClassic {

    private Consumer<EntityLivingBase> consumer;

    public FluidDevilsPeeBlock(Fluid fluid) {
        super(fluid, Material.WATER);
        setRegistryName(new ResourceLocation(Reference.MOD_ID, fluid.getName()));
        setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        this.consumer = consumer;
    }

    public String getName() {
        return fluidName;
    }
}
