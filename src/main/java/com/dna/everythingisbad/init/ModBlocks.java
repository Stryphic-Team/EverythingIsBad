package com.dna.everythingisbad.init;

import com.dna.everythingisbad.block.*;
import com.dna.everythingisbad.block.machines.*;
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
    public static final Block BLOOD_BLOCK = new BloodBlock("blood_block");
    public static final Block URINE_BLOCK = new BloodBlock("urine_block");
    public static final Block LOG_HAPPY_BLOCK = new BlockLogHappy("log_happy");
    public static final Block LEAVES_HAPPY_BLOCK = new BlockLeavesHappy("leaves_happy");
    public static final Block SAPLING_HAPPY_BLOCK = new BlockSaplingHappy("sapling_happy");
    public static final Block QUESTION_MARK_BLOCK = new BlockQuestionMark("question_mark_block");
    public static final Block EMPTY_BLOCK = new BlockEmpty("empty_block");
    public static final Block ALOE_BLOCK = new BlockAloe("aloe");
    public static final Block TOBACCO_BLOCK = new BlockTobacco("tobacco");

    // Machines and generaters
    public static final Block FLUX_TEST = new BlockFluxTest("flux_test");
    public static final Block STUPID_CORE_MACHINE = new BlockStupidCoreMachine("stupid_core_machine");
    public static final Block DRYER_MACHINE = new BlockDryerMachine("dryer_machine");
    public static final Block DIARIC_GENERATOR = new BlockDiaricGenerator("diaric_generator");
    public static final Block MACHINE_FRAME = new BlockMachineFrame("machine_frame");

    // Woke blocks
    public static final Block POOP_STAIRS = new BlockPoopStairs(POOP_BRICKS_BLOCK.getDefaultState(),"poop_stairs");
    public static final Block POOP_FENCE = new BlockPoopFence("poop_fence");

    public static void init(){
        for(Block block:BLOCKS){
            ForgeRegistries.BLOCKS.register(block);
        }
    }
}