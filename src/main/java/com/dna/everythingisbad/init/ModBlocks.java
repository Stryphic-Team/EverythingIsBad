package com.dna.everythingisbad.init;

import com.dna.everythingisbad.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    //Blocks
    public static final Block STUPID_TNT_BLOCK = new BlockStupidTNT("stupid_tnt", Material.IRON);
    public static final Block POOP_BLOCK = new BlockPoop("poop_block",Material.GROUND);
    public static final Block POOP_BRICKS_BLOCK = new BlockPoopBricks("poop_bricks",Material.ROCK);
    public static final Block FLUX_TEST = new BlockFluxTest("flux_test");
    public static final Block BLOOD_BLOCK = new BloodBlock("blood_block");

    public static void init(){
        for(Block block:BLOCKS){
            ForgeRegistries.BLOCKS.register(block);
        }
    }
}