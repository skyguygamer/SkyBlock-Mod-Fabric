package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.skyguygamer.sbmod.SbMod.*;

public class AutoSell {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("autosell").executes(AutoSell::run));

    }

    private static int run(CommandContext<FabricClientCommandSource> fabricClientCommandSourceCommandContext) {
        if (!autoSell) {
            MinecraftClient.getInstance().player.sendMessage(Text.literal(Formatting.GREEN + "Autosell is enabled!"));
            autoSell = true;
            autoSellTime = 9999;
        } else {
            autoSell = false;
            MinecraftClient.getInstance().player.sendMessage(Text.literal(Formatting.GREEN + "Autosell is disabled!"));
        }
        return Command.SINGLE_SUCCESS;
    }
}