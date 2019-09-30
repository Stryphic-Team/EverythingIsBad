package com.dna.everythingisbad.tile.generators;

import com.dna.everythingisbad.tile.TileGeneratorBase;
import net.minecraft.init.Items;

public class TileCoalGenerator extends TileGeneratorBase {
    public TileCoalGenerator() {
        super("coal_generator");
        itemStackHadler.setSlotConfig(0,true,true);
        fluidHandler = null;
        setFinishedProgress(420);
        setTotalEnergyProduced(4096);
        this.displayName = "Coal Generator";//ModBlocks.COAL_GENERATOR.getUnlocalizedName();
    }

    @Override
    public boolean hasFuel() {
        return itemStackHadler.getStackInSlot(0).getItem() == Items.COAL;
    }

    @Override
    public void consumeFuel() {
        itemStackHadler.getStackInSlot(0).shrink(1);
    }
}
