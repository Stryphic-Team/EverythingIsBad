package com.dna.everythingisbad.item.weapons;

import com.dna.everythingisbad.entity.EntityBullet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemPoliceGun extends ItemGunBase {
    public ItemPoliceGun(String name){
        super(name);
        ignoreAmmo = true;
    }


    @Override
    public ItemCartridgeBase getCartridge() {
        return null;
    }

    @Override
    public void onShoot(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 1F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if(!worldIn.isRemote) {
            EntityBullet bulet = new EntityBullet(worldIn, playerIn);
            bulet.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 0F);
            worldIn.spawnEntity(bulet);
        }
    }

    @Override
    public void onEmpty(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_DISPENSER_FAIL, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
    }


    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        // TODO Add real gunshot sound

        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 1F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote)
        {
            EntityBullet bulet = new EntityBullet(worldIn,playerIn);
            bulet.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 0F);
            worldIn.spawnEntity(bulet);
        }

        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
}
