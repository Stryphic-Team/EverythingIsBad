package com.dna.everythingisbad.entityproperties;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityProvider implements ICapabilityProvider, INBTSerializable<NBTTagCompound> {
    private PlayerProperties playerProperties = new PlayerProperties();
    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == InitializedPlayerProperties.PLAYER_PROPERTIES;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == InitializedPlayerProperties.PLAYER_PROPERTIES){
            return (T) playerProperties;
        }

        return null;
    }
    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        playerProperties.saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        playerProperties.loadNBTData(nbt);
    }
}
