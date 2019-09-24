package com.dna.everythingisbad.item.instruments;

import com.dna.everythingisbad.item.ItemBase;
import com.dna.everythingisbad.utils.handlers.MidiHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBanjo extends ItemBase {
    public ItemBanjo(String name)
    {
        super(name);
        this.setMaxStackSize(1);
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
