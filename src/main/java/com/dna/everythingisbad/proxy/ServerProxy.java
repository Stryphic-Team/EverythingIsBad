package com.dna.everythingisbad.proxy;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.utils.handlers.ServerEventHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy extends CommonProxy{
    public void registerItemRenderer(Item item, int meta, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        Main.logger.debug("Server: Pre Intializing");
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        Main.logger.debug("Server: Intializing");

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        Main.logger.debug("Server: Post Intializing");
        MinecraftForge.EVENT_BUS.register(new ServerEventHandler());


    }
}
