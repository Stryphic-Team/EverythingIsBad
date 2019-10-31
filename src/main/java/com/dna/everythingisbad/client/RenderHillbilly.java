package com.dna.everythingisbad.client;

import com.dna.everythingisbad.entity.EntityHillbilly;
import com.dna.everythingisbad.entity.EntityJesus;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;

public class RenderHillbilly extends RenderBiped<EntityHillbilly> {
    private static final ResourceLocation HILLBILLY_TEXTURE = new ResourceLocation(Reference.MOD_ID,"textures/entity/hillbilly.png");

    public RenderHillbilly(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelZombie(), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelZombie(0.5F, true);
                this.modelArmor = new ModelZombie(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */

    protected ResourceLocation getEntityTexture(EntityHillbilly entity)
    {
        return HILLBILLY_TEXTURE;
    }
}
