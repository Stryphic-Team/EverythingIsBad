package com.dna.everythingisbad.ai;

import com.dna.everythingisbad.entityproperties.InitializedPlayerProperties;
import com.dna.everythingisbad.entityproperties.PlayerProperties;
import com.dna.everythingisbad.init.Religion;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class EntityAINearestNonChristian<T extends EntityLivingBase> extends EntityAINearestAttackableTarget {

    private final int targetChance = 10;
    Religion religion;
    public EntityAINearestNonChristian(EntityCreature creature, Class classTarget, boolean checkSight, Religion rel) {
        super(creature, classTarget, checkSight);
        this.religion = rel;
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0)
        {
            return false;
        }
        else if (this.targetClass != EntityPlayer.class && this.targetClass != EntityPlayerMP.class)
        {
            List<T> list = this.taskOwner.world.<T>getEntitiesWithinAABB(this.targetClass, this.getTargetableArea(this.getTargetDistance()), this.targetEntitySelector);

            if (list.isEmpty())
            {
                return false;
            }
            else
            {
                Collections.sort(list, this.sorter);
                this.targetEntity = list.get(0);
                return true;
            }
        }
        else
        {
            this.targetEntity = this.taskOwner.world.getNearestAttackablePlayer(this.taskOwner,this.getTargetDistance(),this.getTargetDistance());
            if (targetEntity != null){
                EntityPlayer player = (EntityPlayer)targetEntity;
                PlayerProperties playerProperties = player.getCapability(InitializedPlayerProperties.PLAYER_PROPERTIES,null);
                if (playerProperties != null) {
                    int playerReligion = playerProperties.getReligion();

                    if (playerReligion == this.religion.ordinal()) {
                        return false;
                    }
                }
            }
            return this.targetEntity != null;
        }
    }
}
