package com.dna.everythingisbad.network.handlers;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.network.messagestypes.MessageTest;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageTestHandler implements IMessageHandler<MessageTest, IMessage> {
    @Override
    public IMessage onMessage(MessageTest message, MessageContext ctx) {
        Main.logger.info("Got Message");
        return null;
    }
}
