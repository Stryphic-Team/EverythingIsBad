package com.dna.everythingisbad.item.weapons;

import com.dna.everythingisbad.item.ItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class ItemGunBase extends ItemBase {
    protected boolean ignoreAmmo;
    private long lastTickUsed = 0;
    protected int delay = 10;
    public ItemGunBase(String name){
        super(name);
        this.maxStackSize = 1;
    }
    public abstract ItemCartridgeBase getCartridge();
    public abstract void onShoot(World worldIn, EntityPlayer playerIn, EnumHand handIn);
    public abstract void onEmpty(World worldIn, EntityPlayer playerIn,EnumHand handIn);
    private boolean hasAmmo(EntityPlayer player){
        ItemStack ammoStack = getAmmo(player);
        return ammoStack != null && ammoStack.getItemDamage() != ammoStack.getMaxDamage();
    }
    private ItemStack getAmmo(EntityPlayer player){
        ItemStack ammoStack = null;
        for(ItemStack item:player.inventory.mainInventory) {
            //Main.logger.info(item.getItem().getUnlocalizedName());
            if (item.getItem() == getCartridge() && item.getItemDamage() != item.getMaxDamage()) {
                ammoStack = item;
            }
        }
        return ammoStack;
    }
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if(playerIn.ticksExisted - lastTickUsed > delay) {
            lastTickUsed = playerIn.ticksExisted;
            ItemStack ammoStack = getAmmo(playerIn);
            // TODO Add real gunshot sound
            if (hasAmmo(playerIn) || ignoreAmmo || playerIn.capabilities.isCreativeMode) {
                onShoot(worldIn, playerIn, handIn);
            } else {
                onEmpty(worldIn, playerIn, handIn);
            }
            if (!playerIn.capabilities.isCreativeMode && hasAmmo(playerIn) && !ignoreAmmo) {
                ammoStack.setItemDamage(ammoStack.getItemDamage() + 1);
            }


            playerIn.addStat(StatList.getObjectUseStats(this));
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }else{
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        }
    }


}
