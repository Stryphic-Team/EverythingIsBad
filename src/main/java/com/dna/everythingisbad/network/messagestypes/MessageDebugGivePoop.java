package com.dna.everythingisbad.network.messagestypes;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageDebugGivePoop implements IMessage {
    public MessageDebugGivePoop(){}

    private int toSend;
    public MessageDebugGivePoop(int toSend) {
        this.toSend = toSend;
    }
    @Override public void toBytes(ByteBuf buf) {
        buf.writeInt(toSend);
    }

    @Override public void fromBytes(ByteBuf buf) {
        toSend = buf.readInt();
    }
}
