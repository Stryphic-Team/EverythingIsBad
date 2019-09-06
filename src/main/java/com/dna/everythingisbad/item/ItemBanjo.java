package com.dna.everythingisbad.item;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import com.dna.everythingisbad.utils.handlers.MidiHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBanjo extends ItemBase {
    public ItemBanjo(String name)
    {

        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        this.setMaxStackSize(1);
        ModItems.ITEMS.add(this);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (MidiHandler.device != null && MidiHandler.device.getDeviceInfo() != null){
            String devicename = MidiHandler.device.getDeviceInfo().getName();
            if (devicename !=null){
                tooltip.add("MIDI device: "+ devicename);
            }else{
                tooltip.add("No MIDI device found!");
            }
            super.addInformation(stack, worldIn, tooltip, flagIn);
        }
    }
}
