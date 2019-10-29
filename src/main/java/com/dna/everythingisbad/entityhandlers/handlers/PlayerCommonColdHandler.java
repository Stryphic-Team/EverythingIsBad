package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerPropertiesCapability;
import com.dna.everythingisbad.init.ModPotions;
import com.dna.everythingisbad.utils.ModConfig;
import com.dna.everythingisbad.utils.RandomUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class PlayerCommonColdHandler extends PlayerHandlerBase {
    @Override
    public void playerInitialization(EntityPlayer player) {
        super.playerInitialization(player);
        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
        if(playerProperties != null) {
            if (RandomUtils.fromRangeI(1,ModConfig.COMMON_COLD_CHANCE) == 1) {
                playerProperties.setHasCommonColdImmunity(false);
            } else {
                playerProperties.setHasCommonColdImmunity(true);
            }
            playerProperties.saveNBTData(player.getEntityData());
        }
    }

    @Override
    public void playerTick(EntityPlayer player) {
        super.playerTick(player);
        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
        if(playerProperties != null) {
            World world = player.getEntityWorld();
            boolean Immune = playerProperties.hasCommonColdImmunity();
            Biome bedBiome;
            if(player.getBedLocation() != null){
                bedBiome = world.getBiome(player.getBedLocation());
                Immune = bedBiome.getTempCategory() == Biome.TempCategory.COLD;
            }
            Biome currentBiome = world.getBiome(player.getPosition());
            if (!Immune) {

                if (player.ticksExisted % ModConfig.COMMON_COLD_CHANCE == ModConfig.COMMON_COLD_CHANCE - 1 && currentBiome.getTempCategory() == Biome.TempCategory.COLD) {
                    player.addPotionEffect(new PotionEffect(ModPotions.POTION_COMMON_COLD.getPotion(), 24000));
                }
            }
        }
    }
}
