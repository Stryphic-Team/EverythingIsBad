package com.dna.everythingisbad.item;

import com.dna.everythingisbad.network.handlers.MessageHeartRateSyncHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemHeartRateMonitor extends ItemBase {
    public ItemHeartRateMonitor(String name){
        super(name);
        this.maxStackSize = 1;
        this.addPropertyOverride(new ResourceLocation("lit"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (entityIn == null){
                    return 0f;
                }else{
                    // in bpm
                    float heartrate = MessageHeartRateSyncHandler.clientHeartRate;
                    float beatsPerSecond = heartrate/60f;
                    float moduloval = 20f/beatsPerSecond;

                    // (Can't divide by zero lol, found this out the hard way...)
                    if (heartrate != 0){
                        if (entityIn.ticksExisted % moduloval < 2){
                            return 1f;
                        }else{
                            return 0f;
                        }
                    }else{
                        return 0f;
                    }
                }
            }
        });
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        NBTTagCompound compound = this.getNBTShareTag(stack);
        if (compound != null){
            // item_heart_rate is the item's internal nbt tag to be displayed
            float heartRate = compound.getFloat("item_heart_rate");
            //tooltip.add("Heart Rate: " + heartRate + " BPM");
            int heartrateint = Math.round(heartRate);
            tooltip.add(heartrateint + " BPM");
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
