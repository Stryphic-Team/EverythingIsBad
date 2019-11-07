package com.dna.everythingisbad.commands.utils;

import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerPropertiesCapability;
import com.dna.everythingisbad.init.Religion;
import com.dna.everythingisbad.utils.helpers.FormatHelper;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerInteractionManager;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;

public class CommandOutputHelper {
    String[] Docs = new String[]{

    };
    public static void sendPlayerBalance(EntityPlayer player){
        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
        if(playerProperties != null) {
            float currentBalance = playerProperties.getBalance();
            player.sendMessage(new TextComponentString("Balance: " + FormatHelper.formatMoney(currentBalance)));
        }
    }
    public static void sendBankBalance(EntityPlayer player){
        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
        if(playerProperties != null) {
            float currentBalance = playerProperties.getBankBalance();
            player.sendMessage(new TextComponentString("Bank Balance: " + FormatHelper.formatMoney(currentBalance)));
        }
    }
    public static void sendPlayerReligion(EntityPlayer player){
        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);

        Religion currentReligion = Religion.values()[playerProperties.getReligion()];
        Style religionStyle = new Style().setColor(currentReligion.getTextFormatting());
        TextComponentString religionTextComponent = new TextComponentString(currentReligion.getDisplayName());
        religionTextComponent.setStyle(religionStyle);
        player.sendMessage(
                new TextComponentString("Religion: ").appendSibling(religionTextComponent)
        );

    }
    public static void sendPlayerTimesPooped(EntityPlayer player){
        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);

        int timesPooped = playerProperties.getTimesPooped();

        player.sendMessage(new TextComponentString("Times Pooped: "+timesPooped));
    }
    public static void sendPlayerBlindness(EntityPlayer player){
        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);

        boolean isBlind = playerProperties.isBlind();

        player.sendMessage(new TextComponentString("Blind: "+isBlind));
    }
    public static void sendPlayerCommonColdImmunity(EntityPlayer player){
        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);

        boolean hasCommonColdImmunity = playerProperties.hasCommonColdImmunity();

        player.sendMessage(new TextComponentString("Common Cold Immunity: "+hasCommonColdImmunity));
    }
    public static void sendBorder(EntityPlayer player){
        player.sendMessage(new TextComponentString("===========================================").setStyle(new Style().setColor(TextFormatting.GOLD)));
    }

    public static void sendAddictionLevel(EntityPlayer player){
        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);

        int addictionlvl = playerProperties.getAngelDustAddictionLvl();
        int tobacco_addiction_lvl = playerProperties.getTobaccoAddictionLvl();

        player.sendMessage(new TextComponentString("Angel Dust Addiction Lvl: " + addictionlvl));
        player.sendMessage(new TextComponentString("Tobacco Addiction Lvl: " + tobacco_addiction_lvl));
    }
    public static void sendStudentStatus(EntityPlayer player){
        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
        boolean isStudent = playerProperties.isStudent();
        player.sendMessage(new TextComponentString("Student: "+isStudent));
    }
    public static void sendPositiveMessage(EntityPlayer player,String message){
        player.sendMessage(new TextComponentString(message).setStyle(new Style().setColor(TextFormatting.GREEN)));
    }
    public static void sendTopBalance(EntityPlayer player,int page){
        MinecraftServer server = player.world.getMinecraftServer();
        if(server != null) {
            PlayerList players = server.getPlayerList();
            players.getOnlinePlayerNames();

            String[] playerData = players.getAvailablePlayerDat();
            ArrayList<EntityPlayerMP> allPlayers = new ArrayList<EntityPlayerMP>();
            for(String playerUUID:playerData){
                GameProfile profileByUUID = server.getPlayerProfileCache().getProfileByUUID(UUID.fromString(playerUUID));
                if(profileByUUID != null) {
                    EntityPlayerMP currentPlayer = new EntityPlayerMP(server, server.getWorld(player.dimension), profileByUUID, new PlayerInteractionManager(player.world));
                    NBTTagCompound nbtTagCompound = players.readPlayerDataFromFile(currentPlayer);
                    if(nbtTagCompound != null) {
                        currentPlayer.readFromNBT(nbtTagCompound);
                    }
                    allPlayers.add(currentPlayer);
                }
            }
            page = Math.min(page,allPlayers.size() / 5);
            page = Math.max(page,0);
            allPlayers.sort(new Comparator<EntityPlayerMP>() {
                @Override
                public int compare(EntityPlayerMP o1, EntityPlayerMP o2) {
                    PlayerProperties playerProperties1 = o1.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES, null);
                    PlayerProperties playerProperties2 = o2.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES, null);

                    if (playerProperties1 != null && playerProperties2 != null) {

                        return Math.round(playerProperties2.getBankBalance() - playerProperties1.getBankBalance());
                    }else{
                        return 0;
                    }
                }
            });
            sendBorder(player);
            for(int i = (page * 5);i<Math.min((page + 1) * 5,allPlayers.size());i++){
                EntityPlayer currentPlayer = allPlayers.get(i);
                PlayerProperties playerProperties = currentPlayer.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES, null);

                if (playerProperties != null) {
                    playerProperties.loadNBTData(currentPlayer.getEntityData());
                    float bankBalance = playerProperties.getBankBalance();

                    player.sendMessage(
                            new TextComponentString(currentPlayer.getDisplayNameString()).appendSibling(
                            new TextComponentString(" : ").setStyle(new Style().setColor(TextFormatting.GOLD))).appendSibling(
                            new TextComponentString(FormatHelper.formatMoney(bankBalance))).setStyle(new Style().setColor(TextFormatting.GREEN))
                    );

                }
            }
            player.sendMessage(
                    new TextComponentString((page + 1) + " / " + ((allPlayers.size() / 5) + 1)).setStyle(new Style().setColor(TextFormatting.GREEN))
            );
            sendBorder(player);

        }
    }
}
