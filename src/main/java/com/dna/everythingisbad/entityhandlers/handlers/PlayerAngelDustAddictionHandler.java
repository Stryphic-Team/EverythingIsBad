package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerPropertiesCapability;
import com.dna.everythingisbad.init.ModPotions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

public class PlayerAngelDustAddictionHandler extends PlayerHandlerBase {
    @Override
    public void playerTick(EntityPlayer player) {
        super.playerTick(player);

        PlayerProperties playerProperties = player.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);

        // Addictions increment every minute (1200 ticks)
        // (May change to 2 minutes later)
        if (player.ticksExisted % 1200 == 160){
            // If you are high on angel dust, then your addiction level increases.
            if (player.isPotionActive(ModPotions.POTION_ADRENALINE.getPotion())){

                int addictionlvl = playerProperties.getAngelDustAddictionLvl();
                playerProperties.setAngelDustAddictionLvl(addictionlvl + 1);
                playerProperties.saveNBTData(player.getEntityData());
            }
        }

        int angel_dust_addiction = playerProperties.getAngelDustAddictionLvl();
        float addictionTime;

        if (angel_dust_addiction == 0){
            addictionTime = 0f;
        }else{
            // First every 20000 ticks, then 10000 ticks, then 6666 ticks, then 5000 ticks, etc...
            addictionTime = 20000f/(float)angel_dust_addiction;
        }
        // If you're an addict, and your modulo value is smol, and you don't have adrenaline
        // or already have withdrawal symptoms, then give withdrawal symptoms periodically
        if (addictionTime != 0f){
            if (player.ticksExisted % (int)addictionTime == (int)(addictionTime/2) &&
                    !player.isPotionActive(ModPotions.POTION_ADRENALINE.getPotion())
            && !player.isPotionActive(ModPotions.POTION_WITHDRAWAL.getPotion()) ){

                // Withdrawal for 50 seconds
                player.addPotionEffect(new PotionEffect(ModPotions.POTION_WITHDRAWAL.getPotion(),1000));
            }
        }
    }
}
