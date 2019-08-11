package com.dna.everythingisbad.proxy;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.fluid.FluidDevilsPee;
import com.dna.everythingisbad.fluid.FluidDevilsPeeBlock;
import com.dna.everythingisbad.init.ModFluids;
import com.dna.everythingisbad.init.ModPotions;
import com.dna.everythingisbad.init.ModSmeltingRecipes;
import com.dna.everythingisbad.network.PacketHandler;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy implements IProxy{
    public void registerItemRenderer(Item item, int meta, String id) {}

    @Override
    public void preInit(FMLPreInitializationEvent event) {

        Main.logger.info("Pre Intializing");
    }

    @Override
    public void init(FMLInitializationEvent event) {

        Main.logger.info("Intializing");
        PacketHandler.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

        Main.logger.info("Post Intializing");
        ModSmeltingRecipes.init();
        ModPotions.init();





        //MinecraftForge.EVENT_BUS.register(new KeyHandler());
        //MinecraftForge.EVENT_BUS.register(new TestHandler());
    }
}
