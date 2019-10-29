package com.dna.everythingisbad.commands.utils;

import com.dna.everythingisbad.entityproperties.InitializedPlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.init.Religion;
import com.dna.everythingisbad.utils.helpers.FormatHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class CommandOutputHelper {
    String[] Docs = new String[]{

    };
    public static void sendPlayerBalance(EntityPlayer player){
        PlayerProperties playerProperties = player.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);
        if(playerProperties != null) {
            float currentBalance = playerProperties.getBalance();
            player.sendMessage(new TextComponentString("Balance: " + FormatHelper.formatMoney(currentBalance)));
        }
    }
    public static void sendBankBalance(EntityPlayer player){
        PlayerProperties playerProperties = player.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);
        if(playerProperties != null) {
            float currentBalance = playerProperties.getBankBalance();
            player.sendMessage(new TextComponentString("Bank Balance: " + FormatHelper.formatMoney(currentBalance)));
        }
    }
    public static void sendPlayerReligion(EntityPlayer player){
        PlayerProperties playerProperties = player.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);

        Religion currentReligion = Religion.values()[playerProperties.getReligion()];
        Style religionStyle = new Style().setColor(currentReligion.getTextFormatting());
        TextComponentString religionTextComponent = new TextComponentString(currentReligion.getDisplayName());
        religionTextComponent.setStyle(religionStyle);
        player.sendMessage(
                new TextComponentString("Religion: ").appendSibling(religionTextComponent)
        );

    }
    public static void sendPlayerTimesPooped(EntityPlayer player){
        PlayerProperties playerProperties = player.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);

        int timesPooped = playerProperties.getTimesPooped();

        player.sendMessage(new TextComponentString("Times Pooped: "+timesPooped));
    }
    public static void sendPlayerBlindness(EntityPlayer player){
        PlayerProperties playerProperties = player.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);

        boolean isBlind = playerProperties.isBlind();

        player.sendMessage(new TextComponentString("Blind: "+isBlind));
    }
    public static void sendPlayerCommonColdImmunity(EntityPlayer player){
        PlayerProperties playerProperties = player.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);

        boolean hasCommonColdImmunity = playerProperties.hasCommonColdImmunity();

        player.sendMessage(new TextComponentString("Common Cold Immunity: "+hasCommonColdImmunity));
    }
    public static void sendBorder(EntityPlayer player){
        player.sendMessage(new TextComponentString("===========================================").setStyle(new Style().setColor(TextFormatting.GOLD)));
    }

    public static void sendAddictionLevel(EntityPlayer player){
        PlayerProperties playerProperties = player.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);

        int addictionlvl = playerProperties.getAngelDustAddictionLvl();
        int tobacco_addiction_lvl = playerProperties.getTobaccoAddictionLvl();

        player.sendMessage(new TextComponentString("Angel Dust Addiction Lvl: " + addictionlvl));
        player.sendMessage(new TextComponentString("Tobacco Addiction Lvl: " + tobacco_addiction_lvl));
    }
}
