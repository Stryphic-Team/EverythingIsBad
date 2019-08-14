package com.dna.everythingisbad.client;

import com.dna.everythingisbad.entity.EntityStupidTNT;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderStupidTNT implements IRenderFactory<EntityStupidTNT> {

    public static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID+":textures/blocks/stupid_tnt.png");


    public void doRender(EntityStupidTNT entity, double x, double y, double z, float entityYaw, float partialTicks){

    }
    @Override
    public Render<? super EntityStupidTNT> createRenderFor(RenderManager manager) {

        return null;
    }
}
