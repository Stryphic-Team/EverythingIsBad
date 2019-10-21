package com.dna.everythingisbad.network.handlers;

import com.dna.everythingisbad.network.messagestypes.MessageTileSync;
import com.dna.everythingisbad.tile.TileDeviceBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageTileSyncHandler implements IMessageHandler<MessageTileSync, IMessage> {
    @Override
    public IMessage onMessage(MessageTileSync message, MessageContext ctx) {
        World world = Minecraft.getMinecraft().player.getEntityWorld();
        BlockPos pos = message.getBlockPos();
        boolean active = message.getState();
        IBlockState iBlockState = world.getBlockState(pos);
        TileEntity tileEntity = world.getTileEntity(pos);
        if(tileEntity instanceof TileDeviceBase){
            TileDeviceBase tileDeviceBase = (TileDeviceBase)tileEntity;
            tileDeviceBase.setFinishedProgress(10);
            if(active){
                tileDeviceBase.setProgress(1);
            }else{
                tileDeviceBase.setProgress(0);
            }

            tileDeviceBase.updateBlockState();
        }
        return null;
    }
}
