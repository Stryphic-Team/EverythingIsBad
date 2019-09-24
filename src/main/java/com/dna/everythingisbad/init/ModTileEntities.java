package com.dna.everythingisbad.init;

import com.dna.everythingisbad.tile.*;
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

    public static void register(){
        for(TileDeviceBase device: TILE_ENTITIES){
            TileEntity.register(device.getName(),device.getClass());
        }
    }
}
