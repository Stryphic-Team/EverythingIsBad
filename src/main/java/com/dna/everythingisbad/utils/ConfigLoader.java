package com.dna.everythingisbad.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class ConfigLoader {
    private String configDir = "config/everythingisbad/";
    private String configFile = configDir + "config.json";
    public ConfigLoader(){
        new File(configDir).mkdirs();
        boolean fileExists = new File(configFile).exists();
        //Creates a new config file with the default config state
        if(!fileExists){
            createNewConfigFile();
        }
        //Reads the current state of the config file
        String fileString = readFile();
        Gson gson = new Gson();
        ConfigModel config = gson.fromJson(fileString,ConfigModel.class);
        //Creates a new config file for outdated config files
        if( config.version == null || !new ConfigModel().version.equals(config.version)){
            createNewConfigFile();
        }
        //Needs to define variables in ModConfig for the config to take affect
        ModConfig.IS_DEBUG = config.is_debug;
        ModConfig.AUTO_POOP_INTERVAL = config.auto_poop_interval;
        ModConfig.BLOOD_SPAWNS_ON_DEATH = config.blood_spawns_on_death;
        ModConfig.BLINDNESS_CHANCE = config.blindness_chance;
        ModConfig.MOBS_MOVE_FASTER = config.mobs_move_faster;
        ModConfig.AUTO_POOP_MAX = config.auto_poop_max;
        ModConfig.MOB_SPEED_MULTIPLIER = config.mob_speed_multiplier;


    }
    private String readFile(){
        StringBuilder fileString = new StringBuilder();
        try (FileReader fileReader = new FileReader(configFile)) {
            int current;
            fileString = new StringBuilder();
            while((current = fileReader.read()) != -1){
                char character = (char) current;
                fileString.append(character);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileString.toString();

    }
    private void createNewConfigFile(){
        try {
            new File(configFile).createNewFile();
            FileWriter fileWriter = new FileWriter(configFile);
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();

            Gson gson = gsonBuilder.create();

            String configJson = gson.toJson(new ConfigModel());
            fileWriter.write(configJson);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Version needs to be updated when new config is updated
    //Default Config Model
    class ConfigModel{
        String version = "v0.2";
        int auto_poop_interval = 24000;
        boolean is_debug = false;
        int auto_poop_max = 6;
        int blindness_chance = 100;
        boolean blood_spawns_on_death = true;
        boolean mobs_move_faster = true;
        int mob_speed_multiplier = 5;
    }
}
