package net.skyguygamer.sbmod.sounds;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.skyguygamer.sbmod.SbMod;

import static net.skyguygamer.sbmod.SbMod.LOGGER;

public class ModSounds {
    public static SoundEvent NOTIFY_SOUND = registerSoundEvent("notify_sound");

    private static SoundEvent registerSoundEvent(String  name) {
        Identifier id = new Identifier(SbMod.SBMOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void registerSounds(){
        LOGGER.info("Registering sounds");
    }
}
