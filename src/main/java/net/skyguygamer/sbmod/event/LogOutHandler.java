package net.skyguygamer.sbmod.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.skyguygamer.sbmod.commands.AutoAdvert;

import static net.skyguygamer.sbmod.SbMod.*;

public class LogOutHandler implements ClientPlayConnectionEvents.Disconnect {
    @Override
    public void onPlayDisconnect(ClientPlayNetworkHandler handler, MinecraftClient client) {
        loggedOn = false;
        time = 0;
        loggedInToWorld = false;
        welcomeMessageTime = 0;
        enchantSword = false;
        playsound = false;
        enchantTool = false;
        enchantChest = false;
        enchantBow = false;
        enchantHelmet = false;
        enchantBoots = false;
        enchantRod = false;
        enchantOther = false;
        enchantAxe = false;
        enchant = false;
        spawnMobs = false;
        autoFix = false;
        welcomeMsg = false;
        AutoAdvert.sendingMessages = false;
        advertTimer = 0;
        autoBuy = false;
    }
}
