package com.dna.everythingisbad.client;

import com.dna.everythingisbad.entity.EntityStupidTNT;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderTNTPrimed;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderStupidTNT extends RenderTNTPrimed {
    public static final IRenderFactory<EntityStupidTNT> FACTORY = new Factory();
    public static final ResourceLocation texture = new ResourceLocation("eib:stupid_tnt");
    public RenderStupidTNT(RenderManager renderManagerIn) {
        super(renderManagerIn);

    }
    private static class Factory implements IRenderFactory<EntityStupidTNT> {

        @Override
        public Render<? super EntityStupidTNT> createRenderFor(RenderManager manager) {
            return new RenderStupidTNT(manager);
        }
    }
    @Override
    protected ResourceLocation getEntityTexture(EntityTNTPrimed entity)
    {
        return texture;
    }
}
