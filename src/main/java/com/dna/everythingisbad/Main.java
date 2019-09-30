package com.dna.everythingisbad;


import com.dna.everythingisbad.init.ModCommands;
import com.dna.everythingisbad.proxy.IProxy;
import com.dna.everythingisbad.reference.Reference;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;



@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION,dependencies = "required:forge@[14.21.1.2768,);required-after:cofhcore@[4.6.0,4.7.0);")

public class Main
{

    @Mod.Instance
    public static Main instance;

    public static Logger logger;
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;
    static {
        FluidRegistry.enableUniversalBucket();
    }
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);
    }
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
    @EventHandler
    public static void fmlServerStarting(FMLServerStartingEvent event){
        ModCommands.init(event);
    }

}
