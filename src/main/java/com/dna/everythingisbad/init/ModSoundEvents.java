package com.dna.everythingisbad.init;

import com.dna.everythingisbad.reference.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class ModSoundEvents {
    static ResourceLocation location = new ResourceLocation(Reference.MOD_ID, "gasoline_babies");
    public static SoundEvent SOUND_EVENT_GASOLINE_BABIES = new SoundEvent(location);

    static ResourceLocation location2 = new ResourceLocation(Reference.MOD_ID, "gods_pee");
    public static SoundEvent SOUND_EVENT_GODS_PEE = new SoundEvent(location2);

}
