package net.skyguygamer.sbmod.commands;


import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.skyguygamer.sbmod.config.Config.staffCheck;

public final class StaffNotifications {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("staffnotify").executes(StaffNotifications::run));
    }

    private static int run(CommandContext<FabricClientCommandSource> context) {
        if (!staffCheck) {
            staffCheck = true;
            context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "Staff notifications have been enabled"));
        } else {
            staffCheck = false;
            context.getSource().sendFeedback(Text.literal(Formatting.GREEN + "Staff notifications have been disabled"));
        }
        return Command.SINGLE_SUCCESS;
    }
}