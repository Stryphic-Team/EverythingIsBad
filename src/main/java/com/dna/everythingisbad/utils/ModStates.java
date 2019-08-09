package com.dna.everythingisbad.utils;

public class ModStates {
    public static int AUTO_POOP_INTERVAL = 20 * 1 * 20;
    public static boolean IS_DEBUG = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
}
