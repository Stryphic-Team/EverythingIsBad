package com.dna.everythingisbad.entityproperties;

import net.minecraft.nbt.NBTTagCompound;

public class PlayerProperties {
    public final float bankInterestRate = 0.03f;
    private boolean hasBeenInitialized = false;
    private boolean hasSoul = false;
    private boolean hasCommonColdImmunity = false;
    private boolean isBlind = false;
    private int religion = 0;
    private int timesPooped = 0;
    private float balance = 0;
    private float bankBalance = 100;
    private boolean student = false;
    private int angelDustAddictionLvl = 0;
    private int tobaccoAddictionLvl = 0;

    public PlayerProperties(){

    }
    public boolean hasBeenInitialized() {
        return hasBeenInitialized;
    }

    public void setHasBeenInitialized(boolean hasBeenInitialized) {
        this.hasBeenInitialized = hasBeenInitialized;
    }

    public boolean hasSoul() {
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

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(float bankBalance) {
        this.bankBalance = bankBalance;
    }
    public int getAngelDustAddictionLvl() { return angelDustAddictionLvl; }

    public void setAngelDustAddictionLvl(int angelDustAddictionLvl) { this.angelDustAddictionLvl = angelDustAddictionLvl; }

    public int getTobaccoAddictionLvl() {
        return tobaccoAddictionLvl;
    }

    public void setTobaccoAddictionLvl(int tobaccoAddictionLvl) {
        this.tobaccoAddictionLvl = tobaccoAddictionLvl;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public void copyFrom(PlayerProperties oldPlayerProperties){
        this.hasBeenInitialized = oldPlayerProperties.hasBeenInitialized();
        this.hasSoul = oldPlayerProperties.hasSoul();
        this.hasCommonColdImmunity = oldPlayerProperties.hasCommonColdImmunity();
        this.isBlind = oldPlayerProperties.isBlind();
        this.religion = oldPlayerProperties.getReligion();
        this.timesPooped = oldPlayerProperties.getTimesPooped();
        this.balance = oldPlayerProperties.getBalance();
        this.bankBalance = oldPlayerProperties.getBankBalance();
        this.angelDustAddictionLvl = oldPlayerProperties.getAngelDustAddictionLvl();
        this.tobaccoAddictionLvl = oldPlayerProperties.getTobaccoAddictionLvl();
        this.student = oldPlayerProperties.isStudent();
    }

    public void saveNBTData(NBTTagCompound compound){
        compound.setBoolean("hasBeenInitialized",hasBeenInitialized);
        compound.setBoolean("hasSoul",hasSoul);
        compound.setBoolean("hasCommonColdImmunity", hasCommonColdImmunity);
        compound.setBoolean("isBlind",isBlind);
        compound.setInteger("religion",religion);
        compound.setInteger("timesPooped",timesPooped);
        compound.setFloat("balance",balance);
        compound.setFloat("bankBalance",bankBalance);
        compound.setInteger("angelDustAddictionLvl", angelDustAddictionLvl);
        compound.setInteger("tobaccoAddictionLvl", tobaccoAddictionLvl);
        compound.setBoolean("student",student);
    }


    public void loadNBTData(NBTTagCompound compound){
        this.hasBeenInitialized = compound.getBoolean("hasBeenInitialized");
        this.hasCommonColdImmunity = compound.getBoolean("hasCommonColdImmunity");
        this.isBlind = compound.getBoolean("isBlind");
        this.religion = compound.getInteger("religion");
        this.timesPooped = compound.getInteger("timesPooped");
        this.hasSoul = compound.getBoolean("hasSoul");
        this.balance = compound.getFloat("balance");
        this.bankBalance = compound.getFloat("bankBalance");
        this.angelDustAddictionLvl = compound.getInteger("angelDustAddictionLvl");
        this.tobaccoAddictionLvl = compound.getInteger("tobaccoAddictionLvl");
        this.student = compound.getBoolean("student");
    }

}
