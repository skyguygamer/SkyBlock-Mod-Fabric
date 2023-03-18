package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.skyguygamer.sbmod.SbMod;

import static net.skyguygamer.sbmod.SbMod.*;

public final class RefreshTimers {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher){
        dispatcher.register(ClientCommandManager.literal("refreshtimers").executes(RefreshTimers::run));
    }

    private static int run(CommandContext<FabricClientCommandSource> source) {
        //AutoAdvert
        advertTimer = 100000;
        SbMod.LOGGER.info("AutoAdvert timer reset");
        //AutoFix
        coolDownCounter = 100000;
        SbMod.LOGGER.info("AutoFix timer reset");
        //AutoSell
        autoSellTime = 100000;
        SbMod.LOGGER.info("AutoSell timer reset");
        //AutoBuy
        autoBuyTime = 100000;
        SbMod.LOGGER.info("AutoBuy timer reset");
        //AutoSpawnMob
        spawnTime = 100000;
        SbMod.LOGGER.info("AutoSpawnMob timer reset");

        MinecraftClient.getInstance().player.sendMessage(Text.literal(Formatting.GREEN + "All timers have been refreshed"));
        return Command.SINGLE_SUCCESS;
    }
}
