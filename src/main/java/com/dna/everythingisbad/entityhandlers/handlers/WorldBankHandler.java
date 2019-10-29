package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.WorldHandlerBase;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerPropertiesCapability;
import com.dna.everythingisbad.utils.ModConfig;
import com.dna.everythingisbad.utils.helpers.FormatHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class WorldBankHandler extends WorldHandlerBase {
    @Override
    public void worldTick(World world) {
        int interestInterval = 24000;
        super.worldTick(world);
        MinecraftServer server = world.getMinecraftServer();
        if(world.getTotalWorldTime() % interestInterval == interestInterval-1) {
            if (server != null) {
                PlayerList playerList = world.getMinecraftServer().getPlayerList();
                String[] onlinePlayers = playerList.getOnlinePlayerNames();
                for (String playerName : onlinePlayers) {
                    EntityPlayerMP currentPlayer = playerList.getPlayerByUsername(playerName);
                    if (currentPlayer != null) {
                        PlayerProperties playerProperties = currentPlayer.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES, null);
                        if (playerProperties != null) {
                            float currentBankBalance = playerProperties.getBankBalance();
                            if(currentBankBalance > 0){
                                float newBankBalance = currentBankBalance + (playerProperties.bankInterestRate * currentBankBalance);
                                if(newBankBalance > 0){
                                    playerProperties.setBankBalance(newBankBalance);
                                    playerProperties.saveNBTData(currentPlayer.getEntityData());
                                    currentPlayer.sendMessage(new TextComponentString("Interest of "+ FormatHelper.formatMoney(playerProperties.bankInterestRate * currentBankBalance)+" has been paid on your bank balance."));
                                }
                            }else if(currentBankBalance < 0){
                                float newBankBalance = currentBankBalance + ((ModConfig.NEGATIVE_BALANCE_INTEREST / 100f) * currentBankBalance);
                                playerProperties.setBankBalance(newBankBalance);
                                playerProperties.saveNBTData(currentPlayer.getEntityData());
                                currentPlayer.sendMessage(new TextComponentString("Interest of "+ FormatHelper.formatMoney((ModConfig.NEGATIVE_BALANCE_INTEREST / 100f) * currentBankBalance)+" has been deducted from your bank balance."));
                            }
                        }
                    }
                }
            }
        }
    }


}
