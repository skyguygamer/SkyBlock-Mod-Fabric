package net.skyguygamer.sbmod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Divide64 {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("64").then(ClientCommandManager.argument("amount", IntegerArgumentType.integer())
                .executes(Divide64 -> run(Divide64.getSource(),
                        IntegerArgumentType.getInteger(Divide64, "amount")
                ))));
    }

    private static int run(FabricClientCommandSource source, double amount) {
         double total = amount/64;
         double remainder = amount%64;
         source.sendFeedback(Text.literal(Formatting.RED + " - " + Formatting.GREEN +  String.format("%.0f", amount) + " / 64"  + " = " + Formatting.WHITE + String.format("%.2f", total) + Formatting.GREEN + " or " + Formatting.WHITE + String.format("%.0f", Math.floor(total)) + Formatting.GREEN + " stacks and " + Formatting.WHITE + String.format("%.0f", remainder) + Formatting.GREEN + " grass"));
         return Command.SINGLE_SUCCESS;
    }
}
