package com.dna.everythingisbad.init;

import com.dna.everythingisbad.block.IOreDictBlock;
import com.dna.everythingisbad.block.buildingblocks.*;
import com.dna.everythingisbad.block.explosives.BlockStupidTNT;
import com.dna.everythingisbad.block.machines.*;
import com.dna.everythingisbad.block.misc.BlockCloud;
import com.dna.everythingisbad.block.misc.BlockEmpty;
import com.dna.everythingisbad.block.misc.BlockQuestionMark;
import com.dna.everythingisbad.block.ore.BlockCopperOre;
import com.dna.everythingisbad.block.plants.*;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    //Blocks
    public static final Block STUPID_TNT_BLOCK = new BlockStupidTNT("stupid_tnt");
    public static final Block POOP_BLOCK = new BlockPoop("poop_block");
    public static final Block POOP_BRICKS_BLOCK = new BlockPoopBricks("poop_bricks");
    public static final Block BLOOD_BLOCK = new BloodBlock("blood_block");
    public static final Block URINE_BLOCK = new BlockUrine("urine_block");
    public static final Block URINE_BRICKS_BLOCK = new BlockUrineBricks("urine_bricks");
    public static final Block LOG_HAPPY_BLOCK = new BlockLogHappy("log_happy");
    public static final Block LEAVES_HAPPY_BLOCK = new BlockLeavesHappy("leaves_happy");
    public static final Block SAPLING_HAPPY_BLOCK = new BlockSaplingHappy("sapling_happy");
    public static final Block QUESTION_MARK_BLOCK = new BlockQuestionMark("question_mark_block");
    public static final Block EMPTY_BLOCK = new BlockEmpty("empty_block");
    public static final Block ALOE_BLOCK = new BlockAloe("aloe");
    public static final Block TOBACCO_BLOCK = new BlockTobacco("tobacco");
    public static final Block CLOUD_BLOCK = new BlockCloud("cloud_block");
    public static final Block COPPER_ORE_BLOCK = new BlockCopperOre("copper_ore");

    // Machines and generaters
    public static final Block FLUX_TEST = new BlockFluxTest("flux_test");
    public static final Block STUPID_CORE_MACHINE = new BlockStupidCoreMachine("stupid_core_machine");
    public static final Block DRYER_MACHINE = new BlockDryerMachine("dryer_machine");
    public static final Block DIARIC_GENERATOR = new BlockDiaricGenerator("diaric_generator");
    public static final Block LIQUIFIER_MACHINE = new BlockLiquifier("liquifier_machine");
    public static final Block STUPID_CORE_REACTOR = new BlockStupidCoreReactor("stupid_core_reactor");
    public static final Block MACHINE_FRAME = new BlockMachineFrame("machine_frame");
    public static final Block URINE_BATTERY = new BlockUrineBattery("urine_battery");
    public static final Block COAL_GENERATOR = new BlockCoalGenerator("coal_generator");
    public static final Block QUARRY = new BlockQuarry("quarry");
    public static final Block SLOT_MACHINE = new BlockSlotMachine("slot_machine");
    public static final Block AUTOMATED_TELLER_MACHINE = new BlockAutomatedTellerMachine("automated_teller_machine");

    // Woke blocks
    public static final Block POOP_STAIRS = new BlockPoopStairs("poop_stairs");
    public static final Block POOP_FENCE = new BlockPoopFence("poop_fence");

    public static void init(){
        for(Block block:BLOCKS){
            ForgeRegistries.BLOCKS.register(block);
        }
    }
    public static void initOreDictionary(){
        for(Block block:BLOCKS){
            if(block instanceof IOreDictBlock){
                ((IOreDictBlock)block).initOreDict();
            }
        }
    }
}