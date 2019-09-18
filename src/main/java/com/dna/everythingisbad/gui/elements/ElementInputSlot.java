package com.dna.everythingisbad.gui.elements;

import com.dna.everythingisbad.gui.DeviceContainerGuiBase;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.util.ResourceLocation;

public class ElementInputSlot extends ElementBase{

    public ElementInputSlot(DeviceContainerGuiBase gui, int startX, int startY) {
        super(gui, startX, startY);
        resourceLocation = new ResourceLocation(Reference.MOD_ID,"textures/gui/elements/input_slot.png");
        textureWidth = 18;
        textureHeight = 18;
    }

    @Override
    public void drawElement() {
        gui.bindTexture(resourceLocation);
        drawTexturedModalRect(gui.getGuiLeft() + startX - 1,gui.getGuiTop() + startY - 1,0,0,textureWidth,textureHeight);
    }
}
