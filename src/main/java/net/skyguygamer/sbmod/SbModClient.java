package net.skyguygamer.sbmod;

import net.fabricmc.api.ClientModInitializer;
import net.skyguygamer.sbmod.event.KeyInputHandler;

public class SbModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
    }
}
