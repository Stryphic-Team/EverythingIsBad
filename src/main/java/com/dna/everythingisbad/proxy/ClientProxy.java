package com.dna.everythingisbad.proxy;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.client.RenderStupidTNT;
import com.dna.everythingisbad.entity.EntityStupidTNT;
import com.dna.everythingisbad.init.ModEntities;
import com.dna.everythingisbad.init.ModFluids;
import com.dna.everythingisbad.utils.handlers.ClientTimingHandler;
import com.dna.everythingisbad.utils.handlers.KeyHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{
    public void registerItemRenderer(Item item, int meta, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));

    }
    public void registerEntityRenderer(){

    }
    @Override
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);
        Main.logger.debug("Client: Pre Intializing");
        ModFluids.registerRenderers();
        RenderingRegistry.registerEntityRenderingHandler(EntityStupidTNT.class, RenderStupidTNT::new);


    }
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        Main.logger.debug("Client: Pre Intializing");
        ModEntities.initRenderer();


    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);//You need one of these oops...
        Main.logger.debug("Client: Pre Intializing");
        MinecraftForge.EVENT_BUS.register(new KeyHandler());
        MinecraftForge.EVENT_BUS.register(new ClientTimingHandler());

    }

}
