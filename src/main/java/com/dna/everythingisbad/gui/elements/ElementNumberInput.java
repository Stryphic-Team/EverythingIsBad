package com.dna.everythingisbad.gui.elements;

import com.dna.everythingisbad.gui.DeviceContainerGuiBase;

import java.io.IOException;

public class ElementNumberInput extends ElementTextField {
    public ElementNumberInput(DeviceContainerGuiBase gui, int startX, int startY) {
        super(gui, startX, startY);
        textField.width = 100;
    }
    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        try {
            Float.parseFloat(textField.getText());
        }catch (NumberFormatException e){
            String currentText = textField.getText();
            if(!currentText.equals("")) textField.setText(currentText.substring(0,currentText.length()-1));
        }
    }
}
