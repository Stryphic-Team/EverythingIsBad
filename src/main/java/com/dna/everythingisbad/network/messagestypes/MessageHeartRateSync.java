package com.dna.everythingisbad.network.messagestypes;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageHeartRateSync implements IMessage {
    public MessageHeartRateSync(){

    }

    private int heartRate;
    public MessageHeartRateSync(int heartRate){
        this.heartRate = heartRate;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        heartRate = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(heartRate);
    }

    public int getHeartRate() {
        return heartRate;
    }
}
