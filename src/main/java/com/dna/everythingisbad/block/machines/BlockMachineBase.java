package com.dna.everythingisbad.block.machines;

import net.minecraft.util.ITickable;

public abstract class BlockMachineBase extends BlockDeviceBase implements ITickable {
    public BlockMachineBase(String name){
        super(name);
    }

    @Override
    public void update() {

    }
}
