package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.tile.TileStupidCoreMachine;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class MachineGuiContainer extends GuiContainer {
    public static final int WIDTH = 176;
    public static final int HEIGHT = 166;
    private final InventoryPlayer player;
    private final TileStupidCoreMachine tileentity;

    private static final ResourceLocation background = new ResourceLocation(Reference.MOD_ID, "textures/gui/stupid_core_machine.png");
    public MachineGuiContainer(InventoryPlayer player, TileStupidCoreMachine tileentity) {
        super(new MachineContainer(player,tileentity));

        xSize = WIDTH;
        ySize = HEIGHT;
        this.player = player;
        this.tileentity = tileentity;
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        //String tileName = this.tileentity.getDisplayName().getUnformattedText();
        //this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) -5, 6, 4210752);
        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 7, this.ySize - 96 + 2, 4210752);
        this.fontRenderer.drawString(Integer.toString(this.tileentity.getEnergyStored()), 115, 72, 4210752);
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
