package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.WorldHandlerBase;
import com.dna.everythingisbad.entityproperties.InitializedPlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
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
                        PlayerProperties playerProperties = currentPlayer.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES, null);
                        if (playerProperties != null) {
                            int currentBankBalance = playerProperties.getBankBalance();
                            if(currentBankBalance > 0){
                                int newBankBalance = currentBankBalance + Math.round(playerProperties.bankInterestRate * (float)currentBankBalance);
                                if(newBankBalance > 0){
                                    playerProperties.setBankBalance(newBankBalance);
                                    playerProperties.saveNBTData(currentPlayer.getEntityData());
                                    currentPlayer.sendMessage(new TextComponentString("Interest has been paid on your bank balance."));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
