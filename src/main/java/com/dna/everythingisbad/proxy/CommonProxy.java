package com.dna.everythingisbad.proxy;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.utils.handlers.KeyHandler;
import com.dna.everythingisbad.utils.handlers.TestHandler;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CommonProxy implements IProxy{
    public void registerItemRenderer(Item item, int meta, String id) {}

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        Main.logger.info("Pre Intializing");
    }

    @Override
    public void init(FMLInitializationEvent event) {

        Main.logger.info("Intializing");
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

        Main.logger.info("Post Intializing");
    }
}
