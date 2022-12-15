package net.skyguygamer.sbmod.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.skyguygamer.sbmod.commands.AutoAdvert;

import static net.skyguygamer.sbmod.SbMod.*;

public class LogOutHandler implements ClientPlayConnectionEvents.Disconnect {
    @Override
    public void onPlayDisconnect(ClientPlayNetworkHandler handler, MinecraftClient client) {
        AutoAdvert.sendingMessages = false;
        advertTimer = 0;
        autoBuy = false;
        autoFix = false;
        enchant = false;
        enchantAxe = false;
        enchantBoots = false;
        enchantBow = false;
        enchantChest = false;
        enchantHelmet = false;
        enchantOther = false;
        enchantRod = false;
        enchantSword = false;
        enchantTool = false;
        loggedInToWorld = false;
        loggedOn = false;
        playsound = false;
        spawnMobs = false;
        time = 0;
        welcomeMessageTime = 0;
        welcomeMsg = false;
        autoPrivate = false;
        autoSell = false;
        coolDown = false;
        enchantInHand = false;
        printMsg = false;
        unEnchant = false;
        unEnchantAxe = false;
        unEnchantBoots = false;
        unEnchantBow = false;
        unEnchantChest = false;
        unEnchantHelmet = false;
        unEnchantOther = false;
        unEnchantRod = false;
        unEnchantSword = false;
        unEnchantTool = false;
    }
}
