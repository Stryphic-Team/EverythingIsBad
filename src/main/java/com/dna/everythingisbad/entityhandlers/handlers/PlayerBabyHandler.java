package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entity.EntityPoliceOfficer;
import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.ModConfig;
import com.dna.everythingisbad.utils.SpawnUtils;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerBabyHandler extends PlayerHandlerBase {
    @Override
    public void playerTick(EntityPlayer player) {
        super.playerTick(player);
        if (player instanceof EntityPlayerMP){
            EntityPlayerMP mp = (EntityPlayerMP)player;
            InventoryPlayer deeta = mp.inventory;
            if(mp.ticksExisted % 20 == 19) {
                for (ItemStack stack : deeta.mainInventory) {
                    if (stack.getItem() == ModItems.BABY_ITEM) {
                        NBTTagCompound compound = stack.getTagCompound();
                        if (compound != null) {
                            int age = compound.getInteger("age");
                            age++;
                            compound.setInteger("age", age);
                            stack.setTagCompound(compound);

                            if (age >= ModConfig.BABY_AGE_UP_TIME) {
                                String name = stack.getDisplayName();
                                stack.shrink(1);
                                EntityVillager entityVillager = new EntityVillager(mp.getServerWorld());
                                entityVillager.setGrowingAge(-24000);
                                entityVillager.setPosition(mp.posX, mp.posY, mp.posZ);
                                if (!name.equals("Baby")) {
                                    entityVillager.setCustomNameTag(name);
                                }
                                mp.getServerWorld().spawnEntity(entityVillager);
                            }
                        }
                    }
                }
            }
        }
    }
    @Override
    public void playerSmeltItem(EntityPlayer player, ItemStack smelting) {
        super.playerSmeltItem(player, smelting);
        if (player instanceof EntityPlayerMP){
            EntityPlayerMP mp = (EntityPlayerMP)player;
            if (smelting.getItem() == ModItems.COOKED_BABY_ITEM){
                int randomamount = random.nextInt(3)+1;
                for (int i=0;i<randomamount;i++){
                    SpawnUtils.spawnMobInRadius(mp.getServerWorld(),mp,new EntityPoliceOfficer(mp.getServerWorld()),5,10);
                }
            }
        }

    }
}
