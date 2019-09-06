package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.network.messagestypes.MessageSyncMachineGui;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class GuiSync implements IMessageHandler<MessageSyncMachineGui, IMessage> {
    public static int ENERGY_STORED = 0;
    public static int FLUID_STORED = 0;
    public static String FLUID_TYPE = "";
    public static int PROGRESS = 0;

    @Override
    public IMessage onMessage(MessageSyncMachineGui message, MessageContext ctx) {
        ENERGY_STORED = message.getEnergyStored();
        PROGRESS = message.getProgress();
        FLUID_TYPE = message.getFluidType();
        FLUID_STORED = message.getFluidStored();
        return null;
    }
}
