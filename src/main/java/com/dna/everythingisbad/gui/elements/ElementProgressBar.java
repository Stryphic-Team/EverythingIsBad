package com.dna.everythingisbad.gui.elements;

import com.dna.everythingisbad.gui.DeviceContainerGuiBase;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.util.ResourceLocation;

public class ElementProgressBar extends ElementBase {
    public ElementProgressBar(DeviceContainerGuiBase gui, int startX, int startY) {
        super(gui, startX, startY);
        resourceLocation = new ResourceLocation(Reference.MOD_ID, "textures/gui/elements/progress_bar.png");
        textureWidth = 23;
        textureHeight = 33;
    }

    @Override
    public void drawElement() {
        gui.bindTexture(resourceLocation);
        int progressScaled = getProgressScaled(23);
        drawTexturedModalRect(gui.getGuiLeft() + startX, gui.getGuiTop() + startY, 0, 0, 23, 16);
        drawTexturedModalRect(gui.getGuiLeft() + startX, gui.getGuiTop() + startY, 0, 16, progressScaled, 16);
    }

    @Override
    public void drawForeground() {

    }

    protected int getProgressScaled(int pixels)
    {
        int i = gui.getTileEntity().getProgress();
        int j = gui.getTileEntity().getFinishedProgress();
        float ratio = (float)i / (float)j;
        return i != 0 && j != 0 ? (int)(ratio * (float)pixels): 0;
    }
}
