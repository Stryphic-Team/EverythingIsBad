package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.tile.EnumTileDataType;
import com.dna.everythingisbad.tile.TileStupidCoreMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class StupidCoreMachineContainer extends Container {
    private TileStupidCoreMachine tileentity;
    private int energy;
    private int progress;

    public StupidCoreMachineContainer(IInventory playerInventory, TileStupidCoreMachine tileentity) {
        this.tileentity = tileentity;
        IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);;
        this.addSlotToContainer(new SlotItemHandler(handler, 0, 94, 35));

        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 9; x++)
            {

                this.addSlotToContainer(new Slot(playerInventory, x + y*9 + 9, 8 + x*18, 84 + y*18));
            }
        }

        for(int x = 0; x < 9; x++)
        {
            this.addSlotToContainer(new Slot(playerInventory, x, 8 + x * 18, 142));
        }

    }
    private void addPlayerSlots(IInventory playerInventory) {
        // Slots for the main inventory

    }
    @Override
    public void updateProgressBar(int id, int data)
    {
        switch (id){
            case 0:
                this.tileentity.setField(EnumTileDataType.ENERGY_STORAGE, data);
                break;
            case 1:
                this.tileentity.setField(EnumTileDataType.PROGRESS,data);
                break;
        }

    }
    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for(int i = 0; i < this.listeners.size(); ++i)
        {
            IContainerListener listener = (IContainerListener)this.listeners.get(i);

            if(this.energy != this.tileentity.getEnergyStored()) listener.sendWindowProperty(this, 0, this.tileentity.getEnergyStored());
            if(this.progress != this.tileentity.getProgress()) listener.sendWindowProperty(this, 1, this.tileentity.getProgress());
        }

        this.energy = this.tileentity.getEnergyStored();
        this.progress = this.tileentity.getProgress();
    }


    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
    protected boolean performMerge(EntityPlayer player, int slotIndex, ItemStack stack) {

        return performMerge(slotIndex, stack);
    }
    protected boolean performMerge(int slotIndex, ItemStack stack) {

        int invBase = getSizeInventory();
        int invFull = inventorySlots.size();

        if (slotIndex < invBase) {
            return mergeItemStack(stack, invBase, invFull, true);
        }
        return mergeItemStack(stack, 0, invBase, false);
    }

    private int getSizeInventory() {
        return this.tileentity.tileContents.getSlots();
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {

        ItemStack stack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {
            ItemStack stackInSlot = slot.getStack();
            stack = stackInSlot.copy();

            if (!performMerge(player, slotIndex, stackInSlot)) {
                return ItemStack.EMPTY;
            }
            slot.onSlotChange(stackInSlot, stack);

            if (stackInSlot.getCount() <= 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.putStack(stackInSlot);
            }
            if (stackInSlot.getCount() == stack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, stackInSlot);
        }
        return stack;
    }
}
