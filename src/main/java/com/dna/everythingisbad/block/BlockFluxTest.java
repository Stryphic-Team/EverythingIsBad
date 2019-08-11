package com.dna.everythingisbad.block;

import com.dna.everythingisbad.creativetab.CreativeTab;
import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.utils.CommonUtils;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.energy.IEnergyStorage;


public class BlockFluxTest extends BlockBase implements IEnergyStorage {
    public BlockFluxTest(String name){
        setRegistryName(name);
        setUnlocalizedName(CommonUtils.createUnlocalizedName(name));
        setHardness(1f);
        this.setCreativeTab(CreativeTab.EVERYTHING_BAD_TAB);
        ModBlocks.BLOCKS.add(this);
        ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 500;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 500;
    }

    @Override
    public int getEnergyStored() {
        return 5000;
    }

    @Override
    public int getMaxEnergyStored() {
        return 5000;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return false;
    }
}
