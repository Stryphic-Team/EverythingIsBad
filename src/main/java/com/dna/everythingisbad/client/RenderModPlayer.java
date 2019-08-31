package com.dna.everythingisbad.client;

import com.dna.everythingisbad.entity.EntityJesus;
import com.dna.everythingisbad.entity.EntityModPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import javax.annotation.Resource;

public class RenderModPlayer extends RenderLivingBase {
    public RenderModPlayer(RenderManager renderManager){
        super(renderManager, new ModelPlayer(1,false),0.5f);
        this.addLayer(new LayerBipedArmor(this));
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        ResourceLocation resourcelocation = new ResourceLocation("everythingbad:textures/models/armor/camo_layer_1");
        return resourcelocation;
    }
}
