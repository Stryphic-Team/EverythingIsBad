package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

import static java.lang.System.out;

@Mod.EventBusSubscriber
public class KeyHandler {
    @SubscribeEvent
    public void pickupItem(EntityItemPickupEvent event) {
        Main.logger.info("Item picked up!");
    }

    private static KeyBinding poopkey;

    public KeyHandler(){
        poopkey = new KeyBinding(Reference.PREFIX + "keybind.poopkey", Keyboard.KEY_P, Reference.PREFIX + "category." + Reference.MOD_ID);
        ClientRegistry.registerKeyBinding(poopkey);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event){
        EntityPlayer player = FMLClientHandler.instance().getClient().player;

    }
}
