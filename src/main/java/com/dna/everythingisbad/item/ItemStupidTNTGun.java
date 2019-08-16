package com.dna.everythingisbad.item;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.entity.EntityStupidTNT;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemStupidTNTGun extends ItemGunBase {
    public ItemStupidTNTGun(String name){
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        ModItems.ITEMS.add(this);
    }
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        ItemStack ammoStack = new ItemStack(Items.AIR);
        /**
         * Checks the players inventory for stupid tnt cartridges
         */
        boolean hasAmmo = false;
        for(ItemStack item:playerIn.inventory.mainInventory){
            //Main.logger.info(item.getItem().getUnlocalizedName());
            if(item.getItem().getUnlocalizedName().equals(ModItems.STUPID_TNT_CARTRIDGE_ITEM.getUnlocalizedName())){
                if(item.getItemDamage() != item.getMaxDamage()) {
                    ammoStack = item;
                    hasAmmo = ammoStack.getItemDamage() != ammoStack.getMaxDamage();
                }
            }else{

            }
        }



        if (!worldIn.isRemote && hasAmmo)
        {
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            EntityStupidTNT entityStupidTNT = new EntityStupidTNT(worldIn, playerIn);
            entityStupidTNT.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 5f, 0.5F);
            worldIn.spawnEntity(entityStupidTNT);
            if(!playerIn.capabilities.isCreativeMode){
                ammoStack.setItemDamage(ammoStack.getItemDamage()+1);
            }






        }else{
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_DISPENSER_FAIL, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        }

        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
}
