package com.dna.everythingisbad.gui.container;

import com.dna.everythingisbad.tile.TileDryerMachine;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.items.SlotItemHandler;

public class DryerMachineContainer extends DeviceContainerBase {
    public DryerMachineContainer(IInventory playerInventory, TileDryerMachine tileentity) {
        super(playerInventory, tileentity);
        //Output slot
        addSlotToContainer(new SlotItemHandler(this.itemHandler, 0, 116, 35));
        //Input Slot
        addSlotToContainer(new SlotItemHandler(this.itemHandler, 0, 57, 35));
    }
}
