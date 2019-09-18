package com.dna.everythingisbad.gui.elements;

import com.dna.everythingisbad.gui.DeviceContainerGuiBase;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.util.ResourceLocation;

public class ElementOutputSlot extends ElementBase{
    public ElementOutputSlot(DeviceContainerGuiBase gui, int startX, int startY) {
        super(gui, startX, startY);
        resourceLocation = new ResourceLocation(Reference.MOD_ID,"textures/gui/elements/input_slot.png");
        textureWidth = 26;
        textureHeight = 26;
    }

    @Override
    public void drawElement() {
        gui.bindTexture(resourceLocation);
        drawTexturedModalRect(gui.getGuiLeft()+startX-5,gui.getGuiTop()+startY-5,0,0,textureWidth,textureHeight);
    }
}
