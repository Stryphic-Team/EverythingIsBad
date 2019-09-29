package com.dna.everythingisbad.gui.elements;

import com.dna.everythingisbad.gui.DeviceContainerGuiBase;
import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.tile.utils.handlers.ModFluidHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class ElementFluidTank  extends ElementBase{
    ModFluidHandler fluidHandler;
    public ElementFluidTank(DeviceContainerGuiBase gui, int startX, int startY) {
        super(gui, startX, startY);
        fluidHandler = gui.getTileEntity().getFluidHandler();
        resourceLocation = new ResourceLocation(Reference.MOD_ID, "textures/gui/elements/fluid_tank.png");
        textureWidth = 24;
        textureHeight = 73;
    }

    @Override
    public void drawElement() {
        int fluidStorageScaled = this.getFluidStorageScaled(73);
        FluidStack fluidStack = gui.getTileEntity().getFluidHandler().getFluidTank().getFluid();

        if(fluidStack != null){
            if(fluidStack.amount > 0) {
                Fluid fluid = fluidStack.getFluid();
                if (fluid != null) {
                    ResourceLocation fluidResource = fluidStack.getFluid().getStill();
                    fluidResource = new ResourceLocation(fluidResource.getResourceDomain(),"textures/"+fluidResource.getResourcePath()+".png");
                    gui.bindTexture(fluidResource);
                    gui.drawSizedTexturedModalRect(gui.getGuiLeft() + startX, gui.getGuiTop() + startY, 0, 0, 12, 73,16,512);
                }
            }
        }
        //TODO Set up the texture for the fluid tank
        gui.bindTexture(resourceLocation);
        drawTexturedModalRect(gui.getGuiLeft() + startX, gui.getGuiTop() + startY, 0, 0, 12, 73-fluidStorageScaled);
        drawTexturedModalRect(gui.getGuiLeft() + startX, gui.getGuiTop() + startY, 12, 0, 12, 73);
    }

    @Override
    public void drawForeground() {

    }

    protected int getFluidStorageScaled(int pixels){
        int i = fluidHandler.getFluidTank().getFluidAmount();
        int j = fluidHandler.getFluidTank().getCapacity();
        float ratio = (float)i / (float)j;
        return i != 0 && j != 0 ? (int)(ratio * (float)pixels): 0;
    }
}
