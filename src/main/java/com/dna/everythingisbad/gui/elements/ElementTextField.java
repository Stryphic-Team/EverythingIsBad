package com.dna.everythingisbad.gui.elements;

import com.dna.everythingisbad.gui.DeviceContainerGuiBase;
import com.dna.everythingisbad.utils.RandomUtils;
import net.minecraft.client.gui.GuiTextField;

import java.io.IOException;

public class ElementTextField extends ElementBase {
    public GuiTextField textField;
    public ElementTextField(DeviceContainerGuiBase gui, int startX, int startY) {
        super(gui, startX, startY);
        this.textField = new GuiTextField(RandomUtils.fromRangeI(0,Integer.MAX_VALUE),gui.getFontRenderer(),gui.getGuiLeft()+startX,gui.getGuiTop()+startY,50,20);
    }

    @Override
    public void drawElement() {
        textField.drawTextBox();
    }

    @Override
    public void drawForeground() {

    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        textField.textboxKeyTyped(typedChar, keyCode);

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        textField.mouseClicked(mouseX,mouseY,mouseButton);
    }
}
