package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entity.EntityPoliceOfficer;
import com.dna.everythingisbad.entityhandlers.PlayerHandlerBase;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.ModConfig;
import com.dna.everythingisbad.utils.SpawnUtils;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockOre;
import net.minecraft.entity.item.EntityXPOrb;
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
            // Every second (20 ticks), the player's inventory is iterated through
            // and each Baby item's age is incremented. If the age is enough then the
            // baby becomes a villager.
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
                                // random villager profession id assigned (new to 0.2.0; before this you could only get farmer types)
                                EntityVillager entityVillager = new EntityVillager(mp.getServerWorld(),random.nextInt(6));
                                // negative values indicate a child, with 1 day (24000 ticks) being the usual value
                                entityVillager.setGrowingAge(-24000);
                                entityVillager.setPosition(mp.posX, mp.posY, mp.posZ);
                                // if you name the Baby item in an anvil, it carries over onto the villager entity
                                if (!name.equals("Baby")) {
                                    entityVillager.setCustomNameTag(name);
                                }
                                mp.getServerWorld().spawnEntity(entityVillager);
                                //player.addExperience(2);

                                int i = EntityXPOrb.getXPSplit(3);
                                for (int poop = 0; poop < 4; poop++){
                                    mp.getServerWorld().spawnEntity(new EntityXPOrb(mp.getServerWorld(), player.posX, player.posY + 0.5D, player.posZ, i));
                                }
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
            // If you cook a baby then 1-4 police officers spawn around you in an annulus between 5 and 10 blocks away
            if (smelting.getItem() == ModItems.COOKED_BABY_ITEM){
                int randomamount = random.nextInt(3)+1;
                for (int i=0;i<randomamount;i++){
                    SpawnUtils.spawnMobInRadius(mp.getServerWorld(),mp,new EntityPoliceOfficer(mp.getServerWorld()),5,10);
                }
            }
        }

    }
}
