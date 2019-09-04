package com.dna.everythingisbad.tile;


import com.dna.everythingisbad.capabilities.ModItemHandler;
import com.dna.everythingisbad.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import scala.util.Random;

public class TileStupidCoreMachine extends TileMachineBase {

    //private NonNullList<ItemStack> tileContents = NonNullList.<ItemStack>withSize(1, ItemStack.EMPTY);
    private Random random = new Random();
    public TileStupidCoreMachine(){
        super("stupid_core_machine");
        this.itemStackHadler = new ModItemHandler(1);
        setFinishedProgress(2400);
        itemStackHadler.setExtractor(true);
    }

    @Override
    public void update(){
        super.update();
        if(energyHandler.getEnergyStored() > 10000 && itemStackHadler.getStackInSlot(0).getCount() < 64){

            if(getProgress() == getFinishedProgress()){
                int current_amount = itemStackHadler.getStackInSlot(0).getCount();
                itemStackHadler.setStackInSlot(0,new ItemStack(ModItems.STUPID_CORE_ITEM,current_amount + 1));
                setProgress(0);
            }else{
                if(energyHandler.reduceEnergy(1000,true)) {
                    stepProgress();
                    energyHandler.reduceEnergy(1000,false);
                    if(getProgress() % 20 + random.nextInt(10) == 0){

                        this.world.createExplosion(null,this.getPos().getX(),this.getPos().getY(),this.getPos().getZ(),0.1f,true);
                    }
                }
            }
        }
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(energyHandler);
        }
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){

            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHadler);
        }
        return super.getCapability(capability, facing);
    }


    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        //ItemStackHelper.loadAllItems(nbt,tileContents);
        super.readFromNBT(nbt);
        this.itemStackHadler.deserializeNBT(nbt.getCompoundTag("Inventory"));


    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setTag("Inventory", this.itemStackHadler.serializeNBT());
        super.writeToNBT(nbt);
        return nbt;
    }
}
