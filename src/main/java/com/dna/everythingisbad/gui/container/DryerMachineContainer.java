package com.dna.everythingisbad.gui.container;

import com.dna.everythingisbad.tile.TileDeviceBase;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.items.SlotItemHandler;

public class DryerMachineContainer extends DeviceContainerBase {
    public DryerMachineContainer(IInventory playerInventory, TileDeviceBase tileentity) {
        super(playerInventory, tileentity);
        //Input Slot
        addSlotToContainer(new SlotItemHandler(this.itemHandler, 0, 57, 35));
        //Output slot
        addSlotToContainer(new SlotItemHandler(this.itemHandler, 1, 116, 35));

    }
}
