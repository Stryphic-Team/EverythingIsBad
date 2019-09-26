package com.dna.everythingisbad.block.machines;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBlockDevice extends ItemBlock {
    int defaultEnergy = 0;
    public ItemBlockDevice(Block block,int defaultEnergy) {
        super(block);
        this.defaultEnergy = defaultEnergy;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        NBTTagCompound tagCompound = stack.getTagCompound();
        int energyStored = defaultEnergy;
        if(tagCompound != null) {
            energyStored = tagCompound.getInteger("energy_stored");
        }
        tooltip.add(1,"Power: "+energyStored+"RF");
    }
}
