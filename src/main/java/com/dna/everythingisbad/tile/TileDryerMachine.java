package com.dna.everythingisbad.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class TileDryerMachine extends TileMachineBase {
    private NonNullList<ItemStack> tileContents = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
}
