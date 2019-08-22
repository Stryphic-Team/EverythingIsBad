package com.dna.everythingisbad.utils.prototypes;

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
/**
 * This helps with fluid registration
 */
public class FluidPrototype {
    private ResourceLocation fluidStill;
    private ResourceLocation fluidFlow;
    private Fluid fluid;
    private BlockFluidBase blockFluidBase;
    private String name;
    private Material material;

    /**
     * Creates a new fluid with the name provided
     * @param name
     */
    public FluidPrototype(String name){
        this(name,null,null,null,null);
    }

    /**
     * Creates a fluid with the name provided and applies a material and a FluidBase
     * @param name
     * @param material
     * @param fluidBase
     */
    public FluidPrototype(String name, Material material, FluidBase fluidBase){
        this(name,fluidBase,null,null,material);
    }

    /**
     * Creates a fluid with the name provided and applies a FluidBase
     * @param name
     * @param fluidBase
     */
    public FluidPrototype(String name, FluidBase fluidBase){
        this(name,fluidBase,null,null,null);
    }

    /**
     * Creates a fluid with the name provided and applies Material
     * @param name
     * @param material
     */
    public FluidPrototype(String name, Material material){
        this(name,null,null,null,material);
    }

    /**
     * Base function not intended to be used externally
     * @param name
     * @param fluid
     * @param fluidStill
     * @param fluidFlow
     * @param material
     */
    public FluidPrototype(String name, @Nullable FluidBase fluid, @Nullable ResourceLocation fluidStill, @Nullable ResourceLocation fluidFlow, @Nullable Material material){
        this.name = name;
        this.fluidStill = fluidStill != null ? fluidStill : new ResourceLocation(Reference.MOD_ID,"fluids/"+name+"_still");
        this.fluidFlow = fluidFlow != null ? fluidFlow : new ResourceLocation(Reference.MOD_ID,"fluids/"+name+"_flow");
        this.fluid = fluid != null ? fluid : new FluidBase(this.name, this.fluidStill, this.fluidFlow);
        this.material = material != null ? material : Material.WATER;
    }

    /**
     * Is to be run after registerBlock
     */
    @SideOnly(Side.CLIENT)
    public void registerRender(){
        //ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(getBlockFluidBase()),new ModItemMeshDefinition(getName()));
        ModelLoader.setCustomStateMapper(getBlockFluidBase(), new ModStateMapper(getName()));
    }

    /**
     * Needs to be run after register fluid
     */
    public void registerBlock(){
        ModBlocks.BLOCKS.add(blockFluidBase);
    }

    /**
     * needs to be run first
     */
    public void registerFluid(){
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
        blockFluidBase = new BlockFluidBase(name,fluid, material);
    }

    /**
     * Returns the fluidStill
     * @return ResourceLocation
     */
    public ResourceLocation getFluidStill() {
        return fluidStill;
    }

    /**
     * Returns the fluidFlow
     * @return ResourceLocation
     */
    public ResourceLocation getFluidFlow() {
        return fluidFlow;
    }

    /**
     * Returns the fluid
     * @return Fluid
     */
    public Fluid getFluid() {
        return fluid;
    }
    /**
     * Returns the blockFluidBase
     * @return BlockFluidBase
     */
    public BlockFluidBase getBlockFluidBase() {
        return blockFluidBase;
    }


    /**
     * returns the name
     * @return String
     */
    public String getName() {
        return name;
    }
    /**
     * returns the material
     * @return Material
     */
    public Material getMaterial() {
        return material;
    }
}
