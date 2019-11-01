package com.dna.everythingisbad.network.handlers;

import com.dna.everythingisbad.network.messagestypes.MessagePlayNote;
import com.dna.everythingisbad.utils.handlers.MidiHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessagePlayNoteHandler implements IMessageHandler<MessagePlayNote, IMessage> {

    @Override
    public IMessage onMessage(MessagePlayNote message, MessageContext ctx) {
        EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
        MidiHandler.PlayNote(message.getNote(),message.getInstrumentId(),(EntityLivingBase)serverPlayer);
        return null;
    }
}
