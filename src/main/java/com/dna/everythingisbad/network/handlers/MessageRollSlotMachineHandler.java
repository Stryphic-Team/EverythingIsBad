package com.dna.everythingisbad.network.handlers;

import com.dna.everythingisbad.network.messagestypes.MessageRollSlotMachine;
import com.dna.everythingisbad.tile.misc.TileSlotMachine;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageRollSlotMachineHandler implements IMessageHandler<MessageRollSlotMachine, IMessage> {
    @Override
    public IMessage onMessage(MessageRollSlotMachine message, MessageContext ctx) {
        if(ctx.getServerHandler().player != null){
            WorldServer world = ctx.getServerHandler().player.getServerWorld();
            TileEntity tileEntity = world.getTileEntity(message.getBlockPos());
            if(tileEntity instanceof TileSlotMachine){
                TileSlotMachine tileSlotMachine = (TileSlotMachine)tileEntity;
                tileSlotMachine.startRoll();
            }
        }
        return null;
    }
}
