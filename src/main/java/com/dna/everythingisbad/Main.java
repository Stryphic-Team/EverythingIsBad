package com.dna.everythingisbad;

        import com.dna.everythingisbad.proxy.CommonProxy;
        import com.dna.everythingisbad.reference.Reference;
        import net.minecraft.init.Blocks;
        import net.minecraftforge.fml.common.Mod;
        import net.minecraftforge.fml.common.Mod.EventHandler;
        import net.minecraftforge.fml.common.SidedProxy;
        import net.minecraftforge.fml.common.event.FMLInitializationEvent;
        import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
        import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class Main
{

    public static Logger logger;
    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.CLIENT_PROXY_CLASS)
    public static CommonProxy proxy;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
