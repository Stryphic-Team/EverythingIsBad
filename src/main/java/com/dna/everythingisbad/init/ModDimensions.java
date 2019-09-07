package com.dna.everythingisbad.init;

import com.dna.everythingisbad.world.dimension.HeavenProvider;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class ModDimensions {
    public static final DimensionType HEAVEN = DimensionType.register("heaven","_heaven",
            420,HeavenProvider.class,false);

    public static void registerDimensions(){
        DimensionManager.registerDimension(420,HEAVEN);
    }
}
