package com.dna.everythingisbad.network.messagestypes;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageTileSync implements IMessage {
    private int x;
    private int y;
    private int z;
    private boolean state;
    public MessageTileSync(){

    }
    public MessageTileSync(int x,int y,int z,boolean state){
        this.x = x;
        this.y = y;
        this.z = z;
        this.state = state;
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        setX(buf.readInt());
        setY(buf.readInt());
        setZ(buf.readInt());
        setState(buf.readBoolean());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(getX());
        buf.writeInt(getY());
        buf.writeInt(getZ());
        buf.writeBoolean(getState());
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

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    public BlockPos getBlockPos(){
        return new BlockPos(x,y,z);
    }
    public void setBlockPos(BlockPos blockPos){
        setX(blockPos.getX());
        setY(blockPos.getY());
        setZ(blockPos.getZ());
    }
}
