package com.dna.everythingisbad.client;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;

public class RenderJesus extends RenderZombie {
    public RenderJesus(RenderManager renderManagerIn) {
        super(renderManagerIn);
        layerRenderers.remove(3);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this);
        addLayer(layerbipedarmor);
        // DEBUG
    }
}
