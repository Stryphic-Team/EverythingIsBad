package com.dna.everythingisbad.network;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.gui.GuiSync;
import com.dna.everythingisbad.network.handlers.MessageDebugGivePoopHandler;
import com.dna.everythingisbad.network.handlers.MessageHeartRateSyncHandler;
import com.dna.everythingisbad.network.handlers.MessagePlayNoteHandler;
import com.dna.everythingisbad.network.handlers.MessageRollSlotMachineHandler;
import com.dna.everythingisbad.network.messagestypes.*;
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
        INSTANCE.registerMessage(MessagePlayNoteHandler.class, MessagePlayNote.class, 2, Side.SERVER);
        INSTANCE.registerMessage(GuiSync.class, MessageSyncMachineGui.class, 3, Side.CLIENT);
        INSTANCE.registerMessage(MessageRollSlotMachineHandler.class, MessageRollSlotMachine.class, 4, Side.SERVER);
        INSTANCE.registerMessage(MessageHeartRateSyncHandler.class, MessageHeartRateSync.class, 5, Side.CLIENT);

        if(ModConfig.IS_DEBUG) {

        }
    }
}
