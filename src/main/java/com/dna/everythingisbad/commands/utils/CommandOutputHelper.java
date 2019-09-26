package com.dna.everythingisbad.commands.utils;

import com.dna.everythingisbad.entityproperties.InitializedPlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

public class CommandOutputHelper {
    public static void sendPlayerBalance(EntityPlayer player){
        PlayerProperties playerProperties = player.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);

        int currentBalance = playerProperties.getBalance();
        player.sendMessage(new TextComponentString("Your Balance: $"+String.valueOf(currentBalance)));
    }
}
