package com.dna.everythingisbad.tile.processing;


import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.tile.TileMachineBase;
import com.dna.everythingisbad.tile.utils.handlers.ModItemHandler;
import net.minecraft.item.ItemStack;
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
        setFinishedProgress(24000);
        itemStackHadler.setSlotConfig(0,true,false);
        this.displayName = "Stupid tm Core Machine";
    }
    @Override
    public void update() {
        super.update();
        if(hasNecessaryItems()) {
            if (getProgress() % 20 + random.nextInt(10) == 0) {
                this.world.createExplosion(null, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 0.1f, true);
            }
        }
    }

    @Override
    public boolean hasNecessaryItems() {
        //Checks to see if we have enough space and if we have enough energy
        return energyHandler.getEnergyStored() > 10000 && itemStackHadler.getStackInSlot(0).getCount() < 64;
    }

    @Override
    public void insertOutput() {
        itemStackHadler.addItem(0,new ItemStack(ModItems.STUPID_CORE_ITEM,1),false);
    }

    @Override
    public void reduceInput() {

    }

    @Override
    public void reduceEnergy() {
        energyHandler.reduceEnergy(1000,false);
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

}
