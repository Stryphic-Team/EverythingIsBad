package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.entityproperties.InitializedPlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.init.Religion;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;

public class PlayerReligionHandler extends PlayerHandlerBase {
    @Override
    public void playerInitialization(EntityPlayer player) {
        super.playerInitialization(player);
        PlayerProperties playerProperties = player.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);
        if(playerProperties != null) {
            int randomIndex = random.nextInt(Religion.values().length);
            playerProperties.setReligion(randomIndex);
            playerProperties.saveNBTData(player.getEntityData());
        }
    }

    @Override
    public void playerPostInitialization(EntityPlayer player) {
        super.playerPostInitialization(player);
        PlayerProperties playerProperties = player.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);
        if(playerProperties != null) {
            int playerReligion = playerProperties.getReligion();
            Religion[] rel = Religion.values(); // That's how you're supposed to define a array in java, lol
            for (Religion religion : rel) {
                if (religion.ordinal() == playerReligion) {
                    player.addSuffix(new TextComponentString(" [" +
                            religion.getDisplayName() + "]").setStyle(new Style().setColor(religion.getTextFormatting())));
                }
            }
        }
    }

    @Override
    public void playerRespawn(EntityPlayer player) {
        super.playerRespawn(player);

        PlayerProperties playerProperties = player.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);
        if(playerProperties != null) {
            int playerReligion = playerProperties.getReligion();
            Religion[] rel = Religion.values(); // That's how you're supposed to define a array in java, lol
            for (Religion religion : rel) {
                if (religion.ordinal() == playerReligion) {
                    player.addSuffix(new TextComponentString(" [" +
                            religion.getDisplayName() + "]").setStyle(new Style().setColor(religion.getTextFormatting())));
                }
            }
        }
    }
}
