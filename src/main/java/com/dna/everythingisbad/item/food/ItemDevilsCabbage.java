package com.dna.everythingisbad.item.food;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.entity.EntityPoliceOfficer;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModPotions;
import com.dna.everythingisbad.utils.CommonUtils;
import com.dna.everythingisbad.utils.SpawnUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDevilsCabbage extends ItemFoodBase {
    public ItemDevilsCabbage(String name)
    {
        super(0,0,true);
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        this.setPotionEffect(ModPotions.POTION_HIGHNESS.getPotionEffect(),1f);
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        ModItems.ITEMS.add(this);
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayerMP){
            //SpawnUtils.spawnMobInRadius(worldIn,(EntityPlayerMP)entityLiving,new EntityPoliceOfficer(worldIn),5,10);
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
