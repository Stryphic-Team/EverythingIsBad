package com.dna.everythingisbad.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Field;
@Deprecated
public class ConfigLoader {
    private String configDir = "config/everythingisbad/";
    private String configFile = configDir + "config.json";

    public ConfigLoader(){
        File fileConfig  = new File(configFile);
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
        if(!verifyConfig(config)){
            createNewConfigFile();
            config = new ConfigModel();
        }
        //Needs to define variables in ModConfig for the config to take affect
        ModConfig.IS_DEBUG = config.is_debug;
        ModConfig.AUTO_POOP_INTERVAL = config.auto_poop_interval;
        ModConfig.BLOOD_SPAWNS_ON_DEATH = config.blood_spawns_on_death;
        ModConfig.BLINDNESS_CHANCE = config.blindness_chance;
        ModConfig.MOBS_MOVE_FASTER = config.mobs_move_faster;
        ModConfig.AUTO_POOP_MAX = config.auto_poop_max;
        ModConfig.MOB_SPEED_MULTIPLIER = config.mob_speed_multiplier;
        ModConfig.COMMON_COLD_CHANCE = config.common_cold_chance;


    }
    private String readFile(){
        StringBuilder fileString = new StringBuilder();
        FileReader fileReader;
        try {
            fileReader = new FileReader(configFile);
            int current;
            fileString = new StringBuilder();
            while((current = fileReader.read()) != -1){
                char character = (char) current;
                fileString.append(character);

            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return fileString.toString();

    }
    private void createNewConfigFile(){
        try {
            File fileConfig = new File(configFile);
            if(fileConfig.exists()) {
                fileConfig.delete();
            }
            fileConfig.createNewFile();
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
        int auto_poop_interval = 24000;
        boolean is_debug = false;
        int auto_poop_max = 6;
        int blindness_chance = 100;
        boolean blood_spawns_on_death = true;
        boolean mobs_move_faster = true;
        int mob_speed_multiplier = 1;
        int common_cold_chance = 10;
        boolean copper_ore_spawn = true;
    }
    //Checks the config for null values and returns false if it detects any
    public boolean verifyConfig(ConfigModel configModel) {

        Field[] fields = configModel.getClass().getDeclaredFields();
        for (Field f : fields) {
            Class t = f.getType();
            try {
                Object v = f.get(configModel);
                if (v == null && !(f.getName().equals("this$0"))) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }

        }
        return true;
    }
}
