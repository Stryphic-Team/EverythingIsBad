package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.tile.TileStupidCoreMachine;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class StupidCoreMachineGuiContainer extends DeviceContainerGuiBase {

    public StupidCoreMachineGuiContainer(InventoryPlayer player, TileStupidCoreMachine tileentity) {
        super(
                new StupidCoreMachineContainer(player, tileentity),
                player,
                tileentity,
                new ResourceLocation(Reference.MOD_ID, "textures/gui/stupid_core_machine.png")
        );
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        drawEnergyStorage();
        drawProgress();
    }
    public void drawEnergyStorage(){
        int energyStoredScaled = this.getEnergyStoredScaled(73);
        this.drawTexturedModalRect(this.guiLeft + 7, this.guiTop + 7, 177, 18, 12, 72 - energyStoredScaled);
    }
    public void drawProgress(){
        int progressScaled = this.getProgressScaled(24);
        this.drawTexturedModalRect(this.guiLeft +58, this.guiTop +35, 177, 0, progressScaled, 17);
    }



}
