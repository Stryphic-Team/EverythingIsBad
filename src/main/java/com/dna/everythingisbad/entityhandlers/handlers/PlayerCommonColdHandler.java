package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.entityproperties.InitializedPlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.utils.ModConfig;
import com.dna.everythingisbad.utils.RandomUtils;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerCommonColdHandler extends PlayerHandlerBase {
    @Override
    public void playerInitialization(EntityPlayer player) {
        super.playerInitialization(player);
        PlayerProperties playerProperties = player.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);
        if(playerProperties != null) {
            if (RandomUtils.fromRangeI(1,ModConfig.COMMON_COLD_CHANCE) == 1) {
                playerProperties.setHasCommonColdImmunity(false);
            } else {
                playerProperties.setHasCommonColdImmunity(true);
            }
            playerProperties.saveNBTData(player.getEntityData());
        }

    }
}
