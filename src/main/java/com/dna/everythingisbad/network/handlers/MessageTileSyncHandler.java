package com.dna.everythingisbad.network.handlers;

import com.dna.everythingisbad.network.messagestypes.MessageTileSync;
import com.dna.everythingisbad.tile.TileDeviceBase;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class MessageTileSyncHandler implements IMessageHandler<MessageTileSync, IMessage> {
    @Override
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(MessageTileSync message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().world;
        BlockPos pos = message.getBlockPos();
        if(world != null) {
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof TileDeviceBase) {
                TileDeviceBase tileDeviceBase = (TileDeviceBase) tileEntity;
                if(tileDeviceBase.getEnergyHandler() != null) {
                    tileDeviceBase.getEnergyHandler().setEnergyStorage(message.getEnergyStored());
                }

                tileDeviceBase.setProgress(message.getProgress());

            }
        }
        return null;
    }
}
