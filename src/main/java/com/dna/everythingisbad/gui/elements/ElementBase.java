package com.dna.everythingisbad.gui.elements;

import com.dna.everythingisbad.gui.DeviceContainerGuiBase;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public abstract class ElementBase {

    protected ResourceLocation resourceLocation;
    protected DeviceContainerGuiBase gui;
    private FontRenderer fontRenderer;

    protected int startX;
    protected int startY;

    protected int width;
    protected int height;

    protected int textureWidth;
    protected int textureHeight;

    protected boolean visible;
    public ElementBase(DeviceContainerGuiBase gui, int startX, int startY){
        this.gui = gui;
        this.startX = startX;
        this.startY = startY;
    }
    public void drawTexturedModalRect(int x, int y, int u, int v, int width, int height) {

        gui.drawSizedTexturedModalRect(x, y, u, v, width, height, textureWidth, textureHeight);
    }

    public abstract void drawElement();
    public abstract void drawForeground();
    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }
    public boolean isVisible(){
        return visible;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }


}
