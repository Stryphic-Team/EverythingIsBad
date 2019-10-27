package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.gui.GuiHandler;
import com.dna.everythingisbad.gui.GuiYellowOverlay;
import com.sun.javafx.geom.Vec4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerScreenYellowHandler extends PlayerHandlerBase {

    @Override
    public void clientPlayerJoined(EntityPlayer player) {
        super.clientPlayerJoined(player);
        Minecraft.getMinecraft().getMinecraft().getMinecraft().displayGuiScreen(new GuiYellowOverlay());
    }

    @Override
    public void clientPlayerTick(EntityPlayer player) {
        super.clientPlayerTick(player);
    }
}
