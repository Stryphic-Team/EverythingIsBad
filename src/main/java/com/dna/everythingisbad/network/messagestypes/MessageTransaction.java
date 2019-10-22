package com.dna.everythingisbad.network.messagestypes;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageTransaction implements IMessage {
    private int amount;
    private TransactionType transactionType;
    public MessageTransaction(){

    }
    public MessageTransaction(int amount,TransactionType transactionType){
        this.amount = amount;
        this.transactionType = transactionType;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        amount = buf.readInt();
        transactionType = TransactionType.values()[buf.readInt()];
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(amount);
        buf.writeInt(transactionType.ordinal());
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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
        DEPOSIT
    }
}
