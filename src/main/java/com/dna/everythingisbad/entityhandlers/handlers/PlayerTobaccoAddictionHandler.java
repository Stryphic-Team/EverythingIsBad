package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.entityproperties.InitializedPlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.init.ModPotions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

public class PlayerTobaccoAddictionHandler extends PlayerHandlerBase {
    @Override
    public void playerTick(EntityPlayer player) {
        super.playerTick(player);

        PlayerProperties playerProperties = player.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);

        // Addictions increment every minute (1200 ticks)
        // (May change to 2 minutes later)
        if (player.ticksExisted % 1200 == 160){
            // If you are stimulated from tobacco, then your addiction level increases.
            if (player.isPotionActive(ModPotions.POTION_STIMULATION.getPotion())){

                int addictionlvl = playerProperties.getTobaccoAddictionLvl();
                playerProperties.setTobaccoAddictionLvl(addictionlvl + 1);
                playerProperties.saveNBTData(player.getEntityData());
            }
        }

        int tobacco_addiction = playerProperties.getTobaccoAddictionLvl();
        float addictionTime;

        if (tobacco_addiction == 0){
            addictionTime = 0f;
        }else{
            // First every 18000 ticks, then 9000 ticks, then 6000 ticks, then 4500 ticks, etc...
            addictionTime = 18000f/(float)tobacco_addiction;
        }
        // If you're an addict, and your modulo value is smol, and you don't have stimulation
        // or already have tobacco withdrawal symptoms, then give withdrawal symptoms periodically
        if (addictionTime != 0f){
            if (player.ticksExisted % (int)addictionTime == (int)(addictionTime/3) &&
                    !player.isPotionActive(ModPotions.POTION_STIMULATION.getPotion())
                    && !player.isPotionActive(ModPotions.POTION_TOBACCO_WITHDRAWAL.getPotion()) ){

                // Withdrawal for 50 seconds
                player.addPotionEffect(new PotionEffect(ModPotions.POTION_TOBACCO_WITHDRAWAL.getPotion(),1000));
            }
        }
    }
}
