package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entity.EntityJesus;
import com.dna.everythingisbad.entityhandlers.LivingHandlerBase;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerPropertiesCapability;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;

public class LivingJesusHealHandler extends LivingHandlerBase {
    @Override
    public void livingDamage(EntityLivingBase entityLiving, DamageSource source, float amount) {
        super.livingDamage(entityLiving, source, amount);
        if (entityLiving instanceof EntityPlayerMP){

            PlayerProperties playerProperties = entityLiving.getCapability(PlayerPropertiesCapability.PLAYER_PROPERTIES,null);
            // Is it a Jesus that attacked you? if so then...
            if (source.getTrueSource() instanceof EntityJesus){
                // are you blind? currently? uhhh
                if (entityLiving.isPotionActive(Potion.getPotionById(15))){
                    entityLiving.removePotionEffect(Potion.getPotionById(15));
                    playerProperties.setBlind(false);
                    playerProperties.saveNBTData(entityLiving.getEntityData());
                }
            }
        }
    }
}
