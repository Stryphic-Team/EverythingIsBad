package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerPropertiesCapability;
import com.dna.everythingisbad.utils.helpers.FormatHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class PlayerMoneyHandler extends PlayerHandlerBase {
    @Override
    public void playerDied(EntityPlayer player) {
        super.playerDied(player);
        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
        if(playerProperties != null){
            playerProperties.setBalance(playerProperties.getBalance() * 0.25f);
            player.sendMessage(new TextComponentString("Your balance has been set to "+ FormatHelper.formatMoney(playerProperties.getBalance()) + ". You should have put your money in the bank.").setStyle(new Style().setColor(TextFormatting.RED)));
            playerProperties.saveNBTData(player.getEntityData());
        }
    }
}
