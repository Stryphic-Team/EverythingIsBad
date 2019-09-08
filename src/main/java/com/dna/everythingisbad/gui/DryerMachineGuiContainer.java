package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.gui.container.DryerMachineContainer;
import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.tile.TileDryerMachine;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class DryerMachineGuiContainer extends DeviceContainerGuiBase {
    public DryerMachineGuiContainer(InventoryPlayer player, TileDryerMachine tileentity) {
        super(
                new DryerMachineContainer(player,tileentity),
                player,
                tileentity,
                new ResourceLocation(Reference.MOD_ID,"textures/gui/dryer_machine.png")
        );
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        drawEnergyStorage();
        drawFluidStorage();
        drawProgress();
    }

    @Override
    public void drawEnergyStorage() {
        int energyStoredScaled = this.getEnergyStoredScaled(73);
        this.drawTexturedModalRect(this.guiLeft + 144, this.guiTop + 7, 177, 18, 12, 72 - energyStoredScaled);
    }

    @Override
    public void drawProgress() {
        int progressScaled = this.getProgressScaled(24);
        this.drawTexturedModalRect(this.guiLeft + 80, this.guiTop + 35, 177, 0, progressScaled, 17);
    }
}
