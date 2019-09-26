package com.dna.everythingisbad.network.handlers;

import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.network.messagestypes.MessageDebugGivePoop;
import com.dna.everythingisbad.utils.ModConfig;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageDebugGivePoopHandler implements IMessageHandler<MessageDebugGivePoop, IMessage> {

    @Override
    public IMessage onMessage(MessageDebugGivePoop message, MessageContext ctx) {
        EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
        serverPlayer.getServerWorld().addScheduledTask(() -> {
            if (ModConfig.IS_DEBUG){
                serverPlayer.inventory.addItemStackToInventory(new ItemStack(ModItems.POOP_ITEM, 1,3));
            }
        });
        return null;
    }
}
