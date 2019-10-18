package com.dna.everythingisbad.item.food;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.init.ModPotions;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAngelDust extends ItemFoodBase {
    public ItemAngelDust(String name)
    {
        super(0,0,false);
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        this.setPotionEffect(ModPotions.POTION_ADRENALINE.getPotionEffect(),1f);
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        ModItems.ITEMS.add(this);
        this.setAlwaysEdible();
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayerMP){
            //SpawnUtils.spawnMobInRadius(worldIn,(EntityPlayerMP)entityLiving,new EntityPoliceOfficer(worldIn),5,10);

            // Add 50 to the entity's drug_sum (the value added on to the target heart rate when under the influence)
            float drugSum = entityLiving.getEntityData().getFloat("drug_sum");
            entityLiving.getEntityData().setFloat("drug_sum", drugSum + 50f);
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
