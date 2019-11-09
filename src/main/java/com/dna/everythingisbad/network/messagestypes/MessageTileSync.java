package com.dna.everythingisbad.network.messagestypes;

import com.dna.everythingisbad.tile.TileDeviceBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MessageTileSync implements IMessage {
    private int x;
    private int y;
    private int z;
    private int progress;
    private int energyStored;
    private int fluidStored;
    public MessageTileSync(){

    }
    public MessageTileSync(TileDeviceBase tileDeviceBase){
        this.x = tileDeviceBase.getPos().getX();
        this.y = tileDeviceBase.getPos().getY();
        this.z = tileDeviceBase.getPos().getZ();
        this.progress = tileDeviceBase.getProgress();
        this.fluidStored = tileDeviceBase.getFluidHandler() == null ? 0 : tileDeviceBase.getFluidHandler().getFluidTank().getFluidAmount();
        this.energyStored = tileDeviceBase.getEnergyHandler() == null ? 0 : tileDeviceBase.getEnergyHandler().getEnergyStored();
    }
    @Override
    public void fromBytes(ByteBuf buf) {
        setX(buf.readInt());
        setY(buf.readInt());
        setZ(buf.readInt());
        setEnergyStored(buf.readInt());
        setFluidStored(buf.readInt());
        setProgress(buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(getX());
        buf.writeInt(getY());
        buf.writeInt(getZ());
        buf.writeInt(getEnergyStored());
        buf.writeInt(getFluidStored());
        buf.writeInt(getProgress());

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
        return new BlockPos(x,y,z);
    }
    public void setBlockPos(BlockPos blockPos){
        setX(blockPos.getX());
        setY(blockPos.getY());
        setZ(blockPos.getZ());
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getEnergyStored() {
        return energyStored;
    }

    public void setEnergyStored(int energyStored) {
        this.energyStored = energyStored;
    }

    public int getFluidStored() {
        return fluidStored;
    }

    public void setFluidStored(int fluidStored) {
        this.fluidStored = fluidStored;
    }
}
