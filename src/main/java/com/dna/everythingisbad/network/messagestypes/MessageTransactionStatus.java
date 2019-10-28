package com.dna.everythingisbad.network.messagestypes;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageTransactionStatus implements IMessage {
    private boolean pass;
    private float balance;
    private float bankBalance;
    public MessageTransactionStatus(){

    }
    public MessageTransactionStatus(boolean pass,float balance,float bankBalance){
        this.pass = pass;
        this.balance = balance;
        this.bankBalance = bankBalance;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(float bankBalance) {
        this.bankBalance = bankBalance;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pass = buf.readBoolean();
        balance = buf.readFloat();
        bankBalance = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(pass);
        buf.writeFloat(balance);
        buf.writeFloat(bankBalance);
    }
}
