package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.Main;

import com.dna.everythingisbad.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;



public class TestHandler {
    int tick_count = 0;

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void timer(TickEvent.ClientTickEvent event){
        tick_count++;
        if(tick_count > 100){
//            ItemStack stack = new ItemStack(ModItems.POOP_ITEM);
//            Item item = stack.getItem();
//            Minecraft.getMinecraft().player.inventory.addItemStackToInventory(stack);
        }
    }
}
