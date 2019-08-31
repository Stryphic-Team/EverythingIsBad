package com.dna.everythingisbad.gui;

import com.dna.everythingisbad.tile.TileStupidCoreMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class StupidCoreMachineContainer extends Container {
    private TileStupidCoreMachine tileentity;

    public StupidCoreMachineContainer(IInventory playerInventory, TileStupidCoreMachine tileentity) {
        this.tileentity = tileentity;
        IItemHandler handler = tileentity;
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
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

}
