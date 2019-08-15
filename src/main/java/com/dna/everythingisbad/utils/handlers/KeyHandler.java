package com.dna.everythingisbad.utils.handlers;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.network.PacketHandler;
import com.dna.everythingisbad.network.messagestypes.MessageDebugGivePoop;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

//@Mod.EventBusSubscriber I think there can only be one of these registered

/**
 * This is is where the keybinds are handled
 */
public class KeyHandler {

    static final Minecraft mc = Minecraft.getMinecraft();


    private static KeyBinding poopkey;

    private static ArrayList<KeyBinding> keys = new ArrayList<KeyBinding>();

    /**
     * Binds the keybinds and registers the keys
     */
    public KeyHandler(){
        poopkey = new KeyBinding(Reference.PREFIX + "keybind.poopkey", Keyboard.KEY_P, Reference.PREFIX + "category." + Reference.MOD_ID);
        ClientRegistry.registerKeyBinding(poopkey);
        addKeys();
    }

    /**
     * Checks for key inputs and triggers server events
     * @param event
     */
    @SubscribeEvent
    public void onKeyInput(KeyInputEvent event){

        EntityPlayer player = FMLClientHandler.instance().getClient().player;
        for (int i=0;i<keys.size();i++) {
            KeyBinding keyBindings = keys.get(i);
            //Main.logger.info("Going thru keys");
            int button = keyBindings.getKeyCode();
            if ( keyBindings.isPressed()) {

                //Main.logger.info(keyBindings.getKeyDescription());

                if (keyBindings.getKeyDescription().equals(Reference.PREFIX + "keybind.poopkey")) {
                    Main.logger.info("Poop key pressed!");
//                    ItemStack stack = new ItemStack(ModItems.POOP_ITEM,1,3);
//                    ItemPoop item = (ItemPoop)stack.getItem();
//                    FMLClientHandler.instance().getClient().player.inventory.addItemStackToInventory(stack);
                    PacketHandler.INSTANCE.sendToServer(new MessageDebugGivePoop(2));
                }
            }
        }
    }

    public static void addKeys() {
        keys.add(poopkey);
    }

    private boolean in_server = false;
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void joinedServer(PlayerEvent.PlayerLoggedInEvent event){
        in_server = true;
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void leaveServer(PlayerEvent.PlayerLoggedOutEvent event){
        in_server = false;
    }

}
