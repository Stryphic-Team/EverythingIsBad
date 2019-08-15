package com.dna.everythingisbad.network;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.network.handlers.MessageDebugGivePoopHandler;
import com.dna.everythingisbad.network.messagestypes.MessageDebugGivePoop;
import com.dna.everythingisbad.reference.Reference;
import com.dna.everythingisbad.utils.ModConfig;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

    public static void init() {
        Main.logger.info("Registering network messages");
        INSTANCE.registerMessage(MessageDebugGivePoopHandler.class, MessageDebugGivePoop.class, 1, Side.SERVER);
        if(ModConfig.IS_DEBUG) {

        }
    }
}
