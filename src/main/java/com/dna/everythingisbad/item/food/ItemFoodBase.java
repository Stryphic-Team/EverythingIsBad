package com.dna.everythingisbad.item.food;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import com.dna.everythingisbad.utils.IHasModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import scala.util.Random;

public class ItemFoodBase extends ItemFood implements IHasModel {
    protected Random random = new Random();
    public ItemFoodBase(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
    }

    public ItemFoodBase(String name,int amount, float saturation, boolean isWolfFood){
        this(amount,saturation,isWolfFood);
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        ModItems.ITEMS.add(this);
    }
    @Override
    public void registerModels()
    {
        Main.proxy.registerItemRenderer(this,0, "inventory");

    }
    public void CureEffect(EntityLivingBase entityLiving, Potion potion){

        String potionname = potion.getName();
        PotionEffect therighteffect = null;
        for (PotionEffect effect:entityLiving.getActivePotionEffects()){
            if (effect.getEffectName().equals(potionname)) {
                therighteffect = effect;
            }
        }
        if (therighteffect != null){ entityLiving.removePotionEffect(therighteffect.getPotion()); }
    }
}
