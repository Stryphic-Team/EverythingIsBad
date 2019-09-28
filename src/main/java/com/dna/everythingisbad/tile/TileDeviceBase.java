package com.dna.everythingisbad.tile;

import com.dna.everythingisbad.init.ModTileEntities;
import com.dna.everythingisbad.network.PacketHandler;
import com.dna.everythingisbad.network.messagestypes.MessageSyncMachineGui;
import com.dna.everythingisbad.tile.utils.handlers.ModEnergyHandler;
import com.dna.everythingisbad.tile.utils.handlers.ModFluidHandler;
import com.dna.everythingisbad.tile.utils.handlers.ModItemHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;


public abstract class TileDeviceBase extends TileEntity implements ITickable {
    private String name;
    protected int progress = 0;
    protected int finishedProgress = 0;
    protected ModItemHandler itemStackHadler;
    protected ModFluidHandler fluidHandler;
    protected ModEnergyHandler energyHandler;
    private MessageSyncMachineGui messageSyncMachineGui;
    protected String displayName = "NotSet";

    public TileDeviceBase(String name){
        this.name = name;
        ModTileEntities.TILE_ENTITIES.add(this);
        messageSyncMachineGui = new MessageSyncMachineGui("",0,0,0);
    }

    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString(displayName);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if(fluidHandler != null){
            fluidHandler.getFluidTank().readFromNBT(compound);
        }
        if(itemStackHadler != null){
            ItemStackHelper.loadAllItems(compound,itemStackHadler.getItemStackList());
        }
        if(energyHandler != null){
            energyHandler.setEnergyStorage(compound.getInteger("energyStored"));
        }
        progress = compound.getInteger("progress");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        if(fluidHandler != null){
            fluidHandler.getFluidTank().writeToNBT(compound);
        }
        if(itemStackHadler != null){
            ItemStackHelper.saveAllItems(compound,itemStackHadler.getItemStackList());
        }
        if(energyHandler != null){
            compound.setInteger("energyStored",energyHandler.getEnergyStored());
        }
        compound.setInteger("progress",progress);
        return super.writeToNBT(compound);
    }

    public String getName(){
        return name;
    }

    public ModItemHandler getItemStackHadler() {
        return itemStackHadler;
    }

    public ModFluidHandler getFluidHandler() {
        return fluidHandler;
    }

    public ModEnergyHandler getEnergyHandler() {
        return energyHandler;
    }

    @Override
    public void update() {

    }

    public void sendGuiNetworkData(Container container, IContainerListener listener) {

        if (listener instanceof EntityPlayer) {
            MessageSyncMachineGui guiPacket = getGuiPacket();
            if (guiPacket != null) {
                PacketHandler.INSTANCE.sendTo(guiPacket,(EntityPlayerMP) listener);
            }
        }
    }
    public MessageSyncMachineGui getGuiPacket(){
        int energyStored = energyHandler.getEnergyStored();
        int fluidStored = fluidHandler != null ? fluidHandler.getFluidTank().getFluidAmount() : 0;
        int progress = getProgress();
        FluidStack fluid = fluidHandler != null ? fluidHandler.getFluidTank().getFluid() : null;
        String fluidType = "";
        if(fluid != null) {
            fluidType = fluid.getFluid().getUnlocalizedName();
        }
        messageSyncMachineGui.setEnergyStored(energyStored);
        messageSyncMachineGui.setProgress(progress);
        messageSyncMachineGui.setFluidType(fluidType);
        messageSyncMachineGui.setFluidStored(fluidStored);
        return messageSyncMachineGui;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY && energyHandler != null) {
            return true;
        }
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && itemStackHadler != null){
            return true;
        }
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && fluidHandler != null){
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY && energyHandler != null) {
            return CapabilityEnergy.ENERGY.cast(energyHandler);
        }
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && itemStackHadler != null){

            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHadler);
        }
        if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && fluidHandler != null){
            return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(fluidHandler);
        }
        return super.getCapability(capability, facing);
    }
    //Gets the progress at which the machine is done
    public int getFinishedProgress(){
        return finishedProgress;
    }
    public int getProgress(){
        return progress;
    }
    public void setProgress(int progress){
        this.progress = progress;
    }
    public void setFinishedProgress(int finishedProgress){
        this.finishedProgress = finishedProgress;
    }
}
