package com.dna.everythingisbad.utils;

/**
 * This is for variables that will later be moved to the config
 */
public class ModConfig {

    public static boolean IS_DEBUG = false;
    public static int AUTO_POOP_INTERVAL = IS_DEBUG ? 20 * 1 * 20 : 20 * 60 * 20;
    public static int AUTO_POOP_MAX = 6; //
    public static int BLINDNESS_CHANCE = IS_DEBUG ? 2 : 100; // between zero and this, exclusive
}
