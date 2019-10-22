package com.dna.everythingisbad.gui.elements;

import com.dna.everythingisbad.gui.DeviceContainerGuiBase;
import com.dna.everythingisbad.utils.RandomUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import java.io.IOException;

public class ElementButton extends ElementBase {

    public GuiButton guiButton;
    public ElementButton(DeviceContainerGuiBase gui, int startX, int startY,String text) {
        super(gui, startX, startY);
        this.guiButton = new GuiButton(RandomUtils.fromRangeI(0,Integer.MAX_VALUE),gui.getGuiLeft()+startX,gui.getGuiTop()+startY,text);
        guiButton.width = 100;
    }

    @Override
    public void drawElement() {
        guiButton.drawButton(Minecraft.getMinecraft(),gui.getMouseX(),gui.getMouseY(),0f);
    }

    @Override
    public void drawForeground() {

    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        boolean buttonPressed = guiButton.mousePressed(Minecraft.getMinecraft(),mouseX,mouseY);
        if(buttonPressed) gui.buttonPressed(this);
    }

}
