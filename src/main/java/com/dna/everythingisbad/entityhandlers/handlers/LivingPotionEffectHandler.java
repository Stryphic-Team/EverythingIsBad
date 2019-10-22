package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.LivingHandlerBase;
import com.dna.everythingisbad.init.ModPotions;
import com.dna.everythingisbad.utils.handlers.PotionEffectHandler;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod;

public class LivingPotionEffectHandler extends LivingHandlerBase {
    @Override
    public void livingTick(EntityLivingBase livingBase) {
        super.livingTick(livingBase);
        boolean highness_active = livingBase.isPotionActive(ModPotions.POTION_HIGHNESS.getPotion());
        boolean common_cold_active = livingBase.isPotionActive(ModPotions.POTION_COMMON_COLD.getPotion());
        boolean adrenaline_active = livingBase.isPotionActive(ModPotions.POTION_ADRENALINE.getPotion());
        boolean withdrawal_active = livingBase.isPotionActive(ModPotions.POTION_WITHDRAWAL.getPotion());
        boolean stimulation_active = livingBase.isPotionActive(ModPotions.POTION_STIMULATION.getPotion());
        boolean tobacco_withdrawal_active = livingBase.isPotionActive(ModPotions.POTION_TOBACCO_WITHDRAWAL.getPotion());

        if(highness_active){
            if (livingBase instanceof EntityPlayerMP) {
                // Casting to entityplayermp
                EntityPlayerMP mp = (EntityPlayerMP)livingBase;
                int highness_duration = mp.getEntityData().getInteger("highness_duration");
                //Main.logger.info("Highness duration in client handler: " + highness_duration);
                PotionEffectHandler.livingEntityHighnessActive(mp, highness_duration);
            }else if (livingBase instanceof EntityCreature){
                int highness_duration = livingBase.getEntityData().getInteger("highness_duration");
                PotionEffectHandler.livingEntityHighnessActive(livingBase, highness_duration);
            }
        }

        if (common_cold_active){
            int common_cold_duration = livingBase.getEntityData().getInteger("common_cold_duration");
            PotionEffectHandler.livingEntityCommonColdActive(livingBase,common_cold_duration);
        }

        if (adrenaline_active){
            int adrenaline_duration = livingBase.getEntityData().getInteger("adrenaline_duration");
            PotionEffectHandler.livingEntityAdrenalineActive(livingBase,adrenaline_duration);
        }

        if (withdrawal_active) {
            int withdrawal_duration = livingBase.getEntityData().getInteger("withdrawal_duration");
            PotionEffectHandler.livingEntityWithdrawalActive(livingBase,withdrawal_duration);
        }
        if (stimulation_active){
            int stimulation_duration = livingBase.getEntityData().getInteger("stimulation_duration");
            PotionEffectHandler.livingEntityStimulationActive(livingBase,stimulation_duration);
        }
        if (tobacco_withdrawal_active){
            int tobacco_withdrawal_duration = livingBase.getEntityData().getInteger("tobacco_withdrawal_duration");
            PotionEffectHandler.livingEntityTobaccoWithdrawalActive(livingBase,tobacco_withdrawal_duration);
        }
    }
}
