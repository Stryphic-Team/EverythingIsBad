package com.dna.everythingisbad.network.messagestypes;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessagePlayNote implements IMessage {

    public MessagePlayNote(){

    }
    private int note;
    private int instrumentId;
    public MessagePlayNote(int note,int instrument_id){
        this.note = note;
        this.instrumentId = instrument_id;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        note = buf.readInt();
        instrumentId = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
         buf.writeInt(note);
         buf.writeInt(instrumentId);
    }
    public int getNote() {
        return note;
    }
    public int getInstrumentId(){
        return instrumentId;
    }

}
