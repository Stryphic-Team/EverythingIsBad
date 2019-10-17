package com.dna.everythingisbad.network.messagestypes;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageHeartRateSync implements IMessage {
    public MessageHeartRateSync(){

    }

    private float heartRate;
    public MessageHeartRateSync(float heartRate){
        this.heartRate = heartRate;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        heartRate = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(heartRate);
    }

    public float getHeartRate() {
        return heartRate;
    }
}
