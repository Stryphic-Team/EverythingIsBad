package com.dna.everythingisbad.client;

import com.dna.everythingisbad.entity.EntityThreeHeadedSheep;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderThreeHeadedSheep extends RenderLiving<EntityThreeHeadedSheep> {
    public RenderThreeHeadedSheep(RenderManager renderManager) {
        super(renderManager,new ModelThreeHeadedSheep(),0.7f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityThreeHeadedSheep entity) {
        return new ResourceLocation("textures/entity/sheep/sheep.png");
    }


}
