package com.dna.everythingisbad.network.messagestypes;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.charset.Charset;

public class MessageSyncMachineGui implements IMessage {
    private String fluidType;
    private int fluidStored;
    private int energyStored;
    private int progress;
    public MessageSyncMachineGui(){

    }
    public MessageSyncMachineGui(String fluidType,int fluidStored,int energyStored,int progress){
        this.fluidType = fluidType;
        this.fluidStored = fluidStored;
        this.progress = progress;
        this.energyStored = energyStored;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        int fluid_type_length = buf.readInt();
        this.fluidType = buf.readCharSequence(fluid_type_length,Charset.defaultCharset()).toString();
        this.fluidStored = buf.readInt();
        this.energyStored = buf.readInt();
        this.progress = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {

        buf.writeInt(fluidType.getBytes().length);
        buf.writeCharSequence(fluidType.subSequence(0,fluidType.length()), Charset.defaultCharset());
        buf.writeInt(fluidStored);
        buf.writeInt(energyStored);
        buf.writeInt(progress);
    }

    public String getFluidType() {
        return fluidType;
    }

    public int getFluidStored() {
        return fluidStored;
    }

    public int getEnergyStored() {
        return energyStored;
    }

    public int getProgress() {
        return progress;
    }

    public void setFluidType(String fluidType) {
        this.fluidType = fluidType;
    }

    public void setFluidStored(int fluidStored) {
        this.fluidStored = fluidStored;
    }

    public void setEnergyStored(int energyStored) {
        this.energyStored = energyStored;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
