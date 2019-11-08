package com.dna.everythingisbad.tile.utils.handlers;

public class ModThermalHandler {
    private float minimumTemperature;
    private float maximumTemperature;
    private float ambientTemperature;
    private float conductivity;
    private float currentTemperature;
    private float temperatureIncrementer;
    private float thermalVelocity = 0;
    private int tick = 0;
    public ModThermalHandler(float minimumTemperature, float maximumTemperature, float ambientTemperature,float conductivity){

        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
        this.ambientTemperature = ambientTemperature;
        this.conductivity = conductivity;

    }
    public void update(){
        tick++;

        if (currentTemperature > ambientTemperature) {
            this.temperatureIncrementer = -(
                    Math.abs(ambientTemperature - currentTemperature) *
                            (Math.max(0.5f, conductivity)) * 0.01f
            );
        } else if (currentTemperature < ambientTemperature) {
            this.temperatureIncrementer = (
                    Math.abs(ambientTemperature - currentTemperature) *
                            (Math.max(0.5f, conductivity)) * 0.01f
            );
        } else {
            temperatureIncrementer = 0;
        }
        this.currentTemperature += this.temperatureIncrementer;


        this.currentTemperature += thermalVelocity * 0.8f;



        this.currentTemperature = Math.min(currentTemperature,maximumTemperature);
        this.currentTemperature = Math.max(currentTemperature,minimumTemperature);
        thermalVelocity = 0;

    }

    public void addTemperatureVector(float vec1){
        thermalVelocity += vec1;
    }

    public float getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(float currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public float getMinimumTemperature() {
        return minimumTemperature;
    }

    public void setMinimumTemperature(float minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    public float getMaximumTemperature() {
        return maximumTemperature;
    }

    public void setMaximumTemperature(float maximumTemperature) {
        this.maximumTemperature = maximumTemperature;
    }

    public float getAmbientTemperature() {
        return ambientTemperature;
    }

    public void setAmbientTemperature(float ambientTemperature) {
        this.ambientTemperature = ambientTemperature;
    }

    public float getConductivity() {
        return conductivity;
    }

    public void setConductivity(float conductivity) {
        this.conductivity = conductivity;
    }
}
