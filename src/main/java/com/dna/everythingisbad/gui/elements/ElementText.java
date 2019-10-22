package com.dna.everythingisbad.gui.elements;

import com.dna.everythingisbad.gui.DeviceContainerGuiBase;

public class ElementText extends ElementBase {
    private String text;
    public ElementText(DeviceContainerGuiBase gui, int startX, int startY,String text) {
        super(gui, startX, startY);
        this.text = text;

    }

    @Override
    public void drawElement() {

    }

    @Override
    public void drawForeground() {
        gui.drawText(startX,startY,text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
