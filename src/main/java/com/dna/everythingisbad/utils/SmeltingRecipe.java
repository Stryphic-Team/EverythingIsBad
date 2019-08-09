package com.dna.everythingisbad.utils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SmeltingRecipe {
    private Item inputItem;
    private ItemStack outputItem;
    private float speed;
    public SmeltingRecipe(Item inputItem, ItemStack outputItem,float speed){
        this.inputItem = inputItem;
        this.outputItem = outputItem;
        this.speed = speed;
    }

    public Item getInputItem() {
        return inputItem;
    }

    public void setInputItem(Item inputItem) {
        this.inputItem = inputItem;
    }

    public ItemStack getOutputItem() {
        return outputItem;
    }

    public void setOutputItem(ItemStack outputItem) {
        this.outputItem = outputItem;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
