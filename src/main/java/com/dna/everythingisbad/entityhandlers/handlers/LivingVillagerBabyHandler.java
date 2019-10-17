package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.LivingHandlerBase;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.ModConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;

public class LivingVillagerBabyHandler extends LivingHandlerBase {
    @Override
    public void livingTick(EntityLivingBase livingBase) {
        super.livingTick(livingBase);
        if (livingBase instanceof EntityVillager){
            EntityVillager villager = (EntityVillager)livingBase;
            if (!villager.isChild()){
                if (villager.ticksExisted % ModConfig.BABY_DROP_INTERVAL == random.nextInt(ModConfig.BABY_DROP_INTERVAL)){
                    // Makes a new itemstack and gives it an NBT string with the age of the baby in ticks
                    ItemStack babyStack = new ItemStack(ModItems.BABY_ITEM, 1, 0);
                    NBTTagCompound babyCmpound = new NBTTagCompound();
                    babyCmpound.setInteger("age",1);
                    babyStack.setTagCompound(babyCmpound);
                    villager.entityDropItem(babyStack,0f);
                    villager.getWorld().playSound((EntityPlayer)null,villager.getPos(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.AMBIENT,2f,0.5f);
                }
            }
        }
    }
}
