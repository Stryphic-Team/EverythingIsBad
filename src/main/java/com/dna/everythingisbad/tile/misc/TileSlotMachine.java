package com.dna.everythingisbad.tile.misc;

import com.dna.everythingisbad.tile.TileDeviceBase;
import com.dna.everythingisbad.tile.utils.handlers.ModItemHandler;
import com.dna.everythingisbad.utils.RandomUtils;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class TileSlotMachine extends TileDeviceBase {
    public ArrayList<ItemStack> rollItems = new ArrayList<ItemStack>();
    public TileSlotMachine() {
        super("slot_machine");
        itemStackHadler = new ModItemHandler(4);
        //Emerald Slot
        itemStackHadler.setSlotConfig(0,true,true);
        //Game Slots
        itemStackHadler.setSlotConfig(1,false,false);
        itemStackHadler.setSlotConfig(2,false,false);
        itemStackHadler.setSlotConfig(3,false,false);

        //Good Items
        rollItems.add(new ItemStack(Items.GOLDEN_APPLE));
        rollItems.add(new ItemStack(Items.GOLD_INGOT));
        //Bad Items
        rollItems.add(new ItemStack(Items.ROTTEN_FLESH));
        rollItems.add(new ItemStack(Items.COAL));
        rollItems.add(new ItemStack(Items.POTATO));
        rollItems.add(new ItemStack(Items.BREAD));
        rollItems.add(new ItemStack(Items.BEEF));
        rollItems.add(new ItemStack(Items.BEETROOT));
        displayName = "Slot Machine";



    }

    @Override
    public void update() {
        super.update();
        if(tick % 4 == 3) {
            if (inProgress()) {
                for (int i = 1; i < 4; i++) {
                    //Sets the item in the current slot to a random item
                    itemStackHadler.setStackInSlot(i, rollItems.get(RandomUtils.fromRangeI(0, rollItems.size())));
                }
                stepProgress();
                if (progress == finishedProgress - 1) {
                    onFinish();
                }

            }
        }

    }
    public void startRoll(){
        if(itemStackHadler.getStackInSlot(0).getItem() == Items.GOLD_NUGGET && !inProgress()) {
            setFinishedProgress(RandomUtils.fromRangeI(15, 20));
            stepProgress();
            itemStackHadler.getStackInSlot(0).shrink(1);
        }
    }
    public void onFinish(){
        setProgress(0);
        for (int i = 1; i < 4; i++) {
            ItemStack itemStack = itemStackHadler.getStackInSlot(i);
            //Checks if the item is good
            if(isItemGood(itemStack.getItem())){
                if(!world.isRemote){
                    EntityItem entityItem = new EntityItem(world);
                    entityItem.setItem(new ItemStack(Items.GOLD_NUGGET,1));
                    entityItem.setPosition(pos.getX(),pos.getY()+1,pos.getZ());
                    world.spawnEntity(entityItem);
                }
            }
            if(isJackPot()){
                EntityItem entityItem = new EntityItem(world);
                entityItem.setItem(new ItemStack(Items.DIAMOND,RandomUtils.fromRangeI(1,7)));
                entityItem.setPosition(pos.getX(),pos.getY()+1,pos.getZ());
                world.spawnEntity(entityItem);
            }
        }
    }
    public boolean isItemGood(Item item){
        return item == Items.GOLD_INGOT || item == Items.DIAMOND_HOE || item == Items.GOLDEN_APPLE;
    }
    public boolean isJackPot(){
        for(int i = 1; i < 4; i++){
            for(int j = 1; j < 4; j++){
                if(itemStackHadler.getStackInSlot(i).getItem() != itemStackHadler.getStackInSlot(j).getItem()){
                    return false;
                }
            }
        }
        if(isItemGood(itemStackHadler.getStackInSlot(1).getItem())){
            return true;
        }else{
            return false;
        }


    }
}
