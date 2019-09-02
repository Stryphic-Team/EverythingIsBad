package com.dna.everythingisbad.tile;


import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.misc.ModItemStackHadler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;

public class TileStupidCoreMachine extends TileMachineBase {

    //private NonNullList<ItemStack> tileContents = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
    public ModItemStackHadler tileContents = new ModItemStackHadler(1);
    public TileStupidCoreMachine(){
        setFinishedProgress(240);
        tileContents.setExtractor(true);
    }
    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return tileContents.getStackInSlot(slot);
    }
    @Override
    public void update(){
        super.update();
        if(getEnergyStored() > 10000 && tileContents.getStackInSlot(0).getCount() < 64){
            reduceEnergyStored(10);
            if(getProgress() == getFinishedProgress()){
                int current_amount = tileContents.getStackInSlot(0).getCount();
                tileContents.setStackInSlot(0,new ItemStack(ModItems.STUPID_CORE_ITEM,current_amount + 1));
                setProgress(0);
            }else{
                stepProgress();
                Main.logger.info(getEnergyStored());
            }

            //tileContents.add(0,new ItemStack(ModItems.STUPID_CORE_ITEM,1));
        }
    }
    @Override
    public int getSlots() {
        return 1;
    }
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        ItemStack stack = tileContents.getStackInSlot(slot);
        int stackCount = stack.getCount();

        if(!simulate) {
            if (stackCount <= amount) {

                ItemStack output_stack = stack.copy();
                stack.setCount(0);
                output_stack.setCount(stackCount);
                return output_stack;
            } else {
                stack.setCount(stackCount - amount);
                ItemStack output_stack = stack.copy();
                output_stack.setCount(amount);
                return output_stack;
            }
        }else{
            return new ItemStack(Items.AIR);
        }

    }
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(this);
        }
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){

            return (T)tileContents;
        }
        return super.getCapability(capability, facing);
    }
    @Override
    public int getSlotLimit(int slot) {
        return 1;
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        //ItemStackHelper.loadAllItems(nbt,tileContents);
        super.readFromNBT(nbt);
        this.tileContents.deserializeNBT(nbt.getCompoundTag("Inventory"));


    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setTag("Inventory", this.tileContents.serializeNBT());
        super.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
        tileContents.setStackInSlot(slot,stack);
    }

    static {
        register("stupid_core_machine", TileStupidCoreMachine.class);
    }
}
