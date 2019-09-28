package com.dna.everythingisbad.client;

import com.dna.everythingisbad.entity.EntityAngel;
import com.dna.everythingisbad.entity.EntityThreeHeadedSheep;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.client.model.ModelBat;
import net.minecraft.client.renderer.entity.RenderBat;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderAngel extends RenderLiving<EntityAngel> {
    private static final ResourceLocation ANGEL_TEXTURE = new ResourceLocation(Reference.MOD_ID,"textures/entity/angel.png");

    public RenderAngel(RenderManager renderManager) {
        super(renderManager,new ModelBat(),0.5f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityAngel entity) {
        return ANGEL_TEXTURE;
    }

}
