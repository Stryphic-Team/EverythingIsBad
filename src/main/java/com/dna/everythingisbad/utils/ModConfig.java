package com.dna.everythingisbad.utils;

import com.dna.everythingisbad.reference.Reference;
import net.minecraftforge.common.config.Config;

/**
 * This is for variables that will later be moved to the config
 */
@Config(modid = Reference.MOD_ID)
public class ModConfig {
    @Config.Name("Mobs Move Faster")
    public static boolean MOBS_MOVE_FASTER = true;
    @Config.Name("Debug Mode")
    public static boolean IS_DEBUG = false;
    @Config.Name("Auto Poop Interval")
    public static int AUTO_POOP_INTERVAL = 24000;
    @Config.Name("Auto Poop Max")
    public static int AUTO_POOP_MAX = 6;
    @Config.Name("Blindness Chance")
    public static int BLINDNESS_CHANCE = 100;
    @Config.Name("Blood Spawns on Death")
    public static boolean BLOOD_SPAWNS_ON_DEATH = true;
    @Config.Name("Mob Speed Multiplier")
    public static int MOB_SPEED_MULTIPLIER = 2;
    @Config.Name("Common Cold Chance")
    public static int COMMON_COLD_CHANCE = 1000;
    @Config.Name("Copper Ore Spawns")
    @Config.RequiresMcRestart
    public static boolean COPPER_ORE_SPAWN = true;
}
