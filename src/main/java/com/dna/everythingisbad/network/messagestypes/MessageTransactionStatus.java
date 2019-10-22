package com.dna.everythingisbad.network.messagestypes;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageTransactionStatus implements IMessage {
    private boolean pass;
    public MessageTransactionStatus(){

    }
    public MessageTransactionStatus(boolean pass){
        this.pass = pass;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        pass = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(pass);
    }
}
