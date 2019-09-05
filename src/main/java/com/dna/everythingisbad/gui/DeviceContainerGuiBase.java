package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.tile.TileDeviceBase;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class DeviceContainerGuiBase extends GuiContainer {
    public static final int WIDTH = 176;
    public static final int HEIGHT = 166;
    private final InventoryPlayer player;
    private final TileDeviceBase tileEntity;
    protected static ResourceLocation background;

    public DeviceContainerGuiBase(DeviceContainerBase deviceContainerBase,InventoryPlayer player, TileDeviceBase tileentity,ResourceLocation background) {
        super(deviceContainerBase);
        this.background = background;
        xSize = WIDTH;
        ySize = HEIGHT;
        this.player = player;
        this.tileEntity = tileentity;
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 18, this.ySize - 96 + 2, 4210752);
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
    protected int getEnergyStoredScaled(int pixels)
    {
        int i = this.tileEntity.getEnergyHandler().getEnergyStored();
        int j = this.tileEntity.getEnergyHandler().getMaxEnergyStored();
        float ratio = (float)i / (float)j;
        return i != 0 && j != 0 ? (int)(ratio * (float)pixels): 0;
    }
    protected int getProgressScaled(int pixels)
    {
        int i = this.tileEntity.getProgress();
        int j = this.tileEntity.getFinishedProgress();
        float ratio = (float)i / (float)j;
        return i != 0 && j != 0 ? (int)(ratio * (float)pixels): 0;
    }
}
