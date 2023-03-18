package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.skyguygamer.sbmod.config.Config.hoverHack;

public final class HoverHelper {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("hoverhack").executes(HoverHelper::run));
    }

    private static int run(CommandContext<FabricClientCommandSource> source) {
        if(!hoverHack) {
            hoverHack = true;
            source.getSource().sendFeedback(Text.literal(Formatting.GREEN + "Hover hack has been enabled!"));
        } else {
            hoverHack = false;
            source.getSource().sendFeedback(Text.literal(Formatting.GREEN + "Hover hack has been disabled!"));
        }
        return Command.SINGLE_SUCCESS;
    }


}
