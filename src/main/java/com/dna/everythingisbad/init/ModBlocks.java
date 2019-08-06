package com.dna.everythingisbad.init;

import com.dna.everythingisbad.block.BlockStupidTNT;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks
{
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    //Blocks
    public static final Block STUPID_TNT_BLOCK = new BlockStupidTNT("stupid_tnt", Material.IRON);


}