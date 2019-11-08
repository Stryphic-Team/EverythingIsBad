package com.dna.everythingisbad.tile.generators;

import com.dna.everythingisbad.init.ModBlocks;
import com.dna.everythingisbad.init.ModItems;
import com.dna.everythingisbad.tile.TileGeneratorBase;
import com.dna.everythingisbad.tile.utils.handlers.ModEnergyHandler;
import com.dna.everythingisbad.utils.RandomUtils;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class TileStupidCoreReactor extends TileGeneratorBase {
    public TileStupidCoreReactor() {
        super("stupid_core_reactor");

        this.energyHandler = new ModEnergyHandler((int)Math.pow(10,9),10000,0,false,true);
        //that's 50,000,000
        setTotalEnergyProduced(10000000);
        setFinishedProgress(1000);
        itemStackHadler.setSlotConfig(0,true,true);
        fluidHandler = null;
        displayName = ModBlocks.STUPID_CORE_REACTOR.getLocalizedName();
    }

    @Override
    public boolean hasFuel() {
        return itemStackHadler.getStackInSlot(0).getItem() == ModItems.STUPID_CORE_ITEM;
    }

    @Override
    public void consumeFuel() {
        itemStackHadler.extractItem(0,1,false);
    }

    @Override
    public void update() {
        super.update();
        int exposionRate = 20;

        if (inProgress()) {
            thermalHandler.addTemperatureVector(3f);
        }
        BlockPos topRandomBlock = world.getTopSolidOrLiquidBlock(new BlockPos(pos.getX() + RandomUtils.fromRangeI(-20, 20), 0, pos.getZ() + RandomUtils.fromRangeI(-20, 20)));
        if (thermalHandler.getCurrentTemperature() >= 400f) {
            if (!world.isRemote) {

                world.setBlockState(topRandomBlock, Blocks.FIRE.getDefaultState());
            }
            //
        }
        if (thermalHandler.getCurrentTemperature() > 450f) {
            exposionRate = (int) Math.max(20f - (thermalHandler.getCurrentTemperature() - 450f), 1f);
            if (tick % exposionRate == exposionRate - 1) {
                world.createExplosion(null, topRandomBlock.getX(), topRandomBlock.getY(), topRandomBlock.getZ(), 2.5f, true);
            }


        }

    }

}
