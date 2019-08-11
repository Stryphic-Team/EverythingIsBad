package com.dna.everythingisbad.utils.helpers;

public class TimeHelper {
    private static final int TICK_RATE = 20; //20 per second;

    public static int fromHours(int hours){
        return  fromMinutes(60) * hours;
    }
    public static int fromMinutes(int minutes){
        return fromSeconds(60) * minutes;
    }
    public static int fromSeconds(int seconds){
        return TICK_RATE * seconds;
    }
    public static int toHours(int ticks){
        return ticks / fromHours(1);
    }
    public static int toMinutes(int ticks){
        return ticks / fromMinutes(1);
    }
    public static int toSeconds(int ticks){
        return ticks / fromSeconds(1);
    }

}
