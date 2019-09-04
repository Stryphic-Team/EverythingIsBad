package com.dna.everythingisbad.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.main.Main;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.GLContext;

public class RenderYellow extends GuiScreen {
    public RenderYellow(){
        Minecraft.getMinecraft().getFramebuffer().framebufferColor[2] = 0;
    }
    public static void yelo(){
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0F,1.0F,0.0F,1.0F);
        RenderHelper.setColorBuffer(1.0F,1.0F,0.0F,1.0F);
        GlStateManager.popMatrix();
    }
}
