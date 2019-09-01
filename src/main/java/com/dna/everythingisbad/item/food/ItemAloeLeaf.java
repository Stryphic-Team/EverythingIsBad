package com.dna.everythingisbad.item.food;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModPotions;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.Random;

public class ItemAloeLeaf extends ItemFoodBase {
    public ItemAloeLeaf(String name)
    {
        super(2,0f,true);
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        ModItems.ITEMS.add(this);
    }

    // Givs poop when you eat the aloe
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving){
        Random rand = new Random();
        if (!worldIn.isRemote){
            ItemStack itemstack = new ItemStack(ModItems.POOP_ITEM,1+rand.nextInt(2),3);
            if (entityLiving instanceof EntityPlayer){
                EntityPlayer player = (EntityPlayer)entityLiving;
                if (!player.inventory.addItemStackToInventory(itemstack)) {
                    player.dropItem(itemstack, false);
                }else{
                    player.inventory.addItemStackToInventory(itemstack);
                }
            }else{
                //entityLiving.dropItem(ModItems.POOP_ITEM,1+rand.nextInt(2));
            }
        }

        if (entityLiving instanceof EntityPlayerMP)
        {
            if (entityLiving instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer)entityLiving;
                entityplayer.getFoodStats().addStats(this, stack);
                worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
                this.onFoodEaten(stack, worldIn, entityplayer);
                entityplayer.addStat(StatList.getObjectUseStats(this));

                if (entityplayer instanceof EntityPlayerMP)
                {
                    CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
                }
            }
        }

        if (entityLiving instanceof EntityPlayer && !((EntityPlayer)entityLiving).capabilities.isCreativeMode)
        {
            stack.shrink(1);
        }
        return stack;
    }
}
