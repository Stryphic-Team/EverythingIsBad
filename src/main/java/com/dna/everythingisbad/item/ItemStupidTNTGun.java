package com.dna.everythingisbad.item;

import com.dna.everythingisbad.entity.EntityStupidTNT;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.entity.player.EntityPlayer;
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

        ModItems.ITEMS.add(this);
    }
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        ItemStack stupidTNTAmmo = new ItemStack(ModBlocks.STUPID_TNT_BLOCK);

        boolean hasAmmo = playerIn.inventory.hasItemStack(stupidTNTAmmo);
        if (!worldIn.isRemote && hasAmmo)
        {
            worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            for(int i = 0;i<10;i++) {
                EntityStupidTNT entityStupidTNT = new EntityStupidTNT(worldIn, playerIn);
                entityStupidTNT.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 5f, 2F);
                worldIn.spawnEntity(entityStupidTNT);
            }
            int ammoSlot = playerIn.inventory.getSlotFor(stupidTNTAmmo);
            ItemStack ammoStack = playerIn.inventory.getStackInSlot(ammoSlot);
            ammoStack.setCount(ammoStack.getCount()-1);


        }else{
            worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_DISPENSER_FAIL, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        }

        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
}
