package com.dna.everythingisbad.entity;

import com.dna.everythingisbad.init.ModItems;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Random;

public class EntitySatan extends EntityPigZombie implements IMerchant {
    private MerchantRecipeList buyingList;
    @Nullable
    private EntityPlayer buyingPlayer;
    public EntitySatan(World worldIn) {
        super(worldIn);
        this.buyingList = new MerchantRecipeList();

    }
    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_HOE));
    }
    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        boolean flag = itemstack.getItem() == Items.NAME_TAG;

        if (flag)
        {
            itemstack.interactWithEntity(player, this, hand);
            return true;
        }
        else if (this.isEntityAlive() && !this.isChild() && !player.isSneaking())
        {
            if (this.buyingList == null)
            {
                this.populateBuyingList();
            }

            if (hand == EnumHand.MAIN_HAND)
            {
                player.addStat(StatList.TALKED_TO_VILLAGER);
            }

            if (!this.world.isRemote && !this.buyingList.isEmpty())
            {
                this.setCustomer(player);
                player.displayVillagerTradeGui(this);
            }
            else if (this.buyingList.isEmpty())
            {
                return super.processInteract(player, hand);
            }

            return true;
        }
        else
        {
            return super.processInteract(player, hand);
        }
    }

    public static class ItemsForSoul implements EntityVillager.ITradeList
    {
        public Item buyingItem;
        public EntityVillager.PriceInfo price;

        public ItemsForSoul(Item itemIn, EntityVillager.PriceInfo priceIn)
        {
            this.buyingItem = itemIn;
            this.price = priceIn;
        }

        public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random)
        {
            int i = 1;

            if (this.price != null)
            {
                i = this.price.getPrice(random);
            }

            recipeList.add(new MerchantRecipe(new ItemStack(this.buyingItem, i, 0), ModItems.SOUL_ITEM));
        }
    }
    private void populateBuyingList()
    {
        ArrayList<EntityVillager.ITradeList> trades = new ArrayList<EntityVillager.ITradeList>();
        trades.add(new ItemsForSoul(ModItems.BANJO_ITEM, new EntityVillager.PriceInfo(1, 22)));

        if (trades != null)
        {
            for (EntityVillager.ITradeList entitysatan$itradelist : trades)
            {
                entitysatan$itradelist.addMerchantRecipe(this, this.buyingList, this.rand);

            }
            buyingList.add(new MerchantRecipe(new ItemStack(ModItems.SOUL_ITEM,1),new ItemStack(ModItems.BANJO_ITEM,1)));
        }
    }


    @Override
    public void setCustomer(@Nullable EntityPlayer player) {
        this.buyingPlayer = player;
    }

    @Nullable
    @Override
    public EntityPlayer getCustomer() {
        return buyingPlayer;
    }

    @Nullable
    @Override
    public MerchantRecipeList getRecipes(EntityPlayer player) {
        if (this.buyingList == null)
        {
            this.populateBuyingList();
        }

        return this.buyingList;
    }

    @Override
    public void setRecipes(@Nullable MerchantRecipeList recipeList) {
        this.buyingList = recipeList;
    }

    @Override
    public void useRecipe(MerchantRecipe recipe) {

    }

    @Override
    public void verifySellingItem(ItemStack stack) {
        if (!this.world.isRemote && this.livingSoundTime > -this.getTalkInterval() + 20)
        {
            this.livingSoundTime = -this.getTalkInterval();
            this.playSound(stack.isEmpty() ? SoundEvents.ENTITY_VILLAGER_NO : SoundEvents.ENTITY_VILLAGER_YES, this.getSoundVolume(), this.getSoundPitch());
        }
    }

    @Override
    public World getWorld() {
        return this.getWorld();
    }

    @Override
    public BlockPos getPos() {
        return this.getPosition();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    }
}
