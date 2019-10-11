package com.dna.everythingisbad.gui.container;

import com.dna.everythingisbad.tile.TileDeviceBase;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.items.SlotItemHandler;

public class SlotMachineContainer extends DeviceContainerBase {
    public SlotMachineContainer(IInventory playerInventory, TileDeviceBase tileentity) {
        super(playerInventory, tileentity);
        addSlotToContainer(new SlotItemHandler(this.itemHandler, 0, 10, 50));
        addSlotToContainer(new SlotItemHandler(this.itemHandler, 1, 57, 35));
        addSlotToContainer(new SlotItemHandler(this.itemHandler, 2, 57+30, 35));
        addSlotToContainer(new SlotItemHandler(this.itemHandler, 3, 57+60, 35));


    }
}
