package com.dna.everythingisbad.proxy;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModEntities;
import com.dna.everythingisbad.utils.handlers.KeyHandler;
import com.dna.everythingisbad.utils.handlers.TestHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{
    public void registerItemRenderer(Item item, int meta, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));

    }
    public void registerEntityRenderer(){
        ModEntities.initRenderer();
    }
    @Override
    public void preInit(FMLPreInitializationEvent event){
        Main.logger.debug("Client: Pre Intializing");
        super.preInit(event);
    }
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        Main.logger.debug("Client: Pre Intializing");
        registerEntityRenderer();

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);//You need one of these oops...
        Main.logger.debug("Client: Pre Intializing");
        MinecraftForge.EVENT_BUS.register(new KeyHandler());
        MinecraftForge.EVENT_BUS.register(new TestHandler());
    }

}
