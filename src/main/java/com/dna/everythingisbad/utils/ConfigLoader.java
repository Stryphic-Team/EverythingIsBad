package com.dna.everythingisbad.utils;

import com.dna.everythingisbad.Main;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class ConfigLoader {
    private String configDir = "config/everythingisbad/";
    private String configFile = configDir + "config.json";
    public ConfigLoader(){
        new File(configDir).mkdirs();
        boolean fileExists = new File(configFile).exists();
        if(!fileExists){
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
        String fileString = readFile();
        Gson gson = new Gson();
        ConfigModel config = gson.fromJson(fileString,ConfigModel.class);
        Main.logger.info(config.auto_poop_interval);
        ModConfig.IS_DEBUG = config.is_debug;
        ModConfig.AUTO_POOP_INTERVAL = config.auto_poop_interval;
        ModConfig.BLOOD_SPAWNS_ON_DEATH = config.blood_spawns_on_death;
        ModConfig.BLINDNESS_CHANCE = config.blindness_chance;
        ModConfig.MOBS_MOVE_FASTER = config.mobs_move_faster;
        ModConfig.AUTO_POOP_MAX = config.auto_poop_max;


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
    class ConfigModel{
        int auto_poop_interval = 24000;
        boolean is_debug = false;
        int auto_poop_max = 6;
        int blindness_chance = 100;
        boolean blood_spawns_on_death = true;
        boolean mobs_move_faster = true;
    }
}
