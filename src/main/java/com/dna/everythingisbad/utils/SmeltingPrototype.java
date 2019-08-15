package com.dna.everythingisbad.utils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SmeltingPrototype {
    private Item inputItem;
    private ItemStack outputItem;
    private float speed;
    public SmeltingPrototype(Item inputItem, ItemStack outputItem, float speed){
        this.inputItem = inputItem;
        this.outputItem = outputItem;
        this.speed = speed;
    }

    /**
     * returns the input item
     * @return Item
     */
    public Item getInputItem() {
        return inputItem;
    }

    /**
     * returns the output item
     * @return ItemStack
     */
    public ItemStack getOutputItem() {
        return outputItem;
    }

    /**
     * returns the speed
     * @return float
     */
    public float getSpeed() {
        return speed;
    }
}
