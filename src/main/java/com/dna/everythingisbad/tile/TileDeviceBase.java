package com.dna.everythingisbad.tile;

import com.dna.everythingisbad.capabilities.ModEnergyHandler;
import com.dna.everythingisbad.capabilities.ModFluidHandler;
import com.dna.everythingisbad.capabilities.ModItemHandler;
import com.dna.everythingisbad.init.ModTileEntities;
import com.dna.everythingisbad.network.PacketHandler;
import com.dna.everythingisbad.network.messagestypes.MessageSyncMachineGui;
import com.dna.everythingisbad.utils.FluidCache;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;


public class TileDeviceBase extends TileEntity implements ITickable {
    private String name;
    private int progress;
    private int finishedProgress;
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
    public void setField(EnumTileDataType type,int data){
        FluidStack fluidStack;
        switch (type){
            case ENERGY_STORAGE:
                energyHandler.setEnergyStorage(data);
                break;
            case PROGRESS:
                this.progress = data;
                break;
            case FLUID_STORED:
                fluidStack = fluidHandler.getFluidTank().getFluid();
                if(fluidStack != null){
                    fluidStack.amount = data;
                }
                break;
            case FLUID_TYPE:
                fluidHandler.getFluidTank().setFluid(new FluidStack(FluidCache.fromInt(data),0));
                break;
        }
    }
    public int getField(EnumTileDataType type){
        switch (type){
            case ENERGY_STORAGE:
                return energyHandler.getEnergyStored();
            case PROGRESS:
                return progress;
            case FLUID_STORED:
                return fluidHandler.getFluidTank().getFluidAmount();
            case FLUID_TYPE:
                FluidStack fluid = fluidHandler.getFluidTank().getFluid();
                if(fluid != null){
                    return FluidCache.toInt(fluidHandler.getFluidTank().getFluid().getUnlocalizedName());
                }else {
                    return -1;
                }
            default:
                return 0;
        }
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
