package com.dna.everythingisbad.network.messagestypes;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageRollSlotMachine implements IMessage {
    private int x;
    private int y;
    private int z;
    public MessageRollSlotMachine(){

    }
    public MessageRollSlotMachine(int x,int y,int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        setX(buf.readInt());
        setY(buf.readInt());
        setZ(buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(getX());
        buf.writeInt(getY());
        buf.writeInt(getZ());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
    public BlockPos getBlockPos(){
        return new BlockPos(getX(),getY(),getZ());
    }
    public void setBlockPos(BlockPos blockPos){
        setX(blockPos.getX());
        setY(blockPos.getY());
        setZ(blockPos.getZ());
    }
}
