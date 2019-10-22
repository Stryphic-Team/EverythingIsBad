package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.entityproperties.InitializedPlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.init.ModPotions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class PlayerAddictionHandler extends PlayerHandlerBase {
    @Override
    public void playerTick(EntityPlayer player) {
        super.playerTick(player);

        PlayerProperties playerProperties = player.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);

        // Addictions increment every minute (1200 ticks)
        if (player.ticksExisted % 1200 == 160){
            // If you are high on angel dust, then your addiction level increases.
            if (player.isPotionActive(ModPotions.POTION_ADRENALINE.getPotion())){
//                NBTTagCompound deeta = player.getEntityData();
//                int angelDustAddiction = deeta.getInteger("angel_dust_addiction");
//                deeta.setInteger("angel_dust_addiction", angelDustAddiction + 1);
//                player.writeEntityToNBT(deeta);
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
            if (player.ticksExisted % addictionTime < 3f &&
                    !player.isPotionActive(ModPotions.POTION_ADRENALINE.getPotion())
            && !player.isPotionActive(ModPotions.POTION_WITHDRAWAL.getPotion()) ){

                // Withdrawal for 50 seconds
                player.addPotionEffect(new PotionEffect(ModPotions.POTION_WITHDRAWAL.getPotion(),1000));
            }
        }
    }
}
