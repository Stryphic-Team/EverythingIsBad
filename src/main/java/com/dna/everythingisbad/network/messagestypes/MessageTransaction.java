package com.dna.everythingisbad.network.messagestypes;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageTransaction implements IMessage {
    private float amount;
    private TransactionType transactionType;
    public MessageTransaction(){

    }
    public MessageTransaction(float amount,TransactionType transactionType){
        this.amount = amount;
        this.transactionType = transactionType;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        amount = buf.readFloat();
        transactionType = TransactionType.values()[buf.readInt()];
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(amount);
        buf.writeInt(transactionType.ordinal());
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
    public enum TransactionType{
        WITHDRAW,
        DEPOSIT,
        BALANCE
    }
}
