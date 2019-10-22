package com.dna.everythingisbad.tile;

import com.dna.everythingisbad.block.machines.BlockDeviceBase;
import com.dna.everythingisbad.init.ModTileEntities;
import com.dna.everythingisbad.network.PacketHandler;
import com.dna.everythingisbad.network.messagestypes.MessageSyncMachineGui;
import com.dna.everythingisbad.tile.utils.handlers.ModEnergyHandler;
import com.dna.everythingisbad.tile.utils.handlers.ModFluidHandler;
import com.dna.everythingisbad.tile.utils.handlers.ModItemHandler;
import com.dna.everythingisbad.tile.utils.helpers.WorldUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;


public abstract class TileDeviceBase extends TileEntity implements ITickable {
    private String name;
    protected int progress = 0;
    protected int finishedProgress = 0;
    protected ModItemHandler itemStackHadler;
    protected ModFluidHandler fluidHandler;
    protected ModEnergyHandler energyHandler;
    protected float temperature = 23;
    protected float temperatureIncrement = 0.1f;
    protected float targetTemperature = 23;
    private MessageSyncMachineGui messageSyncMachineGui;
    protected int tick = 0;
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
        temperature = compound.getFloat("temperature");
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
        compound.setFloat("temperature",temperature);
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
    public void update(){

        tick++;
        if (tick % 10 == 1) {
            switch (world.getBiome(pos).getTempCategory()) {
                case COLD:
                    targetTemperature = -5;
                    break;
                case WARM:
                    targetTemperature = 30;
                    break;
                case MEDIUM:
                    targetTemperature = 23;
                    break;
                case OCEAN:
                    targetTemperature = 10;
                    break;
            }
        }
        updateTemperature();
        ArrayList<IBlockState> blockStates;
        if (tick % 4 == 2) {
            BlockPos firstPoint = pos.add(-1, -1, -1);
            BlockPos secondPoint = pos.add(1, 1, 1);
            blockStates = WorldUtils.getBlocksInCuboid(world,
                    new WorldUtils.SearchBoundingBox(firstPoint, secondPoint));
            for (IBlockState blockState : blockStates) {
                if (blockState == Blocks.WATER.getDefaultState()) {
                    targetTemperature = 10;
                    updateTemperature();
                }
                if (blockState == Blocks.LAVA.getDefaultState()) {
                    targetTemperature = 700;
                    updateTemperature();
                }
            }
        }
        updateBlockState();

    }
    public void updateTemperature(){
        if(targetTemperature - temperature < 0) {
            temperature -= temperatureIncrement;
        }else{
            temperature += temperatureIncrement;
        }
        temperatureIncrement = (float)(Math.log(Math.abs((targetTemperature - temperature))+1d))/50;
    }

    public void updateBlockState(){
        if(tick % 20 == 19) {
            IBlockState currentState = world.getBlockState(pos);
            IBlockState defaultState = currentState.getBlock().getDefaultState();

            if (inProgress()) {
                //world.setBlockState(pos, defaultState.withProperty(BlockDeviceBase.FACING, currentState.getValue(BlockDeviceBase.FACING)).withProperty(BlockDeviceBase.ACTIVE,true), 3);
                BlockDeviceBase.setState(true,currentState,world,pos);
                //PacketHandler.INSTANCE.sendToAll(new MessageTileSync(pos.getX(),pos.getY(),pos.getZ(),true));
            }else{
                //world.setBlockState(pos, defaultState.withProperty(BlockDeviceBase.FACING, currentState.getValue(BlockDeviceBase.FACING)).withProperty(BlockDeviceBase.ACTIVE,false), 3);
                BlockDeviceBase.setState(false,currentState,world,pos);
                //PacketHandler.INSTANCE.sendToAll(new MessageTileSync(pos.getX(),pos.getY(),pos.getZ(),false));
            }
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
        int energyStored = energyHandler != null ? energyHandler.getEnergyStored() : 0;
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
    public float getTemperature(){
        return this.temperature;
    }
    public void stepProgress(){
        progress++;
    }
    public boolean inProgress(){
        return progress > 0 && progress <= finishedProgress;
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return super.getUpdatePacket();
    }
}
