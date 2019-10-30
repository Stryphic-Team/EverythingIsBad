package com.dna.everythingisbad.world.nature;

import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.utils.ModConfig;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenModOres implements IWorldGenerator {
    private WorldGenerator copperOreGen;
    public static WorldGenModOres INSTANCE = new WorldGenModOres();
    public WorldGenModOres(){
        copperOreGen = new WorldGenMinable(ModBlocks.COPPER_ORE_BLOCK.getDefaultState(),8, BlockMatcher.forBlock(Blocks.STONE));
    }
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        int xPos = chunkX * 16;
        int zPos = chunkZ * 16;
        if(world.provider.getDimension() == 0){
            for(int i = 0;i<random.nextInt(64);i++) {
                if(ModConfig.COPPER_ORE_SPAWN) {
                    copperOreGen.generate(world, random, new BlockPos(xPos, random.nextInt(64), zPos));
                }
            }
        }
    }
}
