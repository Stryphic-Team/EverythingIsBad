package com.dna.everythingisbad.gui.elements;

import com.dna.everythingisbad.gui.DeviceContainerGuiBase;
import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.tile.utils.handlers.ModEnergyHandler;
import net.minecraft.util.ResourceLocation;

public class ElementEnergyGauge extends ElementBase {
    private ModEnergyHandler energyHandler;
    public ElementEnergyGauge(DeviceContainerGuiBase gui, int startX, int startY) {
        super(gui, startX, startY);
        energyHandler = gui.getTileEntity().getEnergyHandler();
        resourceLocation = new ResourceLocation(Reference.MOD_ID, "textures/gui/elements/energy_gauge.png");
        textureWidth = 24;
        textureHeight = 73;

    }

    @Override
    public void drawElement() {
        gui.bindTexture(resourceLocation);
        //TODO setup textures
        int energyStoredScaled = getEnergyStoredScaled(73);
        //Draws the full energy gauge
        drawTexturedModalRect(gui.getGuiLeft() + startX, gui.getGuiTop() + startY, 0, 0, 12, 73);
        //Draws the empty energy gauge
        drawTexturedModalRect(gui.getGuiLeft() + startX, gui.getGuiTop() + startY, 12, 0, 12, 72 - energyStoredScaled);
    }
    private int getEnergyStoredScaled(int pixels)
    {
        int i = energyHandler.getEnergyStored();
        int j = energyHandler.getMaxEnergyStored();
        float ratio = (float)i / (float)j;
        return i != 0 && j != 0 ? (int)(ratio * (float)pixels): 0;
    }
}
