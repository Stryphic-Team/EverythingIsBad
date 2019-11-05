package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerPropertiesCapability;
import com.dna.everythingisbad.utils.ModConfig;
import com.dna.everythingisbad.utils.helpers.FormatHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class PlayerBankHandler extends PlayerHandlerBase {
    @Override
    public void playerTick(EntityPlayer player) {
        super.playerTick(player);
        int interestInterval = 240;
        World world = player.getEntityWorld();
        int worldTime = (int)world.getWorldTime();
        if(!world.isRemote && world.getWorldTime() % interestInterval == interestInterval-1) {
            PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES, null);
            if (playerProperties != null) {
                float currentBankBalance = playerProperties.getBankBalance();
                if(currentBankBalance > 0){
                    float newBankBalance = currentBankBalance + (playerProperties.bankInterestRate * currentBankBalance);
                    if(newBankBalance > 0){
                        playerProperties.setBankBalance(newBankBalance);
                        playerProperties.saveNBTData(player.getEntityData());
                        player.sendMessage(new TextComponentString("Interest of "+ FormatHelper.formatMoney(playerProperties.bankInterestRate * currentBankBalance)+" has been paid on your bank balance."));
                    }
                }else if(currentBankBalance < 0){
                    float newBankBalance = currentBankBalance + ((ModConfig.NEGATIVE_BALANCE_INTEREST / 100f) * currentBankBalance);
                    playerProperties.setBankBalance(newBankBalance);
                    playerProperties.saveNBTData(player.getEntityData());
                    player.sendMessage(new TextComponentString("Interest of "+ FormatHelper.formatMoney((ModConfig.NEGATIVE_BALANCE_INTEREST / 100f) * currentBankBalance)+" has been deducted from your bank balance."));
                }
            }
        }
    }
}
