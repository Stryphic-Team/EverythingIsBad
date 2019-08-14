package com.dna.everythingisbad.utils;

import com.dna.everythingisbad.block.BlockFluidBase;
import com.dna.everythingisbad.client.ModStateMapper;
import com.dna.everythingisbad.fluids.FluidBase;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class FluidBuilder {
    private ResourceLocation fluidStill;



    private ResourceLocation fluidFlow;
    private Fluid fluid;
    private BlockFluidBase blockFluidBase;
    private String name;
    private Material material;
    public FluidBuilder(String name){
        this(name,null,null,null,null);
    }
    public FluidBuilder(String name, Material material,FluidBase fluidBase){
        this(name,fluidBase,null,null,material);
    }
    public FluidBuilder(String name, FluidBase fluidBase){
        this(name,fluidBase,null,null,null);
    }
    public FluidBuilder(String name, Material material){
        this(name,null,null,null,material);
    }
    public FluidBuilder(String name, @Nullable FluidBase fluid, @Nullable ResourceLocation fluidStill, @Nullable ResourceLocation fluidFlow, @Nullable Material material){
        this.name = name;
        this.fluidStill = fluidStill != null ? fluidStill : new ResourceLocation(Reference.MOD_ID,"fluids/"+name+"_still");
        this.fluidFlow = fluidFlow != null ? fluidFlow : new ResourceLocation(Reference.MOD_ID,"fluids/"+name+"_flow");;
        this.fluid = fluid != null ? fluid : new FluidBase(this.name, this.fluidStill, this.fluidFlow);
        this.material = material != null ? material : Material.WATER;
    }
    @SideOnly(Side.CLIENT)
    public void registerRender(){
        //ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(getBlockFluidBase()),new ModItemMeshDefinition(getName()));
        ModelLoader.setCustomStateMapper(getBlockFluidBase(), new ModStateMapper(getName()));
    }
    public void registerBlock(){
        ModBlocks.BLOCKS.add(blockFluidBase);
    }

    public void registerFluid(){
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
        blockFluidBase = new BlockFluidBase(name,fluid, material);
    }
    public ResourceLocation getFluidStill() {
        return fluidStill;
    }

    public void setFluidStill(ResourceLocation fluidStill) {
        this.fluidStill = fluidStill;
    }

    public ResourceLocation getFluidFlow() {
        return fluidFlow;
    }

    public void setFluidFlow(ResourceLocation fluidFlow) {
        this.fluidFlow = fluidFlow;
    }

    public Fluid getFluid() {
        return fluid;
    }

    public void setFluid(Fluid fluid) {
        this.fluid = fluid;
    }

    public BlockFluidBase getBlockFluidBase() {
        return blockFluidBase;
    }

    public void setBlockFluidBase(BlockFluidBase blockFluidBase) {
        this.blockFluidBase = blockFluidBase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
