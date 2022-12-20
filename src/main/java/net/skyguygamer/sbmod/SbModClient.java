package net.skyguygamer.sbmod;

import net.fabricmc.api.ClientModInitializer;
import net.skyguygamer.sbmod.event.KeyInputHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SbModClient implements ClientModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SbModClient.class);
    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing sb keybinds");
        KeyInputHandler.register();
    }
}
