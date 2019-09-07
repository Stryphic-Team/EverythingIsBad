package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.tile.TileDeviceBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class DeviceContainerBase extends Container {
    TileDeviceBase tileentity;
    IItemHandler itemHandler;
    int energy;
    int progress;
    int fluid_stored;
    int fluid_type;
    public DeviceContainerBase(IInventory playerInventory,TileDeviceBase tileentity){
        this.tileentity = tileentity;
        this.itemHandler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,null);

        addPlayerSlots(playerInventory);
    }
    protected void addPlayerSlots(IInventory playerInventory){
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
    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data) {
        this.tileentity.getEnergyHandler().setEnergyStorage(GuiSync.ENERGY_STORED);
        String fluidName = GuiSync.FLUID_TYPE.replaceAll("fluid.","");
        Fluid fluid = FluidRegistry.getFluid(fluidName);
        if(fluid != null) {
            this.tileentity.getFluidHandler().getFluidTank().setFluid(new FluidStack(fluid, GuiSync.FLUID_STORED));
        }
        this.tileentity.setProgress(GuiSync.PROGRESS);
//        switch (id){
//            case 0:
//                tileentity.setField(EnumTileDataType.PROGRESS,data);
//                break;
//            case 1:
//                tileentity.setField(EnumTileDataType.ENERGY_STORAGE,data);
//                break;
//            case 2:
//                tileentity.setField(EnumTileDataType.FLUID_STORED,data);
//                break;
//            case 3:
//                tileentity.setField(EnumTileDataType.FLUID_TYPE,data);
//                break;
//        }



    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        for(IContainerListener listener:listeners){
            listener.sendWindowProperty(this, 0, energy);
            tileentity.sendGuiNetworkData(this,listener);
        }
//        for(int i = 0; i < this.listeners.size(); ++i)
//        {
//            IContainerListener listener = (IContainerListener)this.listeners.get(i);
//            int progress = this.tileentity.getProgress();
//            int energy = this.tileentity.getEnergyHandler().getEnergyStorage();
//            int fluid_stored = this.tileentity.getFluidHandler().getFluidTank().getFluidAmount();
//            FluidStack fluidStack = this.tileentity.getFluidHandler().getFluidTank().getFluid();
//            if(fluidStack != null){
//                fluid_type = FluidCache.toInt(fluidStack.getFluid().getName());
//            }else{
//                fluid_type = -1;
//            }
//            if(this.energy != energy) listener.sendWindowProperty(this, 0, energy);
//            if(this.progress != progress) listener.sendWindowProperty(this, 1, progress);
//            if(this.fluid_type != fluid_type) listener.sendWindowProperty(this, 3, fluid_type);
//            if(this.fluid_stored != fluid_stored) listener.sendWindowProperty(this, 2, fluid_stored);
//
//
//        }
//
//        this.energy = this.tileentity.getEnergyHandler().getEnergyStored();
//        this.progress = this.tileentity.getProgress();
//        this.fluid_stored = this.tileentity.getFluidHandler().getFluidTank().getFluidAmount();
//        FluidStack fluidStack = this.tileentity.getFluidHandler().getFluidTank().getFluid();
//        if(fluidStack != null){
//            this.fluid_type = FluidCache.toInt(fluidStack.getUnlocalizedName());
//        }else{
//            this.fluid_type = -1;
//        }
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
        return this.tileentity.getItemStackHadler().getSlots();
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
