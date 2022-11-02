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
         double total = amount/64.0;
         String.format("%.2f", total);
        source.sendFeedback(((Text.literal(" - ").formatted(Formatting.RED)).append(Text.literal(amount + " / " + " 64 " + " = " + String.format("%.2f", total)).formatted(Formatting.GREEN))));

        return Command.SINGLE_SUCCESS;

    }
}
