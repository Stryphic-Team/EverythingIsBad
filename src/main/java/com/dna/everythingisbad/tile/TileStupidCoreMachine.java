package com.dna.everythingisbad.tile;


import com.dna.everythingisbad.init.ModItems;
import net.minecraft.init.Items;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;

public class TileStupidCoreMachine extends TileMachineBase {

    private NonNullList<ItemStack> tileContents = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return tileContents.get(slot);
    }
    @Override
    public void update(){
        super.update();
        if(getEnergyStored() > 10000 && tileContents.get(0).getCount() < 64){
            reduceEnergyStored(10000);
            int current_amount = tileContents.get(0).getCount();
            tileContents.set(0,new ItemStack(ModItems.STUPID_CORE_ITEM,current_amount + 1));
            //tileContents.add(0,new ItemStack(ModItems.STUPID_CORE_ITEM,1));
        }
    }
    @Override
    public int getSlots() {
        return 1;
    }
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        ItemStack stack = tileContents.get(slot);
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
    public int getSlotLimit(int slot) {
        return 1;
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        ItemStackHelper.loadAllItems(nbt,tileContents);
        super.readFromNBT(nbt);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        ItemStackHelper.saveAllItems(nbt,tileContents);
        super.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
        tileContents.set(slot,stack);
    }

    static {
        register("stupid_core_machine", TileStupidCoreMachine.class);
    }
}
