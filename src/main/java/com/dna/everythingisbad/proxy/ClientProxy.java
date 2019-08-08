package com.dna.everythingisbad.proxy;

import com.dna.everythingisbad.init.ModEntities;
import com.dna.everythingisbad.utils.handlers.TestHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy{
    public void registerItemRenderer(Item item, int meta, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));

    }
    public void registerEntityRenderer(){
        ModEntities.initRenderer();
    }
    @Override
    public void init(FMLInitializationEvent event) {

        registerEntityRenderer();

    }
}
