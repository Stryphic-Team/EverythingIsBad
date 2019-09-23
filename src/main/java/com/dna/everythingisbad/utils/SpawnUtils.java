package com.dna.everythingisbad.utils;

import com.dna.everythingisbad.Main;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import java.util.Random;

public class SpawnUtils {

    public static void spawnMobInRadius(World world, EntityPlayerMP player, EntityLiving entity, int min, int max){
        Random rand = new Random();
        double playerx = player.posX;
        double playery = player.posY;
        double playerz = player.posZ;

        // Dont juje my slepping... I mean sppeling - Adggfjggfafafafa
        double entateeX;
        double entateeY;
        double entateeZ;

        double radius;

        for (int i=0;i<100;i++){
            //Main.logger.info("Iderating trhough! " + i);
            radius = rand.nextInt(max-min)+min;

            float randomangle = 2f * (float)(Math.PI * rand.nextFloat());
            entateeX = playerx + (radius * Math.cos(randomangle) );
            entateeY = playery;
            entateeZ = playerz + (radius * Math.sin(randomangle) );

            entity.setPosition(entateeX,entateeY,entateeZ);
            entity.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entateeX,entateeY,entateeZ)),null);

            if (world.spawnEntity(entity)){

                // If you can do it, do it!!! And get out of the loup
                world.spawnEntity(entity);
                break;

            }
        }
    }
}
