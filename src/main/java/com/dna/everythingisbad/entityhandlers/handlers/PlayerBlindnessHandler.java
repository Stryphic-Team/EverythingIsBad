package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerPropertiesCapability;
import com.dna.everythingisbad.utils.ModConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PlayerBlindnessHandler  extends PlayerHandlerBase {
    @Override
    public void playerRespawn(EntityPlayer player) {
        super.playerRespawn(player);
        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
        if(playerProperties != null) {

            if (random.nextInt(ModConfig.BLINDNESS_CHANCE) == 1) {
                playerProperties.setBlind(true);

            } else {
                playerProperties.setBlind(false);
            }
            playerProperties.saveNBTData(player.getEntityData());

            boolean isBlind = playerProperties.isBlind();
            if (isBlind) {
                //player.addSuffix(new TextComponentString(" [Blind]").setStyle(new Style().setColor(TextFormatting.DARK_RED)));
                player.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 100000));
            }
        }

    }

    @Override
    public void playerJoined(EntityPlayer player) {
        super.playerJoined(player);
        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
        if(playerProperties != null) {

            boolean isBlind = playerProperties.isBlind();
            if (isBlind) {
                //player.addSuffix(new TextComponentString(" [Blind]").setStyle(new Style().setColor(TextFormatting.DARK_RED)));
                player.addPotionEffect(new PotionEffect(Potion.getPotionById(15), 100000));
            }
        }
    }

}
