package com.dna.everythingisbad.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemHeartRateMonitor extends ItemBase {
    public ItemHeartRateMonitor(String name){
        super(name);
        this.maxStackSize = 1;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        NBTTagCompound compound = this.getNBTShareTag(stack);
        if (compound != null){
            // item_heart_rate is the item's internal nbt tag to be displayed
            int heartRate = compound.getInteger("item_heart_rate");
            tooltip.add("Heart Rate: " + heartRate + " BPM");
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
