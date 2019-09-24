package com.dna.everythingisbad.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;

public class BlockCloud extends BlockBase {

    public BlockCloud(String name){
        super(name, Material.GROUND);
        setHardness(0.15f);
        setSoundType(SoundType.SNOW);

    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
}
