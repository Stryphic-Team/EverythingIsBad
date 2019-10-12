package com.dna.everythingisbad.init;

import com.dna.everythingisbad.tile.TileDeviceBase;
import com.dna.everythingisbad.tile.generators.TileCoalGenerator;
import com.dna.everythingisbad.tile.generators.TileDiaricGenerator;
import com.dna.everythingisbad.tile.generators.TileFluxTest;
import com.dna.everythingisbad.tile.generators.TileStupidCoreReactor;
import com.dna.everythingisbad.tile.misc.TileSlotMachine;
import com.dna.everythingisbad.tile.processing.TileDryerMachine;
import com.dna.everythingisbad.tile.processing.TileLiquifierMachine;
import com.dna.everythingisbad.tile.processing.TileQuarry;
import com.dna.everythingisbad.tile.processing.TileStupidCoreMachine;
import com.dna.everythingisbad.tile.storage.TileUrineBattery;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;

public class ModTileEntities {
    public static ArrayList<TileDeviceBase> TILE_ENTITIES = new ArrayList<TileDeviceBase>();
    public static final TileStupidCoreMachine TILE_STUPID_CORE_MACHINE = new TileStupidCoreMachine();
    public static final TileDiaricGenerator TILE_DIARIC_GENERATOR = new TileDiaricGenerator();
    public static final TileFluxTest TILE_FLUX_TEST = new TileFluxTest();
    public static final TileDryerMachine TILE_DRYER_MACHINE = new TileDryerMachine();
    public static final TileLiquifierMachine TILE_LIQUIFIER_MACHINE = new TileLiquifierMachine();
    public static final TileUrineBattery TILE_URINE_BATTERY = new TileUrineBattery();
    public static final TileStupidCoreReactor TILE_STUPID_CORE_REACTOR = new TileStupidCoreReactor();
    public static final TileCoalGenerator TILE_COAL_GENERATOR = new TileCoalGenerator();
    public static final TileQuarry TILE_QUARRY = new TileQuarry();
    public static final TileSlotMachine TILE_SLOT_MACHINE = new TileSlotMachine();

    public static void register(){
        for(TileDeviceBase device: TILE_ENTITIES){
            TileEntity.register(device.getName(),device.getClass());
        }
    }
}
