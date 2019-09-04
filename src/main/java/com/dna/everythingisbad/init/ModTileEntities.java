package com.dna.everythingisbad.init;

import com.dna.everythingisbad.tile.*;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;

public class ModTileEntities {
    public static ArrayList<TileDeviceBase> TILE_ENTITIES = new ArrayList<TileDeviceBase>();
    public static final TileStupidCoreMachine STUPID_CORE_MACHINE = new TileStupidCoreMachine();
    public static final TileDiaricGenerator DIARIC_GENERATOR = new TileDiaricGenerator();
    public static final TileFluxTest FLUX_TEST = new TileFluxTest();
    public static final TileDryerMachine DRYER_MACHINE = new TileDryerMachine();

    public static void register(){
        for(TileDeviceBase device: TILE_ENTITIES){
            TileEntity.register(device.getName(),device.getClass());
        }
    }
}
