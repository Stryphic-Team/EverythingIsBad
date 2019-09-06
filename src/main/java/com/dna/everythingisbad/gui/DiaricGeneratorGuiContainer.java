package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.tile.TileDiaricGenerator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class DiaricGeneratorGuiContainer extends DeviceContainerGuiBase {


    public DiaricGeneratorGuiContainer(InventoryPlayer player, TileDiaricGenerator tileentity) {
        super(
                new DiaricGeneratorContainer(player,tileentity),
                player,
                tileentity,
                new ResourceLocation(Reference.MOD_ID, "textures/gui/diaric_generator.png")
        );

    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        drawEnergyStorage();
        drawFluidStorage();
    }
    @Override
    public void drawEnergyStorage(){
        int energyStoredScaled = this.getEnergyStoredScaled(73);
        this.drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 7, 177, 18, 12, 72 - energyStoredScaled);
    }
}
