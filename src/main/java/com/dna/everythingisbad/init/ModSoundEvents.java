package com.dna.everythingisbad.init;

import com.dna.everythingisbad.Main;
import com.dna.everythingisbad.reference.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;


public class ModSoundEvents {
    public static SoundEvent SOUND_EVENT_GASOLINE_BABIES = register("gasoline_babies");
    public static SoundEvent SOUND_EVENT_GODS_PEE = register("gods_pee");
    public static SoundEvent SOUND_EVENT_SMART_MAN1 = register("smart_man1");
    public static SoundEvent SOUND_EVENT_SMART_MAN2 = register("smart_man2");
    public static SoundEvent SOUND_EVENT_SMART_MAN3 = register("smart_man3");
    public static SoundEvent[] SOUND_EVENT_JESUS_AMBIENT = new SoundEvent[]{
            register("jesus_ambient_0")
    };


    public static SoundEvent register(String name){
        ResourceLocation location = new ResourceLocation(Reference.MOD_ID, name);
        SoundEvent soundEvent = new SoundEvent(location);
        soundEvent.setRegistryName(location);
        ForgeRegistries.SOUND_EVENTS.register(soundEvent);
        Main.logger.info(soundEvent.getRegistryName());
        return soundEvent;
    }
}
