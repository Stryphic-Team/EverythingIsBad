package com.dna.everythingisbad.utils;


import org.lwjgl.util.vector.Vector2f;
import scala.util.Random;

public class RandomUtils {
    public static Random random = new Random();

    /**
     * generates a random number between two integers
     * @param min
     * @param max
     * @return random integer
     */
    public static int fromRangeI(int min,int max){
        if(min <= 0) {
            return random.nextInt(max + (Math.abs(min))) + min;
        }else {
            return random.nextInt(max - (Math.abs(min))) + min;
        }
    }

    /**
     * generates a random number between two floating points
     * @param min
     * @param max
     * @return random float
     */
    public static float fromRangeF(float min, float max){
        if(min <= 0) {
            return nextFloat(max + (Math.abs(min))) + min;
        }else{
            return nextFloat(max - (Math.abs(min))) + min;
        }
    }

    /**
     * Improves the vanilla nextFloat function by adding a bound parameter
     * @param bound
     * @return new random bounded float
     */
    public static float nextFloat(float bound){
        return random.nextFloat()*bound;
    }

    /**
     * returns true if probability has been met
     * @param percent
     * @return boolean
     */
    public static boolean percentChance(int percent){
        int percentMM = Math.min(percent,0);
        percentMM = Math.max(percentMM,100);

        if(random.nextInt(100) <= percentMM){
            return true;
        }
        return  false;
    }

    /**
     * provides a 1 in (n) chance to return true
     * @param probabbility
     * @return boolean
     */
    public static boolean withinChance(int probabbility){
        if(random.nextInt(probabbility) == 1){
            return true;
        }
        return false;
    }

    /**
     * returns a position within a given min and max of a point
     * @param point
     * @param minDistance
     * @param maxDistance
     * @return returns a position around the player
     */
    public static Vector2f randomPositionAround(Vector2f point,int minDistance,int maxDistance){
        float angle = fromRangeF(0,(float)Math.PI * 2f);
        float x = (float)Math.cos(angle);
        float y = (float)Math.sin(angle);
        float radius = fromRangeF(minDistance,maxDistance);
        Vector2f position = new Vector2f(x*radius,y*radius);
        return  position;
    }
}
