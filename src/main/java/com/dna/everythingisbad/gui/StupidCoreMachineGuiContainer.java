package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.tile.TileStupidCoreMachine;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class StupidCoreMachineGuiContainer extends GuiContainer {
    public static final int WIDTH = 176;
    public static final int HEIGHT = 166;
    private final InventoryPlayer player;
    private final TileStupidCoreMachine tileEntity;

    private static final ResourceLocation background = new ResourceLocation(Reference.MOD_ID, "textures/gui/stupid_core_machine.png");
    public StupidCoreMachineGuiContainer(InventoryPlayer player, TileStupidCoreMachine tileentity) {
        super(new StupidCoreMachineContainer(player,tileentity));

        xSize = WIDTH;
        ySize = HEIGHT;
        this.player = player;
        this.tileEntity = tileentity;
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        //String tileName = this.tileentity.getDisplayName().getUnformattedText();
        //this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) -5, 6, 4210752);
        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 18, this.ySize - 96 + 2, 4210752);
        //this.fontRenderer.drawString(Integer.toString(this.tileEntity.getEnergyStored()), 115, 72, 4210752);
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int k = this.getEnergyStoredScaled(73);
        this.drawTexturedModalRect(this.guiLeft + 7, this.guiTop + 7, 177, 20, 12, 73 - k);
    }
    private int getEnergyStoredScaled(int pixels)
    {
        int i = this.tileEntity.getEnergyStored();
        int j = this.tileEntity.getMaxEnergyStored();
        float ratio = (float)i / (float)j;
        //Main.logger.info(ratio);

        return i != 0 && j != 0 ? (int)(ratio * (float)pixels): 0;
    }
}
