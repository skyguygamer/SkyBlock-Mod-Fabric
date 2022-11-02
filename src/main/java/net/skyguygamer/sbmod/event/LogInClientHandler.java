package net.skyguygamer.sbmod.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientLoginConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientLoginNetworkHandler;
import net.minecraft.client.network.ClientPlayNetworkHandler;

import static net.skyguygamer.sbmod.SbMod.loggedInToWorld;

public class LogInClientHandler implements ClientLoginConnectionEvents.QueryStart{

    @Override
    public void onLoginQueryStart(ClientLoginNetworkHandler handler, MinecraftClient client) {
        loggedInToWorld = true;
    }
}
