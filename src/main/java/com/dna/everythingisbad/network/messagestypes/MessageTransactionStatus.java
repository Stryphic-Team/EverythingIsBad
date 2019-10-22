package com.dna.everythingisbad.network.messagestypes;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageTransactionStatus implements IMessage {
    private boolean pass;
    private int balance;
    private int bankBalance;
    public MessageTransactionStatus(){

    }
    public MessageTransactionStatus(boolean pass,int balance,int bankBalance){
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

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(int bankBalance) {
        this.bankBalance = bankBalance;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pass = buf.readBoolean();
        balance = buf.readInt();
        bankBalance = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(pass);
        buf.writeInt(balance);
        buf.writeInt(bankBalance);
    }
}
