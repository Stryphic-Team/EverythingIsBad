package com.dna.everythingisbad.init;


import com.dna.everythingisbad.item.ItemPoop;
import com.dna.everythingisbad.item.ItemPoopBrick;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<Item>();
    public static final ItemPoop POOP_ITEM = new ItemPoop("poop");
    public static final ItemPoopBrick POOP_BRICK_ITEM = new ItemPoopBrick("poop_brick");

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        for (Item item: ITEMS) {
            registerRender(item);
        }
    }

    private static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 3, new ModelResourceLocation( item.getRegistryName(), "inventory"));
    }

}
