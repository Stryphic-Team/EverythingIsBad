package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.gui.container.DeviceContainerBase;
import com.dna.everythingisbad.gui.elements.ElementBase;
import com.dna.everythingisbad.gui.elements.ElementButton;
import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.tile.TileDeviceBase;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.ArrayList;

public abstract class DeviceContainerGuiBase extends GuiContainer {
    public static final int WIDTH = 176;
    public static final int HEIGHT = 166;
    private int mouseX;
    private int mouseY;
    private final InventoryPlayer player;
    private final TileDeviceBase tileEntity;
    protected static ResourceLocation background = new ResourceLocation(Reference.MOD_ID,"textures/gui/elements/container_base.png");
    protected ArrayList<ElementBase> guiElements;

    public DeviceContainerGuiBase(DeviceContainerBase deviceContainerBase, InventoryPlayer player, TileDeviceBase tileentity) {
        super(deviceContainerBase);
        xSize = WIDTH;
        ySize = HEIGHT;
        this.player = player;
        this.tileEntity = tileentity;
        guiElements = new ArrayList<ElementBase>();
    }

    @Override
    public void initGui() {
        super.initGui();
        init();
    }

    public abstract void init();
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {

        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 4, this.ySize - 96 + 2, 4210752);
        this.fontRenderer.drawString(tileEntity.getDisplayName().getUnformattedText(),4,4,4210752);
        for(ElementBase element:guiElements){
            if(element.isVisible())  element.drawForeground();
        }
        this.renderHoveredToolTip(mouseX-guiLeft, mouseY-guiTop);


        //drawHoveringText("Fuck",200,200);
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        for(ElementBase element:guiElements){
            if(element.isVisible()) element.drawElement();
        }
        for(GuiButton button: buttonList){
            button.drawButton(mc,mouseX+guiLeft,mouseY+guiTop,0);
        }
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }


    public InventoryPlayer getPlayer() {
        return player;
    }

    public TileDeviceBase getTileEntity() {
        return tileEntity;
    }
    //credit to COFH Team
    //I don't have any idea how this works but I like it
    public void drawSizedTexturedModalRect(int x, int y, int u, int v, int width, int height, float texW, float texH) {

        float texU = 1 / texW;
        float texV = 1 / texH;
        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(x, y + height, this.zLevel).tex((u) * texU, (v + height) * texV).endVertex();
        buffer.pos(x + width, y + height, this.zLevel).tex((u + width) * texU, (v + height) * texV).endVertex();
        buffer.pos(x + width, y, this.zLevel).tex((u + width) * texU, (v) * texV).endVertex();
        buffer.pos(x, y, this.zLevel).tex((u) * texU, (v) * texV).endVertex();
        Tessellator.getInstance().draw();
    }
    public void bindTexture(ResourceLocation texture) {

        mc.renderEngine.bindTexture(texture);
    }
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        for(ElementBase element:guiElements){
            if(element.isVisible()) element.keyTyped(typedChar,keyCode);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        for(ElementBase element:guiElements){
            if(element.isVisible()) element.mouseClicked(mouseX,mouseY,mouseButton);
        }
    }


    public void drawHoveringText(int x, int y, String text){
        this.drawHoveringText(text,x,y);
    }
    public void drawText(int x,int y,String text){
        this.fontRenderer.drawString(text,x,y,0x202020);
    }
    public FontRenderer getFontRenderer(){
        return this.fontRenderer;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void buttonPressed(ElementButton elementButton) {
    }
}
