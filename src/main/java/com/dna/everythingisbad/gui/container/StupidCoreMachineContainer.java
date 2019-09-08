package com.dna.everythingisbad.gui.container;

import com.dna.everythingisbad.tile.TileDeviceBase;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.items.SlotItemHandler;

public class StupidCoreMachineContainer extends DeviceContainerBase {
    public StupidCoreMachineContainer(IInventory playerInventory, TileDeviceBase tileentity) {
        super(playerInventory,tileentity);
        addSlotToContainer(new SlotItemHandler(this.itemHandler, 0, 94, 35));
    }

}
