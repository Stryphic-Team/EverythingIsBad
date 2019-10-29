package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerPropertiesCapability;
import com.dna.everythingisbad.utils.ModConfig;
import com.dna.everythingisbad.utils.RandomUtils;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerCollegeStudentHandler extends PlayerHandlerBase {
    @Override
    public void playerTick(EntityPlayer player) {
        super.playerTick(player);
    }

    @Override
    public void playerInitialization(EntityPlayer player) {
        super.playerInitialization(player);
        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
        if(RandomUtils.percentChance(ModConfig.STUDENT_CHANCE) && playerProperties != null){
            playerProperties.setStudent(true);
            playerProperties.saveNBTData(player.getEntityData());
        }

    }

    @Override
    public void playerPostInitialization(EntityPlayer player) {
        super.playerPostInitialization(player);
        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
        if(playerProperties != null){
            if(playerProperties.isStudent()){
                playerProperties.setBankBalance(playerProperties.getBalance() - RandomUtils.fromRangeF(1000f,20000f));
            }
        }
    }
}
