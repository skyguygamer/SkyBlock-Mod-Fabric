package net.skyguygamer.sbmod.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientLoginConnectionEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientLoginNetworkHandler;

import static net.skyguygamer.sbmod.SbMod.loggedInToWorld;

public class LogOutloginHandler implements ClientLoginConnectionEvents.Disconnect{

    @Override
    public void onLoginDisconnect(ClientLoginNetworkHandler handler, MinecraftClient client) {
        loggedInToWorld = true;
    }
}
