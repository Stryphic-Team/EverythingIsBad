package com.dna.everythingisbad.entityproperties;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class PlayerPropertiesCapability {

    @CapabilityInject(PlayerProperties.class)
    public static Capability<PlayerProperties> PLAYER_PROPERTIES = null;

    public static PlayerProperties getPlayerProperties(EntityPlayer player){
        return player.getCapability(PLAYER_PROPERTIES,null);
    }
}
