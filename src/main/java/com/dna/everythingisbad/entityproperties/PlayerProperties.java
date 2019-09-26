package com.dna.everythingisbad.entityproperties;

import net.minecraft.nbt.NBTTagCompound;

public class PlayerProperties {
    private boolean hasBeenInitialized = false;
    private boolean hasSoul = false;
    private boolean hasCommonColdImmunity = false;
    private boolean isBlind = false;
    private int religion = 0;
    private int timesPooped = 0;
    public PlayerProperties(){

    }
    public boolean hasBeenInitialized() {
        return hasBeenInitialized;
    }

    public void setHasBeenInitialized(boolean hasBeenInitialized) {
        this.hasBeenInitialized = hasBeenInitialized;
    }

    public boolean isHasSoul() {
        return hasSoul;
    }

    public void setHasSoul(boolean hasSoul) {
        this.hasSoul = hasSoul;
    }

    public boolean hasCommonColdImmunity() {
        return hasCommonColdImmunity;
    }

    public void setHasCommonColdImmunity(boolean hasCommonColdImmunity) {
        this.hasCommonColdImmunity = hasCommonColdImmunity;
    }

    public boolean isBlind() {
        return isBlind;
    }

    public void setBlind(boolean blind) {
        isBlind = blind;
    }

    public int getReligion() {
        return religion;
    }

    public void setReligion(int religion) {
        this.religion = religion;
    }

    public int getTimesPooped() {
        return timesPooped;
    }

    public void setTimesPooped(int timesPooped) {
        this.timesPooped = timesPooped;
    }

    public void copyFrom(PlayerProperties oldPlayerProperties){
        this.hasBeenInitialized = oldPlayerProperties.hasBeenInitialized;
    }

    public void saveNBTData(NBTTagCompound compound){
        compound.setBoolean("hasBeenInitialized",hasBeenInitialized);
        compound.setBoolean("hasSoul",hasSoul);
        compound.setBoolean("hasCommonColdImmunity", hasCommonColdImmunity);
        compound.setBoolean("isBlind",isBlind);
        compound.setInteger("religion",religion);
        compound.setInteger("timesPooped",timesPooped);
    }

    public void loadNBTData(NBTTagCompound compound){
        this.hasBeenInitialized = compound.getBoolean("hasBeenInitialized");
        this.hasCommonColdImmunity = compound.getBoolean("hasCommonColdImmunity");
        this.isBlind = compound.getBoolean("isBlind");
        this.religion = compound.getInteger("religion");
        this.timesPooped = compound.getInteger("timesPooped");
    }

}
