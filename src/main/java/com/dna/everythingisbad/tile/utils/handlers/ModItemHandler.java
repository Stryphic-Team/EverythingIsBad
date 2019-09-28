package com.dna.everythingisbad.tile.utils.handlers;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class ModItemHandler extends ItemStackHandler {

    private boolean isInserter = false;
    private boolean isExtractor = false;
    private HashMap<Integer, SlotConfig> slotConfigMap = new HashMap<Integer,SlotConfig>();
    public ModItemHandler(int count){
        super(count);
        for(int i = 0;i<getSlots();i++){
            slotConfigMap.put(i,new SlotConfig(false,false));
        }
    }

    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
    {

        if(!slotConfigMap.get(slot).isInsert()){
            return stack;
        }
        if (stack.isEmpty())
            return ItemStack.EMPTY;

        validateSlotIndex(slot);

        ItemStack existing = this.stacks.get(slot);

        int limit = getStackLimit(slot, stack);

        if (!existing.isEmpty())
        {
            if (!ItemHandlerHelper.canItemStacksStack(stack, existing))
                return stack;

            limit -= existing.getCount();
        }

        if (limit <= 0)
            return stack;

        boolean reachedLimit = stack.getCount() > limit;

        if (!simulate)
        {
            if (existing.isEmpty())
            {
                this.stacks.set(slot, reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack);
            }
            else
            {
                existing.grow(reachedLimit ? limit : stack.getCount());
            }
            onContentsChanged(slot);
        }

        return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount()- limit) : ItemStack.EMPTY;
    }
    @Override
    @Nonnull
    public ItemStack extractItem(int slot, int amount, boolean simulate)
    {
        if(!slotConfigMap.get(slot).isExtract()){
            return ItemStack.EMPTY;
        }

        if (amount == 0)
            return ItemStack.EMPTY;

        validateSlotIndex(slot);

        ItemStack existing = this.stacks.get(slot);

        if (existing.isEmpty())
            return ItemStack.EMPTY;

        int toExtract = Math.min(amount, existing.getMaxStackSize());

        if (existing.getCount() <= toExtract)
        {
            if (!simulate)
            {
                this.stacks.set(slot, ItemStack.EMPTY);
                onContentsChanged(slot);
            }
            return existing;
        }
        else
        {
            if (!simulate)
            {
                this.stacks.set(slot, ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract));
                onContentsChanged(slot);
            }

            return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
        }
    }
    public void setSlotConfig(int slot,boolean extract,boolean insert){
        slotConfigMap.put(slot,new SlotConfig(extract, insert));
    }
    @Deprecated
    public boolean isInserter() {
        return isInserter;
    }
    @Deprecated
    public void setInserter(boolean inserter) {
        isInserter = inserter;
    }
    @Deprecated
    public boolean isExtractor() {
        return isExtractor;
    }
    @Deprecated
    public void setExtractor(boolean extractor) {
        isExtractor = extractor;
    }

    public ItemStack addItem(int slot, @Nonnull ItemStack stack, boolean simulate){
        if (stack.isEmpty())
            return ItemStack.EMPTY;

        validateSlotIndex(slot);

        ItemStack existing = this.stacks.get(slot);

        int limit = getStackLimit(slot, stack);

        if (!existing.isEmpty())
        {
            if (!ItemHandlerHelper.canItemStacksStack(stack, existing))
                return stack;

            limit -= existing.getCount();
        }

        if (limit <= 0)
            return stack;

        boolean reachedLimit = stack.getCount() > limit;

        if (!simulate)
        {
            if (existing.isEmpty())
            {
                this.stacks.set(slot, reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack);
            }
            else
            {
                existing.grow(reachedLimit ? limit : stack.getCount());
            }
            onContentsChanged(slot);
        }

        return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount()- limit) : ItemStack.EMPTY;
    }
    public NonNullList<ItemStack> getItemStackList(){
        return stacks;
    }
    public void setItemStackList(NonNullList<ItemStack> newStacks){
        this.stacks = newStacks;
    }
    private class SlotConfig {
        private boolean extract = false;
        private boolean insert = false;
        public SlotConfig(boolean extract, boolean insert){
            this.extract = extract;
            this.insert = insert;
        }

        public boolean isExtract() {
            return extract;
        }

        public boolean isInsert() {
            return insert;
        }
    }

}
