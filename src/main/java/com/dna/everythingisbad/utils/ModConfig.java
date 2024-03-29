package com.dna.everythingisbad.utils;

import com.dna.everythingisbad.reference.Reference;
import net.minecraftforge.common.config.Config;


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
    @Config.Name("Baby Age-Up Time (sec)")
    public static int BABY_AGE_UP_TIME = 1200;
    @Config.Name("Baby Drop Interval")
    public static int BABY_DROP_INTERVAL = 60000;
    @Config.Name("Blindness Chance")
    public static int BLINDNESS_CHANCE = 100;
    @Config.Name("Blood Spawns on Death(Deprecated)")
    @Deprecated
    public static boolean BLOOD_SPAWNS_ON_DEATH = false;
    @Config.Name("Mob Speed Multiplier")
    public static int MOB_SPEED_MULTIPLIER = 2;
    @Config.Name("Common Cold Chance")
    public static int COMMON_COLD_CHANCE = 1000;
    @Config.Name("Negative Balance Interest")
    public static float NEGATIVE_BALANCE_INTEREST = 5f;
    @Config.Name("Student Chance")
    public static float STUDENT_CHANCE = 6f;
    @Config.Name("Copper Ore Spawns")
    @Config.RequiresMcRestart
    public static boolean COPPER_ORE_SPAWN = true;
}
