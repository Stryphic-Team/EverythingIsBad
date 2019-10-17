package com.dna.everythingisbad.entityhandlers.handlers;

import com.dna.everythingisbad.entityhandlers.LivingHandlerBase;
import com.dna.everythingisbad.init.ModBiomes;
import com.dna.everythingisbad.init.ModDimensions;
import com.dna.everythingisbad.utils.ModTeleporter;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Biomes;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;

public class LivingHeavenHandler extends LivingHandlerBase {
    @Override
    public void livingTick(EntityLivingBase livingBase) {
        super.livingTick(livingBase);
        if (livingBase instanceof EntityPlayerMP){
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP)livingBase;
            WorldServer server = entityPlayerMP.getServerWorld();
            // Get the biome the player is in
            BlockPos playerPos = entityPlayerMP.getPosition();
            Biome biome = entityPlayerMP.getServerWorld().getBiome(playerPos);

            if (biome.equals(ModBiomes.HEAVEN) && entityPlayerMP.posY <= 0){
                int dimension = DimensionType.OVERWORLD.getId();
                entityPlayerMP.changeDimension(dimension,new ModTeleporter(server,entityPlayerMP.posX,300,entityPlayerMP.posZ));
            }else if (!(biome.equals(ModBiomes.HEAVEN) && !(biome.equals(Biomes.SKY))) && entityPlayerMP.posY > 320 &&
                entityPlayerMP.isPotionActive(Potion.getPotionById(25))){
                int dimension = ModDimensions.HEAVEN.getId();
                entityPlayerMP.changeDimension(dimension,new ModTeleporter(server,entityPlayerMP.posX,35,entityPlayerMP.posZ));
            }
        }else if(livingBase instanceof EntityCreature){

            EntityCreature entityCreature = (EntityCreature) livingBase;
            World world = entityCreature.getEntityWorld();
            // Get the biome the player is in
            BlockPos creaturePosition = entityCreature.getPosition();
            Biome biome = world.getBiome(creaturePosition);

            if (biome.equals(ModBiomes.HEAVEN) && entityCreature.posY <= 0) {
                int dimension = DimensionType.OVERWORLD.getId();
                entityCreature.changeDimension(dimension, new ModTeleporter((WorldServer) world, entityCreature.posX, 300, entityCreature.posZ));
            } else if (!(biome.equals(ModBiomes.HEAVEN) && !(biome.equals(Biomes.SKY))) && entityCreature.posY > 320 &&
                entityCreature.isPotionActive(Potion.getPotionById(25))) {
                int dimension = ModDimensions.HEAVEN.getId();
                entityCreature.changeDimension(dimension, new ModTeleporter((WorldServer) world, entityCreature.posX, 35, entityCreature.posZ));
            }

        }
    }
}
