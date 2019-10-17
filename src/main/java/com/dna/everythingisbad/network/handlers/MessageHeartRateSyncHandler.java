package com.dna.everythingisbad.network.handlers;

import com.dna.everythingisbad.network.messagestypes.MessageHeartRateSync;
import com.dna.everythingisbad.network.messagestypes.MessagePlayNote;
import com.dna.everythingisbad.utils.handlers.MidiHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageHeartRateSyncHandler implements IMessageHandler<MessageHeartRateSync, IMessage> {
    public static float clientHeartRate = 69f;
    @Override
    public IMessage onMessage(MessageHeartRateSync message, MessageContext ctx) {
        clientHeartRate = message.getHeartRate();
        return null;
    }
}
