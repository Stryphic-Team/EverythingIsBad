package com.dna.everythingisbad.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBaby extends ItemBase {
    public ItemBaby(String name)
    {
        super(name);
        this.maxStackSize = 1;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        NBTTagCompound compound = this.getNBTShareTag(stack);
        if (compound != null){
            int age = compound.getInteger("age");
            tooltip.add("Age: " + age + "s");
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
